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
public class BetRecordQuery {
    /** id */
    private Long id;

    /** userId */
    private Long userId;

    /** bookmaker */
    private String bookmaker;

    /** activityId */
    private String activityId;

    /** bossId */
    private Integer bossId;

    /** goodsId */
    private Integer goodsId;

    /** money */
    private String money;

    /** calculateMoney */
    private String calculateMoney;

    /** createTime */
    private java.util.Date createTimeBegin;

    private java.util.Date createTimeEnd;

    /** updateTime */
    private java.util.Date updateTimeBegin;

    private java.util.Date updateTimeEnd;

    /** delFlag */
    private Integer delFlag;
}

