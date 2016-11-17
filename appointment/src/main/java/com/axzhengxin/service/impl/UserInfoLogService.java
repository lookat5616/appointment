package com.axzhengxin.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.axzhengxin.dao.UserInfoLogMapper;
import com.axzhengxin.dao.UserInfoMapper;
import com.axzhengxin.dto.UserInfo;
import com.axzhengxin.dto.UserInfoLog;
import com.axzhengxin.service.IUserInfoLogService;

@Service("userInfoLogService")
public class UserInfoLogService implements IUserInfoLogService {
	@Resource
	private UserInfoLogMapper userInfoLogMapper;


	@Override
	public void inertUserInfoLog(UserInfoLog userInfoLog) {
		// TODO Auto-generated method stub
		Date date = new Date();
		
		String id = Long.toString(date.getTime());
		userInfoLog.setId(id);
		userInfoLog.setDate(date);
		userInfoLogMapper.insertSelective(userInfoLog);
		
	}


}
