/**
 * <p> Copyright (c) 2015-2025 微聚未来</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

package com.spring.demo.service;

import com.spring.demo.mapper.UserInfoDao;
import com.spring.demo.po.UserInfo;
import com.spring.demo.query.UserInfoQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ge Hui
 */
@Service
public class UserInfoService {

    @Resource
    private UserInfoDao userInfoDao;

    /**
     * 插入一行数据，如果字段为null，则不处理
     *
     * @param userInfo userInfo
     * @return count
     */
    public int saveSelective(UserInfo userInfo) {
        return userInfoDao.saveSelective(userInfo);
    }

    /**
     * 根据主键更新一行数据，如果字段为null，则不处理
     *
     * @param userInfo userInfo
     * @return count
     */
    public int updateByPrimaryKeySelective(UserInfo userInfo) {
        return userInfoDao.updateByPrimaryKeySelective(userInfo);
    }

    /**
     * 根据主键查询一条数据
     *
     * @param id id
     * @return po
     */
    public UserInfo getByPrimaryKey(Long id) {
        return userInfoDao.getByPrimaryKey(id);
    }

    /**
     * 根据用户名查询登录信息
     *
     * @param loginValue
     * @return
     */
    public UserInfo getByLoginValue(String loginValue) {
        return userInfoDao.getByLoginValue(loginValue);
    }

    /**
     * 根据昵称查询登录信息
     *
     * @param nickName
     * @return
     */
    public UserInfo getByNickName(String nickName) {
        return userInfoDao.getByNickName(nickName);
    }

    /**
     * 根据条件查询多条数据
     *
     * @param userInfoQuery userInfoQuery
     * @return po list
     */
    public List<UserInfo> getListByCondition(UserInfoQuery userInfoQuery) {
        return userInfoDao.getListByCondition(userInfoQuery);
    }
}
