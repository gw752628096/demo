/**    
 * <p> Copyright (c) 2015-2025 微聚未来</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

package com.spring.demo.service;

import java.util.List;
import com.spring.demo.po.BookmakerRecord;
import com.spring.demo.query.BookmakerRecordQuery;
import org.springframework.stereotype.Service;
import com.spring.demo.mapper.BookmakerRecordDao;
import javax.annotation.Resource;

/**
 * @author Ge Hui
 */
@Service
public class BookmakerRecordService {

    @Resource
    private BookmakerRecordDao bookmakerRecordDao;

    /**
     * 插入一行数据，如果字段为null，则不处理
     *
     * @param bookmakerRecord bookmakerRecord
     * @return count
     */
    public int saveSelective(BookmakerRecord bookmakerRecord) {
        return bookmakerRecordDao.saveSelective(bookmakerRecord);
    }

    /**
     * 根据主键更新一行数据，如果字段为null，则不处理
     *
     * @param bookmakerRecord bookmakerRecord
     * @return count
     */
    public int updateByPrimaryKeySelective(BookmakerRecord bookmakerRecord) {
        return bookmakerRecordDao.updateByPrimaryKeySelective(bookmakerRecord);
    }

    /**
     * 根据主键查询一条数据
     *
     * @param id id
     * @return po
     */
    public BookmakerRecord getByPrimaryKey(Long id) {
        return bookmakerRecordDao.getByPrimaryKey(id);
    }

    /**
     * 根据条件查询多条数据
     *
     * @param bookmakerRecordQuery bookmakerRecordQuery
     * @return po list
     */
    public List<BookmakerRecord> getListByCondition(BookmakerRecordQuery bookmakerRecordQuery) {
        return bookmakerRecordDao.getListByCondition(bookmakerRecordQuery);
    }
}
