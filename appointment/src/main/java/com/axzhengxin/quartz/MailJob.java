/**
 * 
 */
/**
 * @author look
 *
 */
package com.axzhengxin.quartz;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.axzhengxin.dto.UserInfo;
import com.axzhengxin.mail.SendEmailUtils;
import com.axzhengxin.service.IDinnerInfoService;
import com.axzhengxin.service.ISendMailService;
import com.axzhengxin.service.IUserInfoService;



public class MailJob {  
	@Resource  
    private IUserInfoService  userInfoService;
	@Resource  
    private IDinnerInfoService  dinnerInfoService;
	@Resource  
    private ISendMailService  sendMailService;
	
    public void sendDinnerMail() {  
        //List<UserInfo> userInfoList = userInfoService.selectAllUser();
        /*
        //StringBuffer strHtml = new StringBuffer();     
        for (UserInfo userInfo : userInfoList) {
        	StringBuffer strHtml = new StringBuffer();
        	strHtml.append(userInfo.getNickname());
        	strHtml.append(", 您好： </br>");
        	strHtml.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"http://36.7.134.37:8088/appointment/dinner/showDinnerInfo?id="+userInfo.getId()+"\">午餐就餐登记,请于上午10:30前点击登记!</a>");
        	SendEmailUtils.send(userInfo.getMail(), "午餐就餐登记", strHtml.toString());
        	
		}
		*/
        sendMailService.sendMailDinnner();
    }  
    /**
     * 就餐登记数量邮件发送
     */
    public void sendDinnerNumMail() {  
    	sendMailService.sendMailDinnnerNum();
    	/*
        int dinnerNum = dinnerInfoService.getDinnerNum();
        int userNum = userInfoService.getUserNum();
        StringBuffer strHtml = new StringBuffer();
    	strHtml.append("张富");
    	strHtml.append(", 您好： </br>");
    	strHtml.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;截止当前公司总人数："+userNum+"人  午餐就餐登记人数:"+dinnerNum+"人。");
    	SendEmailUtils.send("zhangfu@axzhengxin.com", "截止当前公司总人数:"+userNum+"人  午餐就餐登记人数"+dinnerNum+"人", strHtml.toString());
		*/	
    }
}