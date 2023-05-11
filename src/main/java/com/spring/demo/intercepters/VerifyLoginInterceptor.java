package com.spring.demo.intercepters;

import com.spring.demo.annotation.VerifyLogin;
import com.spring.demo.config.SpringApplicationContext;
import com.spring.demo.enums.ErrorUrlEnum;
import com.spring.demo.enums.ResponseResultInfoEnum;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyLoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        VerifyLogin verifyLogin = handlerMethod.getMethodAnnotation(VerifyLogin.class);
        if (verifyLogin == null) {
            verifyLogin = handlerMethod.getBeanType().getAnnotation(VerifyLogin.class);
        }

        if (verifyLogin == null) {
            dealRequestDispatcher(request, response, ResponseResultInfoEnum.ERROR_AUTH_TOKEN.getCode(), ErrorUrlEnum.COMMON.getHeaderName());
            return false;
        }

        VerifyLoginService verifyLoginService = (VerifyLoginService) SpringApplicationContext.getBean("verifyLoginService");
        return verifyLoginService.verifyToken(request, response, verifyLogin);
    }

    private void dealRequestDispatcher(HttpServletRequest request, HttpServletResponse response, int code, String headerName) throws Exception {
        request.setAttribute("code", code);
        request.getRequestDispatcher(ErrorUrlEnum.getErrorUrlByHeaderName(headerName)).forward(request, response);
    }
}
