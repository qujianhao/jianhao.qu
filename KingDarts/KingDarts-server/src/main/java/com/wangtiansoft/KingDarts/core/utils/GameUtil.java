package com.wangtiansoft.KingDarts.core.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;


public class GameUtil {

	private static Map<String,JSONObject> games = null;
	
	public static String[] netCodes = {"s001g003","s001g006","s001g009","s002g003","s003g002","s004g002","s005g002","s008g002","s009g002"};//网络对战匹配
	public static String[] netNames = {"301","501","701","米老鼠","高分赛","红心王","拳王格斗","21点","打气球"};//网络对战名称

	/**
	 * 计算不通模式下订单金额
	 * @param game_mode 1单人，2双人，3三人，4四人，5双人赛2V2，6三人赛3V3，7网络1V1
	 * @return
	 */
	public static BigDecimal countCost(Integer game_mode,BigDecimal price){
		switch(game_mode){
		case 1:
			return price;
		case 2:
			return price.multiply(new BigDecimal(game_mode));
		case 3:
			return price.multiply(new BigDecimal(game_mode));
		case 4:
			return price.multiply(new BigDecimal(game_mode));
		case 5:
			return price.multiply(new BigDecimal(4));
		case 6:
			return price.multiply(new BigDecimal(game_mode));
		case 7:
			return price;
		default:
			throw new AppRuntimeException("游戏类型错误");
		}
	}

	/**
	 * 计算不通模式下积分
	 * @param game_mode 1单人，2双人，3三人，4四人，5双人赛2V2，6三人赛3V3，7网络1V1
	 * @return
	 */
	public static Integer countPoints(Integer game_mode){
		switch(game_mode){
		case 1:
			return 5;
		case 2:
			return 15;
		case 3:
			return 25;
		case 4:
			return 35;
		case 5:
			return 35;
		case 6:
			return 50;
		case 7:
			return 5;
		default:
			throw new AppRuntimeException("游戏类型错误");
		}
	}

	/**
	 * 根据游戏编码获取游戏类型
	 * @param gameCode
	 * @return
	 */
	public static String getGameType(String gameCode){
		if(gameCode.length()>3){
			return gameCode.substring(0, 4);
		}else{
			return gameCode;
		}
	}
	public static List<String> getGameTypeList(){
		try {
			if(games == null){
				initGames();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 根据游戏编码获取游戏名称
	 * @param gameCode
	 * @return
	 */
	public static String getGameName(String gameCode){
		try {
			if(games == null){
				initGames();
			}
			return games.get(gameCode).getString("name");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 是否是大富豪游戏
	 * @param gameCode
	 * @return
	 */
	public static boolean isRich(String gameCode){
		if("s007g001".equals(gameCode)){
			return true;
		}
		return false;
	}

	/**
	 * 是否是网络对战
	 * @param gameCode
	 * @return
	 */
	public static boolean isNetGame(String gameCode){
		String name = getGameName(gameCode);
		if(name!=null&&name.contains("网络")){
			return true;
		}
		return false;
	}
	
	/**
	 * 得到对战网络游戏名称
	 * @param gameCode
	 * @return
	 */
	public static String getNetName(String gameCode){
		for(int i=0;i<GameUtil.netCodes.length;i++){
			if(netCodes[i].equals(gameCode)){
				return netNames[i];
			}
		}
		return "";
	}

	public static void initGames() throws IOException{
		games = new HashMap();
		String fileName = "games.json";
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		if (inputStream == null) {
			throw new IllegalArgumentException("Properties file not found in classpath: " + fileName);
		}
		String result = IOUtils.toString(inputStream, "UTF-8");
		JSONArray jsonArray = JSON.parseArray(result);
		for (int j= 0;j<jsonArray.size();j++){  
			JSONObject  jsonObject= jsonArray.getJSONObject(j);
			JSONArray gamelist = jsonObject.getJSONArray("games");
			for (int k= 0;k<gamelist.size();k++){  
				JSONObject  game = gamelist.getJSONObject(k);
				games.put(game.getString("id"), game);
			}
		}
	}

	/**
	 * 根据积分获得用户等级
	 * @param points
	 * @return
	 */
	public static int getVipLevel(Integer points){
		if(points>=0 && points<=5){
			return 1;
		}else if(points>=6 && points<=50){
			return 2;
		}else if(points>=51 && points<=100){
			return 3;
		}else if(points>=101 && points<=300){
			return 4;
		}else if(points>=301 && points<=500){
			return 5;
		}else if(points>=501 && points<=1000){
			return 6;
		}else if(points>=1001 && points<=2000){
			return 7;
		}else if(points>=2001 && points<=3000){
			return 8;
		}else if(points>=3001){
			return 9;
		}
		return 0;
	}

}
