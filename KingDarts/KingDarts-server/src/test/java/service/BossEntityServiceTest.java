package service;

import static org.junit.Assert.*;

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
import com.wangtiansoft.KingDarts.modules.boss.service.BossEntityService;
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
public class BossEntityServiceTest {

	@Autowired
	private BossEntityService bossEntityService;

	@Test
	public void queryBossEntityInfo(){
		try  { 
			
			Map mapBoass=bossEntityService.queryBossEntityInfo();
			System.out.println(mapBoass);
//			assertTrue();
		}catch (Exception e){  
			e.printStackTrace();
			fail("Test failed!");  
		}  
	} 

	
}
