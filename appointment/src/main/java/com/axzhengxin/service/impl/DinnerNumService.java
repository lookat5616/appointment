package com.axzhengxin.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.axzhengxin.dao.DinnerNumMapper;
import com.axzhengxin.dto.DinnerNum;
import com.axzhengxin.service.IDinnerNumService;

@Service("dinnerNumService")
public class DinnerNumService implements IDinnerNumService {
	@Resource
	private DinnerNumMapper dinnerNumMapper;

	@Override
	public DinnerNum getDinnerNum() {
		// TODO Auto-generated method stub
		DinnerNum dinnerNum = dinnerNumMapper.getDinnerNum();
		return dinnerNum;
	}

	@Override
	public void insertSelective(DinnerNum dinnerNum) {
		// TODO Auto-generated method stub
		Date dbDate = dinnerNumMapper.getDbDate();
		dinnerNum.setDate(dbDate);
		dinnerNumMapper.insertSelective(dinnerNum);
	}

	@Override
	public void update(DinnerNum dinnerNumObj) {
		// TODO Auto-generated method stub
		dinnerNumMapper.updateByPrimaryKeySelective(dinnerNumObj);
	}

}
