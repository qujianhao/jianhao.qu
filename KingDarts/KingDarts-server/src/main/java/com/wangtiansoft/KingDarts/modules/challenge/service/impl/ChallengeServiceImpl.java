package com.wangtiansoft.KingDarts.modules.challenge.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.BeanUtil;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.common.utils.StringUtils;
import com.wangtiansoft.KingDarts.common.utils.date.DateUtil;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.modules.challenge.service.ChallengeService;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.ChallengeMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Challenge;
import com.wangtiansoft.KingDarts.results.core.ChallengeResult;

import tk.mybatis.mapper.entity.Example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by wt-templete-helper.
 */
@Transactional
@Service("challengeService")
public class ChallengeServiceImpl extends BaseService<Challenge, Long> implements ChallengeService{

	@Autowired
	private ChallengeMapper challengeMapper;

	@Override
	public BaseMapper getBaseMapper() {
		return challengeMapper;
	}

	@Override
	public Page<Map> queryChallengePageList(Map paramMap, PageBean pageBean) {
		paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
		PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
		return (Page<Map>) challengeMapper.queryChallengeList(paramMap);
	}

	@Override
	public ChallengeResult getUserChallenge(String uuid,Date date){
		//查询用户在当前时间内是否有有效约战
		Map<String,String> paramMap = new HashMap<>();
		paramMap.put("startDate", DateUtil.DateToString(DateUtil.addHour(date, -2), "yyyy-MM-dd HH:mm:ss"));//开始前2小时
		paramMap.put("endDate", DateUtil.DateToString(DateUtil.addHour(date, 3), "yyyy-MM-dd HH:mm:ss"));//开始后3小时
		paramMap.put("sponsor", uuid);
		paramMap.put("receiver", uuid);
		List<ChallengeResult> challengeList = challengeMapper.queryTimeList(paramMap);
		for(ChallengeResult cr : challengeList){
			if(date.getTime() >= DateUtil.addMinute(cr.getStart_time(),-cr.getFloat_time()).getTime()
					&& date.getTime() < DateUtil.addMinute(cr.getStart_time(),cr.getFloat_time()).getTime()){
				//如果获取时间在有效时间内
				return cr;
			}
		}

		return null;
	}

	@Override
	public void updateWin(String orderNo,String win){
		Challenge challenge = challengeMapper.queryByOrderNo(orderNo);
		if(challenge!=null){
			Challenge record = new Challenge();
			record.setId(challenge.getId());
			if(orderNo.equals(challenge.getSponsor_order_no())){
				if("Y".equals(win)){
					record.setSponsor_win("Y");
				}else{
					record.setSponsor_win("N");
				}
			}else{
				if("Y".equals(win)){
					record.setSponsor_win("N");
				}else{
					record.setSponsor_win("Y");
				}
			}
			record.setChallenge_status(Constants.challenge_status_over);
			challengeMapper.updateByPrimaryKeySelective(record);
		}
	}

	@Override
	public void changeChallengeStatus(){
		List<Integer> statuses = new ArrayList<>();
		statuses.add(Constants.challenge_status_def);
		statuses.add(Constants.challenge_status_agree);

		Map<String,Object> paramMap = new HashMap<>();
		//    	paramMap.put("challenge_status", Constants.challenge_status_agree);
		paramMap.put("statuses", statuses);
		paramMap.put("time_end", new Date());
		List<Map> list = challengeMapper.queryChallengeList(paramMap);
		for(Map m : list){
			ChallengeResult challengeResult = BeanUtil.cast(m, ChallengeResult.class);
			Date lasttime = DateUtil.addMinute((Date)m.get("start_time"), (Integer)m.get("float_time")+1);
			if(lasttime.getTime()<new Date().getTime()){
				if(StringUtils.isNotEmpty(challengeResult.getSponsor_order_no())
						&&StringUtils.isNotEmpty(challengeResult.getReceiver_order_no())){
					//游戏时间已过期，但是游戏已经匹配上，游戏在进行中
					//如果最终开始时间超过3个小时仍然未结束，则超时
					if(DateUtil.addHour(lasttime, 3).getTime()<new Date().getTime()){
						Challenge challenge = new Challenge();
						challenge.setId(challengeResult.getId());
						challenge.setChallenge_status(Constants.challenge_status_overdue);
						challengeMapper.updateByPrimaryKeySelective(challenge);
					}
				}else{
					Challenge challenge = new Challenge();
					challenge.setId(challengeResult.getId());
					challenge.setChallenge_status(Constants.challenge_status_overdue);
					challengeMapper.updateByPrimaryKeySelective(challenge);
				}

			}

		}

	}
}
