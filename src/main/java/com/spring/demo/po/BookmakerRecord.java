/**    
 * <p> Copyright (c) 2015-2025 微聚未来</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

package com.spring.demo.po;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Ge Hui
 */
@Data
public class BookmakerRecord {

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
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    /** 逻辑删除标识 */
    private Integer delFlag;

}
