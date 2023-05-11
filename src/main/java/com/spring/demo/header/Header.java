package com.spring.demo.header;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 请求处理的公共头信息
 *
 * @author Ge Hui
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder", toBuilder = true)
public class Header implements Serializable {
    private static final long serialVersionUID = -5113403946687367428L;

    /**
     * 访问服务url地址
     */
    private String requestUri;

    /**
     * 访问服务url地址
     */
    private String requestUrl;

    /**
     * 访问者ip
     */
    private String remoteIp;

    /**
     * user agent
     */
    private String userAgent;

    /**
     * HTTP Referer
     */
    private String referer;

    /**
     * 没有加 @VeriryLogin 注解的接口是获取不到的，已校验登录态的用户uid
     */
    private Long userId;

    /**
     * 原始token
     */
    private String originToken;
}
