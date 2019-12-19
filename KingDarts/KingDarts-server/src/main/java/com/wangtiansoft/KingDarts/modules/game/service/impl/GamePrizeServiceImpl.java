package com.wangtiansoft.KingDarts.modules.game.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.modules.game.service.GamePrizeService;
import com.wangtiansoft.KingDarts.persistence.dao.master.GamePrizeMapper;
import com.wangtiansoft.KingDarts.persistence.entity.GamePrize;

@Transactional
@Service("gamePrizeService")
public class GamePrizeServiceImpl implements GamePrizeService {
	
	private final Logger logger = LoggerFactory.getLogger(GamePrizeServiceImpl.class);
	
	@Autowired
	private GamePrizeMapper gamePrizeMapper;
	
	@Override
	public JSONArray listValidPrizes() {
		try {
			List<GamePrize> list = gamePrizeMapper.listValidPrize();
			JSONArray array = new JSONArray();
			if (list.size()>0) {
				for (GamePrize gamePrize : list) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", gamePrize.getId());
					jsonObject.put("prize", gamePrize.getPrize());
					jsonObject.put("cond", gamePrize.getCond());
					jsonObject.put("gift", gamePrize.getGift());
					array.add(jsonObject);
				}
			}
			return array;
		} catch (Exception e) {
			logger.error("sql error----"+e);
			throw new  AppRuntimeException("100000","db error");
			
		}
	}

}
