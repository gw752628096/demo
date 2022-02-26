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
public class BossCalculateRecordQuery {
    /** id */
    private Long id;

    /** bookmaker */
    private String bookmaker;

    /** activityId */
    private String activityId;

    /** bossId */
    private Integer bossId;

    /** one */
    private Long one;

    /** two */
    private Long two;

    /** three */
    private Long three;

    /** createTime */
    private java.util.Date createTimeBegin;

    private java.util.Date createTimeEnd;

    /** delFlag */
    private Integer delFlag;

}

