package service;

import static org.junit.Assert.*;

import java.io.File;
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
import com.wangtiansoft.KingDarts.common.utils.OSSUtil;
import com.wangtiansoft.KingDarts.core.stubs.PluginManager;
import com.wangtiansoft.KingDarts.core.stubs.file.BaseFilePluginStub;
import com.wangtiansoft.KingDarts.modules.club.service.ClubInfoService;
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
public class ClubInfoServiceTest {

	@Autowired
	private ClubInfoService clubInfoService;

	@Test
	public void getPriceByEquNo(){
		try  { 
			
			BigDecimal price = clubInfoService.getPriceByEquNo("111111");
			System.out.println(price);
			
//			assertTrue();
		}catch (Exception e){  
			e.printStackTrace();
			fail("Test failed!");  
		}  
	} 
	
	@Test
	public void clubLogo(){
		try  { 
			String basepath = "d:\\";
			PageBean pageBean = new PageBean();
			pageBean.setRows(1000);
			Map paramMap = new HashMap();
			Page<Map> page = clubInfoService.queryClubInfoPageList(paramMap,pageBean);
			for(Map m : page.getResult()){
				String filepath = m.get("logo").toString();
				if(filepath.startsWith("/")){
					continue;
				}
				System.out.println("=== "+basepath+filepath);
				File physicalFile = new File(basepath+filepath);
				if(physicalFile.isFile()){
					OSSUtil.uploadFile(physicalFile, filepath);
					System.out.println("---- "+filepath);
				}
			}
//			assertTrue();
		}catch (Exception e){  
			e.printStackTrace();
			fail("Test failed!");  
		}  
	} 

	
}
