/**    
 * <p> Copyright (c) 2015-2025 微聚未来</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

package com.spring.demo.po;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;


@Data
public class BossCalculateRecord {

    /** id */
    private Long id;

    /** bookmaker */
    private String bookmaker;

    /** activityId */
    private String activityId;

    /** bossId */
    private Integer bossId;

    /** goodsId */
    private Integer goodsId;

    /** createTime */
    private Date createTime;

    /** updateTime */
    private Date updateTime;

    /** delFlag */
    private Integer delFlag;

}
