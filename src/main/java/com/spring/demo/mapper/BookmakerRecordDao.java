/**    
 * <p> Copyright (c) 2015-2025 微聚未来</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

package com.spring.demo.mapper;

import java.util.List;
import com.spring.demo.po.BookmakerRecord;
import com.spring.demo.query.BookmakerRecordQuery;

/**
 * @author Ge Hui
 */
public interface BookmakerRecordDao {

    /**
     * 插入一行数据，如果字段为null，则不处理
     *
     * @param bookmakerRecord bookmakerRecord
     * @return count
     */
    int saveSelective(BookmakerRecord bookmakerRecord);

    /**
     * 根据主键更新一行数据，如果字段为null，则不处理
     *
     * @param bookmakerRecord bookmakerRecord
     * @return count
     */
    int updateByPrimaryKeySelective(BookmakerRecord bookmakerRecord);

    /**
     * 根据主键查询一条数据
     *
     * @param id id
     * @return po
     */
    BookmakerRecord getByPrimaryKey(Long id);

    /**
     * 根据条件查询多条数据
     *
     * @param bookmakerRecordQuery bookmakerRecordQuery
     * @return po list
     */
    List<BookmakerRecord> getListByCondition(BookmakerRecordQuery bookmakerRecordQuery);
}