/**
 * <p> Copyright (c) 2015-2025 微聚未来</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

package com.spring.demo.mapper;

import com.spring.demo.po.BetRecord;
import com.spring.demo.query.BetRecordQuery;

import java.util.List;

/**
 * @author Ge Hui
 */
public interface BetRecordDao {

    /**
     * 插入一行数据，如果字段为null，则不处理
     *
     * @param betRecord betRecord
     * @return count
     */
    int saveSelective(BetRecord betRecord);

    /**
     * 根据主键更新一行数据，如果字段为null，则不处理
     *
     * @param betRecord betRecord
     * @return count
     */
    int updateByPrimaryKeySelective(BetRecord betRecord);

    /**
     * 根据主键查询一条数据
     *
     * @param id id
     * @return po
     */
    BetRecord getByPrimaryKey(Long id);

    /**
     * 根据条件查询多条数据
     *
     * @param betRecordQuery betRecordQuery
     * @return po list
     */
    List<BetRecord> getListByCondition(BetRecordQuery betRecordQuery);
}