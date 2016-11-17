package com.axzhengxin.controller;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.axzhengxin.dto.DinnerInfo;
import com.axzhengxin.dto.DinnerNum;
import com.axzhengxin.dto.UserInfo;
import com.axzhengxin.service.IDinnerInfoService;
import com.axzhengxin.service.IDinnerNumService;
import com.axzhengxin.service.IUserInfoService;





  
@Controller  
@RequestMapping("/user")  
public class UserController {  
    @Resource  
    private IUserInfoService  userInfoService;
    
    @Resource  
    private IDinnerNumService  dinnerNumService;
    
    @Resource
    private IDinnerInfoService dinnerInfoService;
    
      
    @RequestMapping("/showUser")  
    public String toIndex(HttpServletRequest request,Model model){   
    	int userId = Integer.parseInt(request.getParameter("id"));
        //User user = (User) this.userService.getUserById(userId);  
    	String code = request.getParameter("code");
    	int userNum = 0;   //人员总数
    	int dinnerNum = 0;  //就餐登记数量
    	int isbuttAble = 0;  //0,提交按钮不可用，1，提交按钮可用。
    	int isUser = 0;  //0,不是本公司员工，1，是本公司员工
		String responseContent = "";
		String access_token = "";
		String refresh_token = "";
		String openid = "";
		String nickname = "";
    	if (code != null) {
    
    		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxafd0234f8726e785&secret=d0176a7b6c5264403f068ce74c3a7aa8&code="+code+"&grant_type=authorization_code"; 
    		responseContent = getResponseContent(url); 
    		System.out.println("responseContent 1"+ responseContent);
    		if (responseContent !=null) {
    			JSONObject jsonObject = JSON.parseObject(responseContent);
    			if (jsonObject.getString("errcode") != null) {
    				
    			}
    			access_token = jsonObject.getString("access_token");
    			refresh_token = jsonObject.getString("refresh_token");
    			openid = jsonObject.getString("openid");
    			
    		}
    		url = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
    		responseContent = getResponseContent(url); 
    		if (responseContent !=null) {
    			JSONObject jsonObject = JSON.parseObject(responseContent);
    			UserInfo userInfo = JSON.parseObject(responseContent, UserInfo.class);
    			nickname = userInfo.getNickname();
    			UserInfo getUserInfo = userInfoService.getUserInfoByName(userInfo.getNickname());
    			if (getUserInfo == null) {   //如果根据姓名没有查到则根据openid在查一次。
    				getUserInfo = userInfoService.getUserInfoById(Integer.parseInt(userInfo.getOpenid()));
    				if (getUserInfo != null) {   //根据openId存在，则更新相关数据
    					userInfoService.updateByPrimaryKeySelective(userInfo);
    				}
    			}
    			
    			if (getUserInfo != null) {
    				isUser = 1;  //是本公司员工
    				//用户微信信息入库
    				//UserInfoService.inertUserInfo(userInfo);
    				//查询人员总数
    				userNum = userInfoService.getUserNum();
        			//查询当前就餐登记数据
    				DinnerNum dinnerNumObj = dinnerNumService.getDinnerNum();
    				if(dinnerNumObj != null) {
    					dinnerNum = dinnerNumObj.getDinnernum();
    				} else {
    					DinnerNum dinnerNumObjFirst = new DinnerNum();
    					dinnerNumObjFirst.setDinnernum(0);
    					dinnerNumService.insertSelective(dinnerNumObjFirst);
    				}
    				//是否已经就餐登记
    				DinnerInfo dinnerInfo = dinnerInfoService.getDinnerInfo(userInfo.getOpenid());
    				if (dinnerInfo == null){
    					isbuttAble = 1;	   //没有就餐登记，提交按钮不可用。		
    				} else {
    					isbuttAble = 0;	   //已经就餐登记，提交按钮不可用。	
    				}
    			} else {
    				//不是本公司员工，提交按钮不可用
    				isUser = 0;
    			}
    			
    		}
    	}
    	
        System.out.println("code:"+code);
        System.out.println("look at zhangfu");
        model.addAttribute("userNum", userNum);  
        model.addAttribute("dinnerNum", dinnerNum); 
        model.addAttribute("isbuttAble", isbuttAble);  
        model.addAttribute("isUser", isUser); 
        model.addAttribute("openid", openid); 
        model.addAttribute("nickname", nickname); 
        if(isUser == 0 ) {
        	return "promptPage";     		
        } 
        return "dinnerInfo"; 
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
                

                //拉取用户信息(需scope为 snsapi_userinfo)
                //url = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
                //get = new HttpGet(url);    
                // response = httpclient.execute(get);
                //HttpEntity entity = response.getEntity();
                //if (null != entity) {
                //   JSONObject jsonObject = JSON.parseObject(responseContent);
                //    access_token = jsonObject.getString("access_token");
                //
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
}
