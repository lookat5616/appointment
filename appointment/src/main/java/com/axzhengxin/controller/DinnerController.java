package com.axzhengxin.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.axzhengxin.dto.DinnerInfo;
import com.axzhengxin.dto.DinnerNum;
import com.axzhengxin.dto.UserInfo;
import com.axzhengxin.dto.UserInfoLog;
import com.axzhengxin.service.IDinnerInfoService;
import com.axzhengxin.service.IDinnerNumService;
import com.axzhengxin.service.IUserInfoLogService;
import com.axzhengxin.service.IUserInfoService;





  
@Controller  
@RequestMapping("/dinner")  
public class DinnerController {  
    @Resource  
    private IUserInfoService  userInfoService;
    
    @Resource  
    private IDinnerNumService  dinnerNumService;
    
    @Resource
    private IDinnerInfoService dinnerInfoService;
    
    @Resource  
    private IUserInfoLogService  userInfoLogService;
    
      
    @RequestMapping("/showDinnerInfo")  
    public String toDinnerInfo(HttpServletRequest request,Model model){   
    	String userId = request.getParameter("id");
    	int userNum = 0;   //人员总数
    	int dinnerNum = 0;  //就餐登记数量
    	int isbuttAble = 0;  //0,提交按钮不可用，1，提交按钮可用。
    	int isUser = 0;  //0,不是本公司员工，1，是本公司员工
    	String nickname = "";
		//插入用户日志表：
		UserInfo getUserInfo = userInfoService.getUserInfoById(Integer.parseInt(userId));
		    			
		if (getUserInfo != null) {
			isUser = 1;  //是本公司员工
			nickname = getUserInfo.getNickname();
			//查询人员总数
			userNum = userInfoService.getUserNum();
			//查询当前就餐登记数据
			dinnerNum = dinnerInfoService.getDinnerNum();
			//插入就餐统计表
			DinnerNum dinnerNumObj = dinnerNumService.getDinnerNum();
			if(dinnerNumObj == null) {
				DinnerNum dinnerNumObjFirst = new DinnerNum();
				dinnerNumObjFirst.setDinnernum(0);
				dinnerNumService.insertSelective(dinnerNumObjFirst);
			}
			//是否已经就餐登记
			DinnerInfo dinnerInfo = dinnerInfoService.getDinnerInfo(Integer.toString(getUserInfo.getId()));
			if (dinnerInfo == null){
				isbuttAble = 1;	   //没有就餐登记，提交按钮可用。		
			} else {
				isbuttAble = 0;	   //已经就餐登记，提交按钮不可用。	
			}
		} else {
			//不是本公司员工，提交按钮不可用
			isUser = 0;
		}
	
        if(isUser == 0 ) {
        	try {
				model.addAttribute("message", new String(java.net.URLEncoder.encode("您不是公司员工，不能进行就餐登记!","UTF-8")));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
        	return "promptPage";     		
        } 
        model.addAttribute("userNum", userNum);  
        model.addAttribute("dinnerNum", dinnerNum); 
        model.addAttribute("isbuttAble", isbuttAble);  
        model.addAttribute("isUser", isUser); 
        model.addAttribute("userId", userId);
        model.addAttribute("nickname", nickname);
        model.addAttribute("cutrdate",getDate("yyyy-MM-dd"));
        return "dinnerInfo"; 
    }
    
    @RequestMapping("/insertDinnerInfo") 
    public @ResponseBody Map<String,Object> submitDinnerInfo(HttpServletRequest request,Model model) {
    	String userId = request.getParameter("userId");
    	//是否已经就餐登记
		DinnerInfo isDinnerInfo = dinnerInfoService.getDinnerInfo(userId);
		
        if (isDinnerInfo == null) {
    	   //记录登记明细
           UserInfo getUserInfo = userInfoService.getUserInfoById(Integer.parseInt(userId));
    	   DinnerInfo dinnerInfo = new DinnerInfo();
    	   dinnerInfo.setUserId(userId);
    	   dinnerInfo.setName(getUserInfo.getRealname());
    	   dinnerInfo.setData(new Date());
    	   dinnerInfoService.inert(dinnerInfo);
        }
       //更新就餐登记数量  从明细表中获取就餐数量
 	   //更新就餐人数表
 	    int dinnerNum = dinnerInfoService.getDinnerNum();
 	    DinnerNum dinnerNumObj = dinnerNumService.getDinnerNum();
 	    dinnerNumObj.setDinnernum(dinnerNum);
 	    dinnerNumService.update(dinnerNumObj);
 	    Map<String,Object> map = new HashMap<String,Object>();
	 	int userNum = userInfoService.getUserNum();
 	    map.put("userNum", userNum);  
 	    map.put("dinnerNum", dinnerNum); 
 	    map.put("isbuttAble", 0);  
 	    map.put("isUser", 1); 
 	    map.put("userId", userId);
        return map; 
    }
    
    @RequestMapping("/refDinnerInfo") 
    public @ResponseBody Map<String,Object> refDinnerInfo(HttpServletRequest request,Model model) {
    	
    	String dinnerNumStr = request.getParameter("dinnerNum");
    	if (dinnerNumStr == null) {
    		dinnerNumStr = "0";	
    	}
    	int userNum = userInfoService.getUserNum();
    	//更新就餐登记数量  从明细表中获取就餐数量
    	int dinnerNum = dinnerInfoService.getDinnerNum();
    	//更新就餐人数表
    	if (Integer.parseInt(dinnerNumStr) < dinnerNum) {
    		DinnerNum dinnerNumObj = dinnerNumService.getDinnerNum();
    		dinnerNumObj.setDinnernum(dinnerNum);
    		dinnerNumService.update(dinnerNumObj);
    	}
	    Map<String,Object> map = new HashMap<String,Object>();
	    map.put("userNum", userNum);  
	    map.put("dinnerNum", dinnerNum); 
        return map; 
    }

	private String getResponseContent(String url) {
		String responseContent = null;
		try{
            X509TrustManager x509mgr = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] xcs, String string) {
                }
                public void checkServerTrusted(X509Certificate[] xcs, String string) {
                }
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[] { x509mgr }, null);
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            CloseableHttpClient httpclient  = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            
            HttpGet get = new HttpGet(url);    
            HttpResponse response = httpclient.execute(get);
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                responseContent = EntityUtils.toString(entity, "UTF-8");
            }
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }catch(KeyManagementException e){
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return responseContent;
	}
	
	//获取当前时间字符串，
	private String getDate(String formatStr) {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
		return format.format(c.getTime());	
	}
	
}
