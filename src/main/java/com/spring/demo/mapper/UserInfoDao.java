/**
 * <p> Copyright (c) 2015-2025 微聚未来</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

package com.spring.demo.mapper;

import com.spring.demo.po.UserInfo;
import com.spring.demo.query.UserInfoQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Ge Hui
 */
@Mapper
public interface UserInfoDao {

    /**
     * 插入一行数据，如果字段为null，则不处理
     *
     * @param userInfo userInfo
     * @return count
     */
    int saveSelective(UserInfo userInfo);

    /**
     * 根据主键更新一行数据，如果字段为null，则不处理
     *
     * @param userInfo userInfo
     * @return count
     */
    int updateByPrimaryKeySelective(UserInfo userInfo);

    /**
     * 根据主键查询一条数据
     *
     * @param id id
     * @return po
     */
    UserInfo getByPrimaryKey(Long id);

    /**
     * 根据条件查询多条数据
     *
     * @param userInfoQuery userInfoQuery
     * @return po list
     */
    List<UserInfo> getListByCondition(UserInfoQuery userInfoQuery);

    /**
     * 根据用户名查询登录信息
     *
     * @param loginValue
     * @return
     */
    UserInfo getByLoginValue(String loginValue);

    /**
     * 根据昵称查询登录信息
     *
     * @param nickName
     * @return
     */
    UserInfo getByNickName(String nickName);
}