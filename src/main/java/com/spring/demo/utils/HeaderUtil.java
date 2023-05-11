package com.spring.demo.utils;


import com.google.common.base.Strings;
import com.google.common.net.HttpHeaders;
import com.spring.demo.header.Header;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 公共Header工具类
 *
 * @author Ge Hui
 */
public final class HeaderUtil {

    private HeaderUtil() {
    }

    public static Header buildFromHttp(HttpServletRequest httpRequest, @Nullable HttpServletResponse httpResponse) {

        return Header.builder()
                .requestUri(httpRequest.getRequestURI())
                .requestUrl(httpRequest.getRequestURL().toString())
                .remoteIp(getRemoteIp(httpRequest))
                .referer(Strings.nullToEmpty(httpRequest.getHeader(HttpHeaders.REFERER)))
                .build();
    }

    public static String getRemoteIp(HttpServletRequest request) {
        return RequestUtil.getRemoteIp(request);
    }

    private static final String HTTP_REQUEST_ATTR_HEADER = HeaderUtil.class.getName() + ".HTTP_REQUEST_ATTR_HEADER";

    public static void setHeaderToHttpRequestAttr(HttpServletRequest request, Header header) {
        request.setAttribute(HTTP_REQUEST_ATTR_HEADER, header);
    }

    public static Header getHeaderFromHttpRequestAttr(HttpServletRequest request) {
        return (Header) request.getAttribute(HTTP_REQUEST_ATTR_HEADER);
    }

    public static Header getHeaderFromHttpRequestAttr(NativeWebRequest webRequest) {
        return (Header) webRequest.getAttribute(HTTP_REQUEST_ATTR_HEADER, RequestAttributes.SCOPE_REQUEST);
    }

}
