package com.axzhengxin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.axzhengxin.dao.DinnerInfoMapper;
import com.axzhengxin.dto.DinnerInfo;
import com.axzhengxin.service.IDinnerInfoService;

@Service("dinnerInfoService")
public class DinnerInfoService implements IDinnerInfoService {
	@Resource
	private DinnerInfoMapper dinnerInfoMapper;
	@Override
	public DinnerInfo getDinnerInfo(String userid) {
		// TODO Auto-generated method stub
	    DinnerInfo dinnerInfo	= dinnerInfoMapper.selectByUserId(userid);
		return dinnerInfo;
	}
	@Override
	public void inert(DinnerInfo dinnerInfo) {
		// TODO Auto-generated method stub
		dinnerInfoMapper.insertSelective(dinnerInfo);
		
	}
	
	@Override
	public int getDinnerNum() {
		// TODO Auto-generated method stub
		Integer dinnerNum = dinnerInfoMapper.selectDinnerNum();
		return dinnerNum.intValue();
	}
	
	@Override
	public List<DinnerInfo> getAllDinnerInfo() {
		// TODO Auto-generated method stub
		List<DinnerInfo> dinnerList = dinnerInfoMapper.selectAllDinner();
		return dinnerList;
	}

}
