package com.spring.demo.intercepters;

import com.spring.demo.header.Header;
import com.spring.demo.token.TokenCheckResult;
import com.spring.demo.utils.AuthToken;
import org.apache.commons.lang3.StringUtils;

public final class TokenHeaderConvert {

    private TokenHeaderConvert() {
    }

    /**
     * 重置和补充header中的属性值，以token为准
     */
    public static Header supplementHeader(Header header, TokenCheckResult checkResult) {
        AuthToken authToken = checkResult.getToken();
        if (null == authToken) {
            return header;
        }
        boolean checkParams = StringUtils.isBlank(authToken.getUserId());
        if (checkParams) {
            return header;
        }
        try {
            return header.toBuilder()
                    .userId(StringUtils.isEmpty(authToken.getUserId()) ? null : Long.parseLong(authToken.getUserId()))
                    .build();
        } catch (Exception e) {
            return header;
        }
    }
}
