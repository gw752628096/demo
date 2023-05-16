/**    
 * <p> Copyright (c) 2015-2025 微聚未来</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

package com.spring.demo.service;

import java.util.List;
import com.spring.demo.po.BossBetConfig;
import com.spring.demo.query.BossBetConfigQuery;
import org.springframework.stereotype.Service;
import com.spring.demo.mapper.BossBetConfigDao;
import javax.annotation.Resource;

/**
 * @author Ge Hui
 */
@Service
public class BossBetConfigService {

    @Resource
    private BossBetConfigDao bossBetConfigDao;

    /**
     * 插入一行数据，如果字段为null，则不处理
     *
     * @param bossBetConfig bossBetConfig
     * @return count
     */
    public int saveSelective(BossBetConfig bossBetConfig) {
        return bossBetConfigDao.saveSelective(bossBetConfig);
    }

    /**
     * 根据主键更新一行数据，如果字段为null，则不处理
     *
     * @param bossBetConfig bossBetConfig
     * @return count
     */
    public int updateByPrimaryKeySelective(BossBetConfig bossBetConfig) {
        return bossBetConfigDao.updateByPrimaryKeySelective(bossBetConfig);
    }

    /**
     * 根据主键查询一条数据
     *
     * @param id id
     * @return po
     */
    public BossBetConfig getByPrimaryKey(Long id) {
        return bossBetConfigDao.getByPrimaryKey(id);
    }

    /**
     * 根据条件查询多条数据
     *
     * @param bossBetConfigQuery bossBetConfigQuery
     * @return po list
     */
    public List<BossBetConfig> getListByCondition(BossBetConfigQuery bossBetConfigQuery) {
        return bossBetConfigDao.getListByCondition(bossBetConfigQuery);
    }
}
