package com.wangtiansoft.KingDarts.modules.challenge.service;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.Challenge;
import com.wangtiansoft.KingDarts.results.core.ChallengeResult;
import com.wangtiansoft.KingDarts.results.core.UserResult;

public interface ChallengeWeixinService extends IBaseService<Challenge, Integer> {

	/**
	 * 用户爽约次数
	 * @param user
	 * @return
	 */
	Integer missTimes(String userId);
	
	/**
	 * 新增约战信息
	 * @param challenge
	 * @param user
	 */
	void insertChallenge(Challenge challenge, UserResult user);
	
	/**
	 * 获取所有的约战列表
	 * @param user
	 * @param pageBean
	 * @return
	 */
	Page<ChallengeResult> findAllList(ChallengeResult challenge, PageBean pageBean);
	
	/**
	 * 获取当前用户的约战列表
	 * @param user
	 * @param pageBean
	 * @return
	 */
	Page<ChallengeResult> findSponsorChallengeList(ChallengeResult challenge, PageBean pageBean);
	
	/**
	 * 获取当前用户被约战的约战
	 * @param user
	 * @param pageBean
	 * @return
	 */
	Page<ChallengeResult> findReceiverChallengeList(ChallengeResult challenge, PageBean pageBean);

	/**
	 * 点击"对手爽约"后，将isMiss置为"Y"
	 * @param id
	 * @param useraccount
	 */
	void updateIsMiss(String id, String useraccount);
	
	/**
	 * 点击"应战"或者"拒绝"后，设置应战标志位
	 * @param id
	 * @param receiveStatus
	 */
	void updateReceiveStatus(String id, Integer receiveStatus);
	
	/**
	 * 返回约战详情
	 * @param id
	 * @return
	 */
	Challenge queryChallenge(String id);
	
	/**
	 * 设置约战已过期
	 * @param id
	 */
	void setPassDueStatus(String id);

	
}
