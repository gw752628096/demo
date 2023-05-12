/**    
 * <p> Copyright (c) 2015-2025 微聚未来</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

package com.spring.demo.mapper;

import java.util.List;
import com.spring.demo.po.BossBetConfig;
import com.spring.demo.query.BossBetConfigQuery;


public interface BossBetConfigDao {

    /**
     * 插入一行数据，如果字段为null，则不处理
     *
     * @param bossBetConfig bossBetConfig
     * @return count
     */
    int saveSelective(BossBetConfig bossBetConfig);

    /**
     * 根据主键更新一行数据，如果字段为null，则不处理
     *
     * @param bossBetConfig bossBetConfig
     * @return count
     */
    int updateByPrimaryKeySelective(BossBetConfig bossBetConfig);

    /**
     * 根据主键查询一条数据
     *
     * @param id id
     * @return po
     */
    BossBetConfig getByPrimaryKey(Long id);

    /**
     * 根据条件查询多条数据
     *
     * @param bossBetConfigQuery bossBetConfigQuery
     * @return po list
     */
    List<BossBetConfig> getListByCondition(BossBetConfigQuery bossBetConfigQuery);
}