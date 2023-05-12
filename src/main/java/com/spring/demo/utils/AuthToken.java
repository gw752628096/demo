package com.spring.demo.utils;

import lombok.Data;
import lombok.ToString;

/**
 * @author wanghaitao
 * @create 2018年06月13日上午10:19:04
 * @desc token对象
 */
@Data
@ToString
public class AuthToken {

    /**
     * 用户userId
     */
    private String userId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 过期时间
     */
    private String exp;
}
