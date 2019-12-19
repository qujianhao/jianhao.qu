package api;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wangtiansoft.KingDarts.modules.user.service.UserService;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.wangtiansoft.KingDarts.common.utils.HttpUtil;
import com.wangtiansoft.KingDarts.common.utils.date.DateUtil;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
public class AppAPITest {

	private static String baseUrl = "http://127.0.0.1";
//	private static String baseUrl = "http://47.105.62.156";
	
	private static String AUTHORIZATION = "x-access-token";
	private static String AUTHEQUNO = "equno";
	private static String accessToken = "061844eb0bb445efbf662ca4b41a7d71";
	@Resource(name="userService")
    private UserService userService;

	@Test
    public void testuser(){
	    userService.video("0cf7c741faf840f8942977b37237ae67");
    }

	
	@Test
	public void gameres(){
		try {
			String url = baseUrl+"/api/equ/gameres";
			Map<String, String> params = new HashMap<>();
			Map<String, String> headers = new HashMap<>();
//			headers.put(AUTHORIZATION, accessToken);
//			headers.put(AUTHEQUNO, "222222");
//			JSONArray jsonArray = JSON.parseArray(input);
//			String resultStr = HttpUtil.post(url, orderresults(), headers);
			
			headers.put(AUTHORIZATION, "0c10905393cd44bcb17460ca3fa32c1f");
			headers.put(AUTHEQUNO, "222222");
			String input = FileUtils.readFileToString(new File("d:/new_game_data_json3.txt"),  "UTF-8");
			System.out.println(input);
			String resultStr = HttpUtil.post(url, input, headers);
			System.out.println(resultStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private String orderresults(){
    	Map orderRes = new HashMap();
    	orderRes.put("type", "orderresults");
    	orderRes.put("requestId", "12321");
    	
    	Map data = new HashMap();
    	data.put("order_no", "SHOIK28R");
        
        List results = new ArrayList<>();
        results.add(getGroup1());
        results.add(getGroup2());
        data.put("results", results);
        
        orderRes.put("data", data);
        String push = JSON.toJSONString(orderRes);  
        System.out.println(push);
        return push;
    }
    
    private Map getGroup1(){
    	Map group1 = new HashMap();
        group1.put("groupId", 1);
        group1.put("groupScore", 0);
        
        List playerList = new ArrayList<>();
        Map player1 = new HashMap();
        player1.put("PPD", 50.166668);
        player1.put("PPR", 150.5);
        player1.put("MPR", -1);
        player1.put("hitNum", 6);
        player1.put("playerId", 1);
        player1.put("playerName", "player1");
        player1.put("playerScore", 301);
        playerList.add(player1);
        
        group1.put("playerList", playerList);
        
        List roundList = new ArrayList<>();
        Map round1 = new HashMap();
        round1.put("hitNum", 3);
        round1.put("playerId", 1);
        round1.put("roundScore", 180);
        
        List hitDataList = new ArrayList<>();
        Map hitData1 = new HashMap();
        hitData1.put("area", 1);
        hitData1.put("score", 20);
        hitDataList.add(hitData1);
        Map hitData2 = new HashMap();
        hitData2.put("area", 1);
        hitData2.put("score", 20);
        hitDataList.add(hitData2);
        Map hitData3 = new HashMap();
        hitData3.put("area", 1);
        hitData3.put("score", 20);
        hitDataList.add(hitData3);
        round1.put("hitData", hitDataList);
        roundList.add(round1);
        
        
        Map round2 = new HashMap();
        round2.put("hitNum", 3);
        round2.put("playerId", 1);
        round2.put("roundScore", 121);
        
        List hitDataList2 = new ArrayList<>();
        Map hitData21 = new HashMap();
        hitData21.put("area", 1);
        hitData21.put("score", 20);
        hitDataList2.add(hitData21);
        Map hitData22 = new HashMap();
        hitData22.put("area", 1);
        hitData22.put("score", 7);
        hitDataList2.add(hitData22);
        Map hitData23 = new HashMap();
        hitData23.put("area", 3);
        hitData23.put("score", 20);
        hitDataList2.add(hitData23);
        round2.put("hitData", hitDataList2);
        roundList.add(round2);
        
        group1.put("roundList", roundList);
        
        return group1;
    }
    
    private Map getGroup2(){
    	Map group1 = new HashMap();
    	group1.put("groupId", 2);
    	group1.put("groupScore", 301);
    	
    	List playerList = new ArrayList<>();
    	Map player1 = new HashMap();
    	player1.put("PPD", 0.0);
    	player1.put("PPR", 0.0);
    	player1.put("MPR", -1);
    	player1.put("hitNum", 3);
    	player1.put("playerId", 2);
    	player1.put("playerName", "player2");
    	player1.put("playerScore", 0);
    	playerList.add(player1);
    	
    	group1.put("playerList", playerList);
    	
    	List roundList = new ArrayList<>();
    	Map round1 = new HashMap();
    	round1.put("hitNum", 3);
    	round1.put("playerId", 2);
    	round1.put("roundScore", 0);
    	
    	List hitDataList = new ArrayList<>();
    	Map hitData1 = new HashMap();
    	hitData1.put("area", 0);
    	hitData1.put("score", 0);
    	hitDataList.add(hitData1);
    	Map hitData2 = new HashMap();
    	hitData2.put("area", 0);
    	hitData2.put("score", 0);
    	hitDataList.add(hitData2);
    	Map hitData3 = new HashMap();
    	hitData3.put("area", 0);
    	hitData3.put("score", 0);
    	hitDataList.add(hitData3);
    	round1.put("hitData", hitDataList);
    	roundList.add(round1);
    	
    	
    	group1.put("roundList", roundList);
    	
    	return group1;
    }
	
}
