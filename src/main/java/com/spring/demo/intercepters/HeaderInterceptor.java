package com.spring.demo.intercepters;

import com.spring.demo.header.Header;
import com.spring.demo.token.AuthTokenCheck;
import com.spring.demo.token.TokenCheckResult;
import com.spring.demo.utils.HeaderUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HeaderInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Header header = integrationHeader(request, response, handler);

        HeaderUtil.setHeaderToHttpRequestAttr(request, header);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

    /**
     * 整合header，主要处理常规的header信息，还有没有VerifyLogin注解同样需要用户的信息补充
     *
     * @param request
     * @param response
     * @return
     */
    private Header integrationHeader(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Header header = HeaderUtil.buildFromHttp(request, response);
        String token = AuthTokenCheck.getOriginAuthToken(request);
        if (StringUtils.isBlank(token)) {
            return header;
        }
        try {
            TokenCheckResult checkResult = AuthTokenCheck.getAndJudgeAuthToken(request);
            if (!checkResult.success()) {
                return header;
            }
            return TokenHeaderConvert.supplementHeader(header, checkResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return header;
    }
}
