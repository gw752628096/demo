package com.spring.demo.token;

import com.spring.demo.config.SpringApplicationContext;
import com.spring.demo.enums.ResponseResultInfoEnum;
import com.spring.demo.utils.AuthToken;
import com.spring.demo.utils.AuthTokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class AuthTokenCheck {

    /**
     * Token密钥
     */
    public static final String TOKEN_PARAM_NAME = "access-token";
    private static final StringRedisTemplate stringRedisTemplate;

    static {
        stringRedisTemplate = (StringRedisTemplate) SpringApplicationContext.getBean("stringRedisTemplate");
    }

    /**
     * 从request中获取原始token
     *
     * @param request
     * @return
     */
    public static String getOriginAuthToken(HttpServletRequest request) {
        return getTokenByRequest(request);
    }

    /**
     * 获取token
     *
     * @param request
     * @return
     */
    private static String getTokenByRequest(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_PARAM_NAME);
        if (token == null) {
            token = request.getParameter(TOKEN_PARAM_NAME);
        }
        return token;
    }

    public static AuthToken getValidChangeToken(String originToken) {
        AuthToken authToken;
        try {
            //校验并兼容token的处理
            authToken = AuthTokenUtils.parseToken(originToken, stringRedisTemplate.opsForValue().get("login.token.encodedKey"));
            if (authToken == null) {
                return null;
            }

            Date expDate = new Date(Long.parseLong(authToken.getExp()));
            if (new Date().after(expDate)) {
                return null;
            }

            return authToken;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static TokenCheckResult getAndJudgeAuthToken(HttpServletRequest request) {
        return judgeValidToken(getTokenByRequest(request));
    }

    public static TokenCheckResult judgeValidToken(String originToken) {
        try {
            //token内容为空的无效token
            if (StringUtils.isEmpty(originToken)) {
                return new TokenCheckResult(ResponseResultInfoEnum.ERROR_AUTH_TOKEN.getCode());
            }
            //增加内容无效token的判断，可能是null字符串，采用token的长度进行判断，有效的token至少20字符
            if (originToken.length() <= 20) {
                return new TokenCheckResult(ResponseResultInfoEnum.ERROR_AUTH_TOKEN.getCode());
            }

            AuthToken token = getValidChangeToken(originToken);
            //token为空或者过期
            if (null == token) {
                return new TokenCheckResult(ResponseResultInfoEnum.ERROR_AUTH_TOKEN.getCode()).token(token);
            }

            if (StringUtils.isBlank(token.getUserId())) {
                return new TokenCheckResult(ResponseResultInfoEnum.ERROR_AUTH_TOKEN.getCode()).token(token);
            }

            //正常返回对象
            return new TokenCheckResult().token(token).originToken(originToken);
        } catch (Exception e) {
            return new TokenCheckResult(ResponseResultInfoEnum.ERROR_AUTH_TOKEN.getCode());
        }
    }
}

