/**    
 * <p> Copyright (c) 2015-2025 微聚未来</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

package com.spring.demo.service;

import java.util.List;
import com.spring.demo.po.BossCalculateRecord;
import com.spring.demo.query.BossCalculateRecordQuery;
import org.springframework.stereotype.Service;
import com.spring.demo.mapper.BossCalculateRecordDao;
import javax.annotation.Resource;

/**
 * @author Ge Hui
 */
@Service
public class BossCalculateRecordService {

    @Resource
    private BossCalculateRecordDao bossCalculateRecordDao;

    /**
     * 插入一行数据，如果字段为null，则不处理
     *
     * @param bossCalculateRecord bossCalculateRecord
     * @return count
     */
    public int saveSelective(BossCalculateRecord bossCalculateRecord) {
        return bossCalculateRecordDao.saveSelective(bossCalculateRecord);
    }

    /**
     * 根据主键更新一行数据，如果字段为null，则不处理
     *
     * @param bossCalculateRecord bossCalculateRecord
     * @return count
     */
    public int updateByPrimaryKeySelective(BossCalculateRecord bossCalculateRecord) {
        return bossCalculateRecordDao.updateByPrimaryKeySelective(bossCalculateRecord);
    }

    /**
     * 根据主键查询一条数据
     *
     * @param id id
     * @return po
     */
    public BossCalculateRecord getByPrimaryKey(Long id) {
        return bossCalculateRecordDao.getByPrimaryKey(id);
    }

    /**
     * 根据条件查询多条数据
     *
     * @param bossCalculateRecordQuery bossCalculateRecordQuery
     * @return po list
     */
    public List<BossCalculateRecord> getListByCondition(BossCalculateRecordQuery bossCalculateRecordQuery) {
        return bossCalculateRecordDao.getListByCondition(bossCalculateRecordQuery);
    }
}
