/**    
 * <p> Copyright (c) 2015-2025 微聚未来</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

package com.spring.demo.mapper;

import java.util.List;
import com.spring.demo.po.BossInfo;
import com.spring.demo.query.BossInfoQuery;


public interface BossInfoDao {

    /**
     * 插入一行数据，如果字段为null，则不处理
     *
     * @param bossInfo bossInfo
     * @return count
     */
    int saveSelective(BossInfo bossInfo);

    /**
     * 根据主键更新一行数据，如果字段为null，则不处理
     *
     * @param bossInfo bossInfo
     * @return count
     */
    int updateByPrimaryKeySelective(BossInfo bossInfo);

    /**
     * 根据主键查询一条数据
     *
     * @param id id
     * @return po
     */
    BossInfo getByPrimaryKey(Long id);

    /**
     * 根据条件查询多条数据
     *
     * @param bossInfoQuery bossInfoQuery
     * @return po list
     */
    List<BossInfo> getListByCondition(BossInfoQuery bossInfoQuery);
}