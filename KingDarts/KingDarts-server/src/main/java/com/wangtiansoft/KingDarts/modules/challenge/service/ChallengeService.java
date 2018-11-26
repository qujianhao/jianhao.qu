package com.wangtiansoft.KingDarts.modules.challenge.service;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.Challenge;
import com.wangtiansoft.KingDarts.results.core.ChallengeResult;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* Created by wt-templete-helper.
 */
public interface ChallengeService extends IBaseService<Challenge, Long> {

    // 分页查询Challenge
    Page<Map> queryChallengePageList(Map paramMap, PageBean pageBean);

	/**
	 * 得到约战用户
	 * @param uuid
	 * @param date
	 * @return
	 */
    ChallengeResult getUserChallenge(String uuid, Date date);

	/**
	 * 更新约战成绩
	 * @param orderNo
	 * @param win
	 */
	void updateWin(String orderNo, String win);

	/**
	 * 标记约战已过期
	 */
	void changeChallengeStatus();

	

}

