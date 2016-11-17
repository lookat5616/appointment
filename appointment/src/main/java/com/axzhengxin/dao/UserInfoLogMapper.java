package com.axzhengxin.dao;

import com.axzhengxin.dto.UserInfoLog;

public interface UserInfoLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserInfoLog record);

    int insertSelective(UserInfoLog record);

    UserInfoLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserInfoLog record);

    int updateByPrimaryKey(UserInfoLog record);
}