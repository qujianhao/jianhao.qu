package service;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wangtiansoft.KingDarts.modules.user.service.UserService;
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
import com.wangtiansoft.KingDarts.persistence.dao.master.AccountMapper;
import com.wangtiansoft.KingDarts.persistence.dao.record.SigninReMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Account;
import com.wangtiansoft.KingDarts.persistence.entity.Signin;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = CmsApplication.class)
//@WebAppConfiguration

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApplication.class)
@EnableAutoConfiguration
public class AccountServiceTest {

	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private SigninReMapper signinMapper;

	@Autowired
	 private UserService userService;

	@Test
	public void testuser(){
		userService.video("0cf7c741faf840f8942977b37237ae67");
	}


	@Test
	public void getAccount(){
		try  { 
			
			/*List<Map> list1 = signinMapper.querySigninList(new HashMap());
			for(Map a:list1){
				System.out.println(a);
			}*/
			
			PageBean pageBean = new PageBean();
			pageBean.setPage(1);
			pageBean.setRows(10);
			PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
			Page<Map> page = (Page<Map>) signinMapper.querySigninList(new HashMap());
			for(Map a:page.getResult()){
				System.out.println(a);
			}
			
			List<Account> list = accountMapper.selectAll();
			for(Account a:list){
				System.out.println(a.getRemark());
			}
			
			/*List<Signin> list2 = signinMapper.selectAll();
			for(Signin a:list2){
				System.out.println(a.getUseraccount());
			}*/
			
//			assertTrue();
		}catch (Exception e){  
			e.printStackTrace();
			fail("Test failed!");  
		}  
	} 

	
}
