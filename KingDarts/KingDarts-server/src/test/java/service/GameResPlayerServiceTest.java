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

import com.wangtiansoft.KingDarts.CmsApplication;
import com.wangtiansoft.KingDarts.modules.game.service.GameResPlayerService;
import com.wangtiansoft.KingDarts.persistence.entity.GameOrder;
import com.wangtiansoft.KingDarts.persistence.entity.GameResPlayer;

import tk.mybatis.mapper.entity.Example;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApplication.class)
@EnableAutoConfiguration
public class GameResPlayerServiceTest {

	@Autowired
	private GameResPlayerService gameResPlayerService;

	@Test
	public void find(){
		try  { 
			Example example = new Example(GameResPlayer.class);
	    	example.createCriteria().andEqualTo("order_no", "SHOIK28R");
	    	List<GameResPlayer> list = gameResPlayerService.findAllByExample(example);
	    	for(GameResPlayer g : list){
	    		System.out.println(g.getPpd());
	    		System.out.println(g.getPpr());
	    		System.out.println(g.getMpr());
	    	}
//			assertTrue();
		}catch (Exception e){  
			e.printStackTrace();
			fail("Test failed!");  
		}  
	} 

	
}
