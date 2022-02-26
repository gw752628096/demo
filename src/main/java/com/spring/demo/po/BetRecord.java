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
public class BetRecord {

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

    /** money */
    private String calculateMoney;

    /** createTime */
    private Date createTime;

    /** delFlag */
    private Integer delFlag;

}
