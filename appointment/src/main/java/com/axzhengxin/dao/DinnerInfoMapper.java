package com.axzhengxin.dao;

import java.util.List;

import com.axzhengxin.dto.DinnerInfo;

public interface DinnerInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(DinnerInfo record);

    int insertSelective(DinnerInfo record);

    DinnerInfo selectByPrimaryKey(String id);
    
    DinnerInfo seelctByName(DinnerInfo dinnerInfo);

    int updateByPrimaryKeySelective(DinnerInfo record);

    int updateByPrimaryKey(DinnerInfo record);

	DinnerInfo selectByName(String name);

	DinnerInfo selectByUserId(String userId);

	Integer selectDinnerNum();

	List<DinnerInfo> selectAllDinner();
}