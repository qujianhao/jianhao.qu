package service;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.CmsApplication;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.modules.user.service.UserService;
import com.wangtiansoft.KingDarts.modules.weixin.utils.WeiXinUtils;
import com.wangtiansoft.KingDarts.persistence.dao.master.AccountMapper;
import com.wangtiansoft.KingDarts.persistence.dao.record.SigninReMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Account;
import com.wangtiansoft.KingDarts.persistence.entity.Signin;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApplication.class)
@EnableAutoConfiguration
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	public void consume(){
		try  { 
			userService.consume("d8e9689cb2124ec5b913892ac1ae6160"
					, new Long(938), new BigDecimal(10), 1, "s001g001", 2, "111111");
			
//			assertTrue();
		}catch (Exception e){  
			e.printStackTrace();
			fail("Test failed!");  
		}  
	} 
	@Test
	public void addOrUpdateFromWeiXin(){
		try  { 
			String nickName="";
			int gender= 1;
			String city="";//城市
			String province="";//省份
			String country="";//国家
			String headimgurl="";
			String unionid="";
			userService.addOrUpdateFromWeiXin("appid111","", WeiXinUtils.filterWeixinEmoji(nickName), unionid, headimgurl, country, city, province, gender, null,null);
			
//			assertTrue();
		}catch (Exception e){  
			e.printStackTrace();
			fail("Test failed!");  
		}  
	} 

	
}
