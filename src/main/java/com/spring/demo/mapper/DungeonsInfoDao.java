/**
 * <p> Copyright (c) 2015-2025 微聚未来</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

package com.spring.demo.mapper;

import com.spring.demo.po.DungeonsInfo;
import com.spring.demo.query.DungeonsInfoQuery;

import java.util.List;


public interface DungeonsInfoDao {

    /**
     * 插入一行数据，如果字段为null，则不处理
     *
     * @param dungeonsInfo dungeonsInfo
     * @return count
     */
    int saveSelective(DungeonsInfo dungeonsInfo);

    /**
     * 根据主键更新一行数据，如果字段为null，则不处理
     *
     * @param dungeonsInfo dungeonsInfo
     * @return count
     */
    int updateByPrimaryKeySelective(DungeonsInfo dungeonsInfo);

    /**
     * 根据主键查询一条数据
     *
     * @param id id
     * @return po
     */
    DungeonsInfo getByPrimaryKey(Long id);

    /**
     * 根据条件查询多条数据
     *
     * @param dungeonsInfoQuery dungeonsInfoQuery
     * @return po list
     */
    List<DungeonsInfo> getListByCondition(DungeonsInfoQuery dungeonsInfoQuery);
}