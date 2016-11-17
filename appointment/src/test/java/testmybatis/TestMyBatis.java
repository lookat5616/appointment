package testmybatis;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;


@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)		//��ʾ�̳���SpringJUnit4ClassRunner��
public class TestMyBatis {
  private static Logger logger = Logger.getLogger(TestMyBatis.class);
//	private ApplicationContext ac = null;


//	@Before
//	public void before() {
//		//ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		//userService = (IUserService) ac.getBean("userService");
//		System.out.println("[TestMyBatis]+"+userServices);
//	}

  @Test
  public void test1() {
	//System.out.println("[TestMyBatis]+"+userServices);
    //UserInfo userInfo = userInfoService.getUserInfoById("oC7GCwSHGFDv0_W86xm54rYzjfuk");
    // System.out.println(user.getUserName());
    // logger.info("ֵ��"+user.getUserName());
    //logger.info(JSON.toJSONString(userInfo));
  }
}