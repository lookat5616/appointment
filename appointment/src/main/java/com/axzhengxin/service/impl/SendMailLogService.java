package com.axzhengxin.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.axzhengxin.dao.SendMailLogMapper;
import com.axzhengxin.dto.SendMailLog;
import com.axzhengxin.service.ISendMailLogService;
import com.axzhengxin.service.IUserInfoService;
@Service("sendMailLogService")
public class SendMailLogService implements ISendMailLogService {
	@Resource  
    private SendMailLogMapper sendMailLogMapper;
	@Override
	public void insert(SendMailLog sendMailLog) {
		// TODO Auto-generated method stub
		sendMailLogMapper.insert(sendMailLog);
	}

}
