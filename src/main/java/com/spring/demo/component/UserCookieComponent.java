package com.spring.demo.component;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserCookieComponent {
    public static final String USER_ID = "access-token";

    public static final int EXPIRE_TIME = 30 * 24 * 60 * 60;

    protected static final String URL_START = "/";

    public void addCookie(String key, String value, int expiry) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        Cookie cookie = new Cookie(key, value);
        cookie.setPath(URL_START);
        cookie.setMaxAge(expiry);
        response.addCookie(cookie);
    }

    public void addLoginCookie(String value) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        Cookie cookie = new Cookie(USER_ID, value);
        cookie.setMaxAge(EXPIRE_TIME);
        cookie.setPath(URL_START);
        response.addCookie(cookie);
    }
}
