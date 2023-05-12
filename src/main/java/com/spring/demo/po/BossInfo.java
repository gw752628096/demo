/**    
 * <p> Copyright (c) 2015-2025 微聚未来</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

package com.spring.demo.po;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;


@Data
public class BossInfo {

    /** 主键 */
    private Long id;

    /** BOSS名 */
    private String bossName;

    /** 副本ID */
    private Long dungeonsId;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    /** 逻辑删除标识 */
    private Integer delFlag;

}
