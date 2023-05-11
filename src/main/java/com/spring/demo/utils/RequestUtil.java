package com.spring.demo.utils;

import com.google.common.net.InetAddresses;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public final class RequestUtil {

    private RequestUtil() {
    }

    public static String getRemoteIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip)) {
            String[] ips = ip.split(",");
            if (ips.length > 0) {
                return ips[0];

            } else {
                return ip;
            }
        }
        ip = request.getHeader("Proxy-Client-IP");
        if (!StringUtils.isBlank(ip) && InetAddresses.isInetAddress(ip)) {
            return ip;
        }
        ip = request.getHeader("WL-Proxy-Client-IP");
        if (!StringUtils.isBlank(ip) && InetAddresses.isInetAddress(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }
}
