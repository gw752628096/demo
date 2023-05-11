package com.spring.demo.enums;

import org.apache.commons.lang3.StringUtils;

public enum ErrorUrlEnum {
    COMMON("x-access-token", "/check/error.do"),

    ;

    ErrorUrlEnum(String headerName, String url) {
        this.headerName = headerName;
        this.url = url;
    }

    private String headerName;
    private String url;

    public String getHeaderName() {
        return headerName;
    }


    public String getUrl() {
        return url;
    }

    public static String getDefaultErrorUrl() {
        return getErrorUrlByHeaderName(null);
    }

    public static String getErrorUrlByHeaderName(String headerName) {
        if (StringUtils.isBlank(headerName)) {
            return COMMON.getUrl();
        }
        for (ErrorUrlEnum errorUrlEnum : ErrorUrlEnum.values()) {
            if (errorUrlEnum.getHeaderName().equals(headerName)) {
                return errorUrlEnum.getUrl();
            }
        }
        return COMMON.getUrl();
    }

    public static boolean isErrorUrl(String url) {
        for (ErrorUrlEnum errorUrlEnum : ErrorUrlEnum.values()) {
            if (StringUtils.defaultString(url).contains(errorUrlEnum.getUrl())) {
                return true;
            }
        }
        return false;
    }
}
