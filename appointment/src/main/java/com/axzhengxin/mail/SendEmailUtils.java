package com.axzhengxin.mail;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailUtils {

    
    private static String from = "stgl@axzhengxin.com";  //发件人
    private static String nick = "食堂管理";  //昵称
    private static String password = "Stgl!@1234";//密码
    
    private static String protocol = "smtp";
    private static String host = "smtp.axzhengxin.com";
    private static String port = "25";
    private static String auth = "true";
    
    private static Properties properties = new Properties();
    
    
    public static Properties loadProp() {
    	Properties prop = new Properties();
    	try {
    		InputStreamReader in = new InputStreamReader(SendEmailUtils.class.getClassLoader().getResourceAsStream("mail.properties"),"UTF-8");
			prop.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	from = prop.getProperty("from","stgl@axzhengxin.com");
    	//昵称
		nick = prop.getProperty("nick","食堂管理");
    	password = prop.getProperty("password","Stgl!@1234");//密码
    	protocol = prop.getProperty("mail.transport.protocol","smtp");//密码
    	host = prop.getProperty("mail.smtp.host","smtp.axzhengxin.com");//主机
    	port = prop.getProperty("mail.smtp.port","25");//端口
    	auth = prop.getProperty("mail.smtp.auth","true");//密码
    	properties = prop;
    	return prop;
    }
    
    public static  Session createSession() {
    	Properties prop = loadProp();
    	Properties props = new Properties();
    	//协议
        props.setProperty("mail.transport.protocol", protocol);
        //服务器
        props.setProperty("mail.smtp.host", host);
        //端口
        props.setProperty("mail.smtp.port", port);
        //使用smtp身份验证
        props.setProperty("mail.smtp.auth", auth);
        Session session = Session.getDefaultInstance(props, new MyAuthenricator(from, password));
        return session;
    }
    
    public static Transport createTransport(Session session) {
    	 Transport transport = null;
         try {
        	 transport = session.getTransport("smtp");  
             // 连接服务器的邮箱  
             //transport.connect(host, from, password); 
 		} catch (NoSuchProviderException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return transport;
    }
    
    public static  MimeMessage createMimeMessage(Session session,String subject,String Content,String to)
            throws Exception {
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //指明邮件的发件人
        message.setFrom(new InternetAddress(from,nick));
        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        //邮件的标题
        message.setSubject(subject);
        //邮件的文本内容
        message.setContent(Content, "text/html;charset=UTF-8");
        //返回创建好的邮件对象
        message.saveChanges();
        return message;
    }
    
    public static void close(Transport transport) {  
        if (null != transport)  
            try{  
                transport.close();  
            }catch (Exception e) {  
                transport = null;  
         }  
     }  
    

   public static void send(String to, String subject, String Content){
   	   Properties props = new Properties();
   	   //协议
       props.setProperty("mail.transport.protocol", protocol);
       //服务器
       props.setProperty("mail.smtp.host", host);
       //端口
       props.setProperty("mail.smtp.port", port);
       //使用smtp身份验证
       props.setProperty("mail.smtp.auth", auth);
        Session session = Session.getDefaultInstance(props, new MyAuthenricator(from, password));
        session.setDebug(true);
        //通过session得到transport对象
        Transport ts = null;
        try {
			ts = session.getTransport();
		} catch (NoSuchProviderException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        MimeMessage mimeMessage = null;
        try {
        	mimeMessage = createSimpleMail(session,subject,Content,to);
            ts.send(mimeMessage);
            ts.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static  MimeMessage createSimpleMail(Session session,String subject,String Content,String to)
            throws Exception {
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //指明邮件的发件人
        message.setFrom(new InternetAddress(from,nick));
        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        //邮件的标题
        message.setSubject(subject);
        //邮件的文本内容
        message.setContent(Content, "text/html;charset=UTF-8");
        //返回创建好的邮件对象
        message.saveChanges();
        return message;
    }
    
    /*
    public static void main(String[] args) {
    	SendEmailUtils s = new SendEmailUtils("zhangfu@axzhengxin.com","Zhangfu@1234","123282482@qq.com",
             "20160611121859OYI7W1");
        s.send();
	}
	*/
    
  //用户名密码验证，需要实现抽象类Authenticator的抽象方法PasswordAuthentication
    static class MyAuthenricator extends Authenticator{  
        String u = null;  
        String p = null;  
        public MyAuthenricator(String u,String p){  
            this.u=u;  
            this.p=p;  
        }  
        @Override  
        protected PasswordAuthentication getPasswordAuthentication() {  
            return new PasswordAuthentication(u,p);  
        }  
    }

}
