package com.axzhengxin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.axzhengxin.service.ISendMailService;

@Controller  
@RequestMapping("/sendMail")  
public class SendMailController {  
	@Resource  
    private ISendMailService  sendMailService;
    
      
    @RequestMapping("/sendDinnerMail")  
    public String sendDinnerMail(HttpServletRequest request,Model model){   
    	sendMailService.sendMailDinnner();
        return "dinnerInfo"; 
    }
    
    @RequestMapping("/sendDinnerNumMail")  
    public String sendDinnerNumMail(HttpServletRequest request,Model model){   
    	sendMailService.sendMailDinnnerNum();
        return "dinnerInfo"; 
    }
}
