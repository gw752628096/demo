/**    
 * <p> Copyright (c) 2015-2025 微聚未来</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

package com.spring.demo.query;

import lombok.Data;
import lombok.ToString;
import java.math.BigDecimal;

/**
 * @author Ge Hui
 */
@Data
public class BookmakerRecordQuery {
    /** 主键 */
    private Long id;

    /** 庄家ID */
    private Long bookmaker;

    /** 活动ID */
    private String activityId;

    /** 副本ID */
    private Long dungeonsId;

    /** 最大单柱金额 */
    private String maxMoney;

    /** 1:初始化  2:开盘中  3:已结束 */
    private String betStatus;

    /** 创建时间 */
    private java.util.Date createTimeBegin;

    private java.util.Date createTimeEnd;

    /** 更新时间 */
    private java.util.Date updateTimeBegin;

    private java.util.Date updateTimeEnd;

    /** 逻辑删除标识 */
    private Integer delFlag;

}

