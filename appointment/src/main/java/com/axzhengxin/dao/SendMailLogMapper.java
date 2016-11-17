package com.axzhengxin.dao;

import com.axzhengxin.dto.SendMailLog;

public interface SendMailLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SendMailLog record);

    int insertSelective(SendMailLog record);

    SendMailLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SendMailLog record);

    int updateByPrimaryKey(SendMailLog record);
}