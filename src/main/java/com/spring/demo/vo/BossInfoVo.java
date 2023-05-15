/**
 * <p> Copyright (c) 2015-2025 微聚未来</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

package com.spring.demo.vo;

import com.spring.demo.po.GoodsInfo;
import lombok.Data;

import java.util.List;


@Data
public class BossInfoVo {

    /** 主键 */
    private Long id;

    /** BOSS名 */
    private String bossName;

    /** 副本ID */
    private Long dungeonsId;

    private List<GoodsInfo> goodsList;
}
