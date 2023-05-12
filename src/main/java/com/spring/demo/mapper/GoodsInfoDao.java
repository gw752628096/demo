/**    
 * <p> Copyright (c) 2015-2025 微聚未来</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

package com.spring.demo.mapper;

import java.util.List;
import com.spring.demo.po.GoodsInfo;
import com.spring.demo.query.GoodsInfoQuery;


public interface GoodsInfoDao {

    /**
     * 插入一行数据，如果字段为null，则不处理
     *
     * @param goodsInfo goodsInfo
     * @return count
     */
    int saveSelective(GoodsInfo goodsInfo);

    /**
     * 根据主键更新一行数据，如果字段为null，则不处理
     *
     * @param goodsInfo goodsInfo
     * @return count
     */
    int updateByPrimaryKeySelective(GoodsInfo goodsInfo);

    /**
     * 根据主键查询一条数据
     *
     * @param id id
     * @return po
     */
    GoodsInfo getByPrimaryKey(Long id);

    /**
     * 根据条件查询多条数据
     *
     * @param goodsInfoQuery goodsInfoQuery
     * @return po list
     */
    List<GoodsInfo> getListByCondition(GoodsInfoQuery goodsInfoQuery);
}