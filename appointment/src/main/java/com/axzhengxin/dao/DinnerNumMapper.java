package com.axzhengxin.dao;

import java.util.Date;

import com.axzhengxin.dto.DinnerNum;

public interface DinnerNumMapper {
    int deleteByPrimaryKey(String id);

    int insert(DinnerNum record);

    int insertSelective(DinnerNum record);

    DinnerNum selectByPrimaryKey(String id);
    
    DinnerNum getDinnerNum();
    
    Date getDbDate();

    int updateByPrimaryKeySelective(DinnerNum record);

    int updateByPrimaryKey(DinnerNum record);
}