package com.spring.demo.intercepters;

import com.spring.demo.annotation.VerifyLogin;
import com.spring.demo.enums.ErrorUrlEnum;
import com.spring.demo.header.Header;
import com.spring.demo.token.AuthTokenCheck;
import com.spring.demo.token.TokenCheckResult;
import com.spring.demo.utils.HeaderUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service("verifyLoginService")
public class VerifyLoginService {

    public boolean verifyToken(HttpServletRequest request, HttpServletResponse response, VerifyLogin verifyLogin) throws Exception {
        TokenCheckResult checkResult = AuthTokenCheck.getAndJudgeAuthToken(request);
        if (checkResult.success()) {
            Header header = HeaderUtil.getHeaderFromHttpRequestAttr(request);
            if (header == null) {
                header = HeaderUtil.buildFromHttp(request, response);
            }
            HeaderUtil.setHeaderToHttpRequestAttr(request, TokenHeaderConvert.supplementHeader(header, checkResult));
            return true;
        }
        dealRequestDispatcher(request, response, checkResult.getCode(), verifyLogin.headerName());
        return false;
    }


    private void dealRequestDispatcher(HttpServletRequest request, HttpServletResponse response, int code, String headerName) throws Exception {
        request.setAttribute("code", code);
        request.getRequestDispatcher(ErrorUrlEnum.getErrorUrlByHeaderName(headerName)).forward(request, response);
    }
}
