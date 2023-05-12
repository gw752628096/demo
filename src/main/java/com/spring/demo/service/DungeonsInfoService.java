/**    
 * <p> Copyright (c) 2015-2025 微聚未来</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

package com.spring.demo.service;

import java.util.List;
import com.spring.demo.po.DungeonsInfo;
import com.spring.demo.query.DungeonsInfoQuery;
import org.springframework.stereotype.Service;
import com.spring.demo.mapper.DungeonsInfoDao;
import javax.annotation.Resource;


@Service
public class DungeonsInfoService {

    @Resource
    private DungeonsInfoDao dungeonsInfoDao;

    /**
     * 插入一行数据，如果字段为null，则不处理
     *
     * @param dungeonsInfo dungeonsInfo
     * @return count
     */
    public int saveSelective(DungeonsInfo dungeonsInfo) {
        return dungeonsInfoDao.saveSelective(dungeonsInfo);
    }

    /**
     * 根据主键更新一行数据，如果字段为null，则不处理
     *
     * @param dungeonsInfo dungeonsInfo
     * @return count
     */
    public int updateByPrimaryKeySelective(DungeonsInfo dungeonsInfo) {
        return dungeonsInfoDao.updateByPrimaryKeySelective(dungeonsInfo);
    }

    /**
     * 根据主键查询一条数据
     *
     * @param id id
     * @return po
     */
    public DungeonsInfo getByPrimaryKey(Long id) {
        return dungeonsInfoDao.getByPrimaryKey(id);
    }

    /**
     * 根据条件查询多条数据
     *
     * @param dungeonsInfoQuery dungeonsInfoQuery
     * @return po list
     */
    public List<DungeonsInfo> getListByCondition(DungeonsInfoQuery dungeonsInfoQuery) {
        return dungeonsInfoDao.getListByCondition(dungeonsInfoQuery);
    }
}
