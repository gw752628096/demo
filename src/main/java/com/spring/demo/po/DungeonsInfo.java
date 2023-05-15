/**    
 * <p> Copyright (c) 2015-2025 微聚未来</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

package com.spring.demo.po;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;


@Data
public class DungeonsInfo {

    /** 主键 */
    private Long id;

    /** 副本名称 */
    private String dungeonsName;

    /** 副本人数 */
    private Integer num;

    /** 副本难度 普通/英雄 */
    private String type;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    /** 逻辑删除标识 */
    private Integer delFlag;

}
