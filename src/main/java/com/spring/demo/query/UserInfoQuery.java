/**
 * <p> Copyright (c) 2015-2025 微聚未来</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

package com.spring.demo.query;

import lombok.Data;

/**
 * @author Ge Hui
 */
@Data
public class UserInfoQuery {
    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String loginValue;

    /**
     * 登录类型
     */
    private String nickName;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间戳
     */
    private java.util.Date createTimeBegin;

    private java.util.Date createTimeEnd;

    /**
     * 更新时间戳
     */
    private java.util.Date updateTimeBegin;

    private java.util.Date updateTimeEnd;

    /**
     * 删除标识
     */
    private Integer delFlag;

}

