/**    
 * <p> Copyright (c) 2015-2025 微聚未来</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

package com.spring.demo.service;

import java.util.List;
import com.spring.demo.po.BossInfo;
import com.spring.demo.query.BossInfoQuery;
import org.springframework.stereotype.Service;
import com.spring.demo.mapper.BossInfoDao;
import javax.annotation.Resource;


@Service
public class BossInfoService {

    @Resource
    private BossInfoDao bossInfoDao;

    /**
     * 插入一行数据，如果字段为null，则不处理
     *
     * @param bossInfo bossInfo
     * @return count
     */
    public int saveSelective(BossInfo bossInfo) {
        return bossInfoDao.saveSelective(bossInfo);
    }

    /**
     * 根据主键更新一行数据，如果字段为null，则不处理
     *
     * @param bossInfo bossInfo
     * @return count
     */
    public int updateByPrimaryKeySelective(BossInfo bossInfo) {
        return bossInfoDao.updateByPrimaryKeySelective(bossInfo);
    }

    /**
     * 根据主键查询一条数据
     *
     * @param id id
     * @return po
     */
    public BossInfo getByPrimaryKey(Long id) {
        return bossInfoDao.getByPrimaryKey(id);
    }

    /**
     * 根据条件查询多条数据
     *
     * @param bossInfoQuery bossInfoQuery
     * @return po list
     */
    public List<BossInfo> getListByCondition(BossInfoQuery bossInfoQuery) {
        return bossInfoDao.getListByCondition(bossInfoQuery);
    }
}
