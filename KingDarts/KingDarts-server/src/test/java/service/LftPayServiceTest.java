package service;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import com.jfinal.kit.PropKit;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.wangtiansoft.KingDarts.CmsApplication;
import com.wangtiansoft.KingDarts.modules.pay.service.LftPayService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApplication.class)
@EnableAutoConfiguration
public class LftPayServiceTest {

	@Autowired
	private LftPayService lftPayService;

	@Test
	public void testPay(){
		try  { 
			lftPayService.testPay("KDS_1714157666");

			//			assertTrue();
		}catch (Exception e){  
			e.printStackTrace();
			fail("Test failed!");  
		}  
	} 

	@Test
	public void transfers(){
		try  { 

			lftPayService.transfers("oni_M4vTsxuhULAhe4HFliNBFNME", "127.0.0.1" ,"", "100");

			//			assertTrue();
		}catch (Exception e){  
			e.printStackTrace();
			fail("Test failed!");  
		}  
	} 


}
