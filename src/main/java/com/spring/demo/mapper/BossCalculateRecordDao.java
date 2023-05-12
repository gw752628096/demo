/**    
 * <p> Copyright (c) 2015-2025 微聚未来</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

package com.spring.demo.mapper;

import java.util.List;
import com.spring.demo.po.BossCalculateRecord;
import com.spring.demo.query.BossCalculateRecordQuery;


public interface BossCalculateRecordDao {

    /**
     * 插入一行数据，如果字段为null，则不处理
     *
     * @param bossCalculateRecord bossCalculateRecord
     * @return count
     */
    int saveSelective(BossCalculateRecord bossCalculateRecord);

    /**
     * 根据主键更新一行数据，如果字段为null，则不处理
     *
     * @param bossCalculateRecord bossCalculateRecord
     * @return count
     */
    int updateByPrimaryKeySelective(BossCalculateRecord bossCalculateRecord);

    /**
     * 根据主键查询一条数据
     *
     * @param id id
     * @return po
     */
    BossCalculateRecord getByPrimaryKey(Long id);

    /**
     * 根据条件查询多条数据
     *
     * @param bossCalculateRecordQuery bossCalculateRecordQuery
     * @return po list
     */
    List<BossCalculateRecord> getListByCondition(BossCalculateRecordQuery bossCalculateRecordQuery);
}