/**    
 * <p> Copyright (c) 2015-2025 微聚未来</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

package com.spring.demo.query;

import lombok.Data;
import lombok.ToString;
import java.math.BigDecimal;


@Data
public class DungeonsInfoQuery {
    /** 主键 */
    private Long id;

    /** 下注人的ID */
    private String dungeonsName;

    /** 副本人数 */
    private Integer num;

    /** 副本难度 NORMAL/HERO */
    private String type;

    /** 创建时间 */
    private java.util.Date createTimeBegin;

    private java.util.Date createTimeEnd;

    /** 更新时间 */
    private java.util.Date updateTimeBegin;

    private java.util.Date updateTimeEnd;

    /** 逻辑删除标识 */
    private Integer delFlag;

}

