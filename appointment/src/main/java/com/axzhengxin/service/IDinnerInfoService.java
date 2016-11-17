package com.axzhengxin.service;

import java.util.List;

import com.axzhengxin.dto.DinnerInfo;
import com.axzhengxin.dto.UserInfo;

public interface IDinnerInfoService {
	
	public DinnerInfo getDinnerInfo(String userid);

	public void inert(DinnerInfo dinnerInfo);

	int getDinnerNum();

	List<DinnerInfo> getAllDinnerInfo();

}
