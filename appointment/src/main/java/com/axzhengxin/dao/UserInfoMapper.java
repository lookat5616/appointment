package com.axzhengxin.dao;

import java.util.List;

import com.axzhengxin.dto.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(String openid);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(String openid);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
    
    Integer selectUserNum();

	UserInfo getUserInfoByName(String name);
	
	List<UserInfo> selectAllUser();
	
	UserInfo selectById(int id);
}