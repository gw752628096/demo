/**
 * <p> Copyright (c) 2015-2025 微聚未来</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

package com.spring.demo.service;

import com.spring.demo.mapper.BetRecordDao;
import com.spring.demo.po.BetRecord;
import com.spring.demo.query.BetRecordQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ge Hui
 */
@Service
public class BetRecordService {

    @Resource
    private BetRecordDao betRecordDao;

    /**
     * 插入一行数据，如果字段为null，则不处理
     *
     * @param betRecord betRecord
     * @return count
     */
    public int saveSelective(BetRecord betRecord) {
        return betRecordDao.saveSelective(betRecord);
    }

    /**
     * 根据主键更新一行数据，如果字段为null，则不处理
     *
     * @param betRecord betRecord
     * @return count
     */
    public int updateByPrimaryKeySelective(BetRecord betRecord) {
        return betRecordDao.updateByPrimaryKeySelective(betRecord);
    }

    /**
     * 根据主键查询一条数据
     *
     * @param id id
     * @return po
     */
    public BetRecord getByPrimaryKey(Long id) {
        return betRecordDao.getByPrimaryKey(id);
    }

    /**
     * 根据条件查询多条数据
     *
     * @param betRecordQuery betRecordQuery
     * @return po list
     */
    public List<BetRecord> getListByCondition(BetRecordQuery betRecordQuery) {
        return betRecordDao.getListByCondition(betRecordQuery);
    }
}
