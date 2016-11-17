package com.axzhengxin.service.impl;



import com.axzhengxin.dao.UserInfoMapper;
import com.axzhengxin.dto.DinnerInfo;
import com.axzhengxin.dto.SendMailLog;
import com.axzhengxin.dto.UserInfo;
import com.axzhengxin.mail.SendEmailUtils;
import com.axzhengxin.service.IDinnerInfoService;
import com.axzhengxin.service.ISendMailService;
import com.axzhengxin.service.IUserInfoService;
import com.axzhengxin.util.UtilTool;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;
@Service("sendMailService")
public class SendMailService  implements ISendMailService{
	@Resource  
    private IUserInfoService  userInfoService;
	@Resource  
    private IDinnerInfoService  dinnerInfoService;
	@Resource  
    private SendMailLogService  sendMailLogService;
	
	
	@Override
	public void sendMailDinnner() {
		// TODO Auto-generated method stub
		//获取用户
		List<UserInfo> userInfoList = userInfoService.selectAllUser();
		//获取session
		Session session = SendEmailUtils.createSession();
		session.setDebug(true);
		Properties prop = SendEmailUtils.loadProp();
		String serverId = prop.getProperty("serverId","");
		String fromMail = prop.getProperty("from","");
		//获取Transport
		Transport transport = SendEmailUtils.createTransport(session);
		String nowDate = UtilTool.dateToStr(new Date(), "MM月dd日");
		String subject = nowDate+"午餐就餐登记,  如不就餐请忽略!";
		MimeMessage mimeMessage = null;
		for (UserInfo userInfo : userInfoList) {
			String dinnerUrl = serverId+"/appointment/dinner/showDinnerInfo?id="+userInfo.getId();
        	StringBuffer strHtml = new StringBuffer();
        	strHtml.append(userInfo.getNickname());
        	strHtml.append(", 您好： </br>");
        	strHtml.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;午餐就餐登记,请于上午10:30前点击：<a href=\""+dinnerUrl+"\">登记</a>  进行登记！<br/>");
        	strHtml.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style='color:red;'>注：邮件为系统自动发送，请勿回复！</span>");
        	//创建MimeMessage
        	try {
				mimeMessage = SendEmailUtils.createMimeMessage(session,subject,strHtml.toString(),userInfo.getMail());
				transport.send(mimeMessage);
				inertSendMailLog(fromMail, userInfo, dinnerUrl);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//关闭资源
		SendEmailUtils.close(transport);
	}

	private void inertSendMailLog(String fromMail, UserInfo userInfo, String dinnerUrl) {
		SendMailLog sendMailLog = new SendMailLog();
		sendMailLog.setSandName(userInfo.getRealname());
		sendMailLog.setSandMail(userInfo.getMail());
		sendMailLog.setSendUrl(dinnerUrl);
		sendMailLog.setFromMail(fromMail);
		sendMailLog.setSendTime(new Date());
		sendMailLogService.insert(sendMailLog);
	}

	@Override
	public void sendMailDinnnerNum() {
		// TODO Auto-generated method stub
		//获取session
		Session session = SendEmailUtils.createSession();
		session.setDebug(true);
		Properties prop = SendEmailUtils.loadProp();
		String toStr = prop.getProperty("to","");
		String toNameStr = prop.getProperty("toName","");
		String fromMail = prop.getProperty("from","");
		//获取Transport
		Transport transport = SendEmailUtils.createTransport(session);
		int dinnerNum = dinnerInfoService.getDinnerNum();
        int userNum = userInfoService.getUserNum();
        String nowDate = UtilTool.dateToStr(new Date(), "MM月dd日");
        String subject =nowDate+" 截止当前公司总人数："+userNum+"人  午餐就餐登记人数:"+dinnerNum+"人,详情见邮件。";
        List<Map> countDinnerList = allUserDinnerInfo();
        //对countDinnerList进行排序，没有登记方在上面，已登记放在下面
        countDinnerList = sortDinnerList(countDinnerList);
        //拼接就餐登记表格字符串
        String dinnerInfoTable = dinnerInfoTable(countDinnerList);
        
        String[] toArr = toStr.split(",");
        String[] toNameArr = toNameStr.split(",");
        
        for (int i=0 ; i <  toArr.length ;i++) {
	        StringBuffer strHtml = new StringBuffer();
	    	strHtml.append(toNameArr[i]);
	    	strHtml.append(", 您好： </br>");
	    	strHtml.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+nowDate+"  截止当前公司总人数："+userNum+"人  午餐就餐登记人数:"+dinnerNum+"人。");
	    	strHtml.append("</br>");
	    	strHtml.append(dinnerInfoTable);
	        
	    	//SendEmailUtils.send("zhangfu@axzhengxin.com", "截止当前公司总人数:"+userNum+"人  午餐就餐登记人数"+dinnerNum+"人", strHtml.toString());
	    	MimeMessage mimeMessage;
			try {
				mimeMessage = SendEmailUtils.createMimeMessage(session,subject,strHtml.toString(),toArr[i]);
				transport.send(mimeMessage);
				UserInfo userInfo = new UserInfo();
				userInfo.setRealname(toNameArr[i]);
				userInfo.setMail(toArr[i]);
				inertSendMailLog(fromMail, userInfo, subject);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
    	SendEmailUtils.close(transport);
	}

	private List<Map> sortDinnerList(List<Map> countDinnerList) {
		// TODO Auto-generated method stub
		//未经登记List
		List<Map> unrecordedList = new ArrayList();
		//已登记ListrecordedList
		List<Map> recordedList = new ArrayList();
		List<Map> list = new ArrayList();
		for (Map map : countDinnerList) {
			if (!"".equals(map.get("date"))) {
				recordedList.add(map);
			} else {
				unrecordedList.add(map);   	
			}
		}
		list.addAll(unrecordedList);
		list.addAll(recordedList);
		return list;
	}

	private String dinnerInfoTable(List<Map> allUserDinnerInfo) {
		// TODO Auto-generated method stub
		StringBuffer tableStr = new StringBuffer();
		tableStr.append("<table border='1'cellspacing='0' cellpadding='0' >");
		tableStr.append("<tr>");
		tableStr.append("<td>姓名</td>");
		tableStr.append("<td>邮箱</td>");
		tableStr.append("<td>是否登记</td>");
		tableStr.append("<td>登记时间</td>");
		tableStr.append("</tr>");
		for (Map<String,String> countDinnerMap : allUserDinnerInfo) {  
			tableStr.append("<tr>");
			tableStr.append("<td>");
			tableStr.append(countDinnerMap.get("name"));
			tableStr.append("</td>");
			tableStr.append("<td>");
			tableStr.append(countDinnerMap.get("mail"));
			tableStr.append("</td>");
			tableStr.append("<td>");
			tableStr.append(countDinnerMap.get("isCount"));
			tableStr.append("</td>");
			tableStr.append("<td>");
			tableStr.append(countDinnerMap.get("date"));
			tableStr.append("</td>");
			tableStr.append("</tr>");
		}  
		tableStr.append("</table>");
		return tableStr.toString();
	}

	private List<Map> allUserDinnerInfo() {
		//获取发送邮件用户
		List<UserInfo> sendUserInfo = userInfoService.selectAllUser();
		//获取当天点餐用户
		List<DinnerInfo> dinnerInfo = dinnerInfoService.getAllDinnerInfo();
		List<Map> countDinnerList = new ArrayList();
		//拼接map是否登记
		Map<String,String> countMap = null;
		for (UserInfo userInfo : sendUserInfo) {
			countMap = new HashMap();
			countMap.put("name", userInfo.getRealname());
			countMap.put("mail", userInfo.getMail());
			//是否登记：
			Map<String,String> dinnerInfoMap = isCountDinner(userInfo.getId(),dinnerInfo);
			countMap.put("isCount",dinnerInfoMap.get("isCount"));
			countMap.put("date",dinnerInfoMap.get("date"));
			countDinnerList.add(countMap);
		}
		return countDinnerList;
	}

	private Map<String,String> isCountDinner(Integer userid, List<DinnerInfo> dinnerInfoList) {
		// TODO Auto-generated method stub
		Map<String,String> dinnerInfoMap = new HashMap();
		String isCount = "未登记";
		String date = "";
		for (DinnerInfo dinnerInfo : dinnerInfoList) {
			if (userid.toString().equals(dinnerInfo.getUserId())) {
				isCount = "已登记";
				date = UtilTool.dateToStr(dinnerInfo.getData(), "yyyy-MM-dd HH:mm:ss");
				break;
			} 
		}
		dinnerInfoMap.put("isCount", isCount);
		dinnerInfoMap.put("date", date);
		return dinnerInfoMap;
	}

	
} 

