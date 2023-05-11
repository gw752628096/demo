package com.spring.demo.token;

import com.spring.demo.utils.AuthToken;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author wangshuaixin
 * @Title: com.yinke.loan.base.common.TokenCheckResult
 * @Description: token校验结果封装，统一安全的token校验
 * @date 2019/10/10
 */
@Setter
@Getter
@NoArgsConstructor
public class TokenCheckResult {

    private static final int SUCCESS = 0;

    /**
     * 状态描述，主要是无效，被登出等状态
     */
    private int code = SUCCESS;
    /**
     * 如果正常，则返回token对象
     */
    private AuthToken token;
    /**
     * 原生字符串token
     */
    private String originToken;

    public TokenCheckResult(int code) {
        this.code = code;
    }

    /**
     * 是否成功的标示
     *
     * @return
     */
    public boolean success() {
        return code == SUCCESS && null != token;
    }

    public TokenCheckResult token(AuthToken token) {
        this.token = token;
        return this;
    }

    public TokenCheckResult originToken(String originToken) {
        this.originToken = originToken;
        return this;
    }
}
