package com.axzhengxin.service;

import java.util.List;

import com.axzhengxin.dto.UserInfo;

public interface IUserInfoService {
	public UserInfo getUserInfoById(int id);
	
	public void inertUserInfo(UserInfo userInfo);
	
	public int getUserNum();

	public UserInfo getUserInfoByName(String nickname);

	public void updateByPrimaryKeySelective(UserInfo userInfo);
	
	public List<UserInfo> selectAllUser();
}
