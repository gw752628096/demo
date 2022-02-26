/**
 * <p> Copyright (c) 2015-2025 微聚未来</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

package com.spring.demo.po;

import lombok.Data;

import java.util.Date;

/**
 * @author Ge Hui
 */
@Data
public class UserInfo {

    /** id */
    private Long id;

    /** 用户名 */
    private String loginValue;

    /** 登录类型 */
    private String nickName;

    /** 密码 */
    private String pd;

    /** 创建时间戳 */
    private Date createTime;

    /** 更新时间戳 */
    private Date updateTime;

    /** 删除标识 */
    private Integer delFlag;

}
