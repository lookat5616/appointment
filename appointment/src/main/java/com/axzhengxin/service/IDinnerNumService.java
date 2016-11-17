package com.axzhengxin.service;

import java.util.Date;

import com.axzhengxin.dto.DinnerNum;

public interface IDinnerNumService {
	public DinnerNum getDinnerNum();
	
	public void insertSelective(DinnerNum dinnerNum);

	public void update(DinnerNum dinnerNumObj);
	
}
