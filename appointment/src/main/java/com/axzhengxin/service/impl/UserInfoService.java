package com.axzhengxin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.axzhengxin.dao.UserInfoMapper;
import com.axzhengxin.dto.UserInfo;
import com.axzhengxin.service.IUserInfoService;

@Service("userInfoService")
public class UserInfoService implements IUserInfoService {
	@Resource
	private UserInfoMapper userInfoMapper;
	@Override
	public UserInfo getUserInfoById(int id) {
		// TODO Auto-generated method stub
		UserInfo userInfo = userInfoMapper.selectById(id);
		return userInfo;
	}

	@Override
	public void inertUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		userInfoMapper.insert(userInfo);
	}

	@Override
	public int getUserNum() {
		// TODO Auto-generated method stub
		Integer userNum =  userInfoMapper.selectUserNum();
		return userNum.intValue();
	}

	@Override
	public UserInfo getUserInfoByName(String nickname) {
		// TODO Auto-generated method stub
		UserInfo userInfo = userInfoMapper.getUserInfoByName(nickname);
		return userInfo;
	}

	@Override
	public void updateByPrimaryKeySelective(UserInfo userInfo) {
		// TODO Auto-generated method stub
		userInfoMapper.updateByPrimaryKeySelective(userInfo);
	}

	@Override
	public List<UserInfo> selectAllUser() {
		// TODO Auto-generated method stub
		List<UserInfo> userInfoList = (List<UserInfo>) userInfoMapper.selectAllUser();
		return userInfoList;
	}



}
