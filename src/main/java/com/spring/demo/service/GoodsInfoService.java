/**    
 * <p> Copyright (c) 2015-2025 微聚未来</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

package com.spring.demo.service;

import java.util.List;
import com.spring.demo.po.GoodsInfo;
import com.spring.demo.query.GoodsInfoQuery;
import org.springframework.stereotype.Service;
import com.spring.demo.mapper.GoodsInfoDao;
import javax.annotation.Resource;


@Service
public class GoodsInfoService {

    @Resource
    private GoodsInfoDao goodsInfoDao;

    /**
     * 插入一行数据，如果字段为null，则不处理
     *
     * @param goodsInfo goodsInfo
     * @return count
     */
    public int saveSelective(GoodsInfo goodsInfo) {
        return goodsInfoDao.saveSelective(goodsInfo);
    }

    /**
     * 根据主键更新一行数据，如果字段为null，则不处理
     *
     * @param goodsInfo goodsInfo
     * @return count
     */
    public int updateByPrimaryKeySelective(GoodsInfo goodsInfo) {
        return goodsInfoDao.updateByPrimaryKeySelective(goodsInfo);
    }

    /**
     * 根据主键查询一条数据
     *
     * @param id id
     * @return po
     */
    public GoodsInfo getByPrimaryKey(Long id) {
        return goodsInfoDao.getByPrimaryKey(id);
    }

    /**
     * 根据条件查询多条数据
     *
     * @param goodsInfoQuery goodsInfoQuery
     * @return po list
     */
    public List<GoodsInfo> getListByCondition(GoodsInfoQuery goodsInfoQuery) {
        return goodsInfoDao.getListByCondition(goodsInfoQuery);
    }
}
