package com.wangtiansoft.KingDarts.modules.challenge.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.date.DateUtil;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.modules.challenge.service.ChallengeWeixinService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.ChallengeMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.ClubInfoMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.DictMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.UserMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Challenge;
import com.wangtiansoft.KingDarts.persistence.entity.ClubInfo;
import com.wangtiansoft.KingDarts.persistence.entity.Dict;
import com.wangtiansoft.KingDarts.persistence.entity.User;
import com.wangtiansoft.KingDarts.results.core.ChallengeResult;
import com.wangtiansoft.KingDarts.results.core.UserResult;

@Transactional
@Service("challengeWeixinService")
public class ChallengeWeixinServiceImpl extends BaseService<Challenge, Integer> implements ChallengeWeixinService {

	@Autowired
    private ChallengeMapper challengeMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ClubInfoMapper clubInfoMapper;
    @Autowired
	private DictMapper dictMapper;
	
	@Override
	public BaseMapper<Challenge> getBaseMapper() {
		return challengeMapper;
	}

	@Override
	public Integer missTimes(String userId) {
		Map<String, Object> params = Maps.newHashMap();
		Calendar c = Calendar.getInstance(Locale.CHINA);
		c.setTime(new Date());
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		//当前日期的周一时间
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		params.put("startDate", c.getTime());
		
		//返回当前日期的周日时间
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		c.add(Calendar.WEEK_OF_YEAR, 1);	//增加一周，因为默认的周日是一周的开始,而不是我们理解的一周的结束
		params.put("endDate", c.getTime());
		
		params.put("userId", userId);
		
		return challengeMapper.queryMissTimes(params);
	}

	@Override
	public void insertChallenge(Challenge challenge, UserResult user) {
		//设置发起者信息
			challenge.setSponsor_id(user.getId());
			challenge.setSponsor_useraccount(user.getUuid());
			
			//设置应战者信息
			String receiveUseraccount = challenge.getReceiver_useraccount();
			User receiver = userMapper.getByUserAccount(receiveUseraccount);
			if(receiveUseraccount == null) {
				throw new AppRuntimeException("您选择的用户不存在！");
			}
			challenge.setReceiver_id(receiver.getId());
			challenge.setCreate_time(new Date());
			challenge.setReceive_status(Constants.receive_status_wait);
			challenge.setChallenge_status(Constants.challenge_status_def);
			
			//判断此时间段是否有约战
			timeConflict(challenge);
			
			/*Map<String,String> paramMap = new HashMap<>();
			paramMap.put("startDate", DateUtil.DateToString(DateUtil.addHour(challenge.getStart_time(), -2), "yyyy-MM-dd HH:mm:ss"));//开始前2小时
			paramMap.put("endDate", DateUtil.DateToString(DateUtil.addHour(challenge.getStart_time(), 3), "yyyy-MM-dd HH:mm:ss"));//开始后3小时
			paramMap.put("sponsor", challenge.getSponsor_useraccount());
			paramMap.put("receiver", challenge.getReceiver_useraccount());
			//只有状态是应战的情况才判断时间
			List<ChallengeResult> challengeList = challengeMapper.queryTimeList(paramMap);
			for(ChallengeResult cr : challengeList){
				if(DateUtil.conflict(challenge.getStart_time(), DateUtil.addMinute(challenge.getStart_time(),challenge.getFloat_time())
						, cr.getStart_time(), DateUtil.addMinute(cr.getStart_time(),cr.getFloat_time()))){
					throw new AppRuntimeException("时间冲突不能约战");
				}
			}*/
			challengeMapper.insert(challenge);
		
	}
	

	@Override
	public Page<ChallengeResult> findAllList(ChallengeResult challenge, PageBean pageBean) {
		PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
		Page<ChallengeResult> allChallengeList = (Page<ChallengeResult>) challengeMapper.findAllList(challenge);
		return allChallengeList;
	}

	@Override
	public Page<ChallengeResult> findSponsorChallengeList(ChallengeResult challenge, PageBean pageBean) {
		PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
//		Challenge challenge = new Challenge();
//		challenge.setSponsor_id(user.getId());
		Page<ChallengeResult> sponsorChallengeList = (Page<ChallengeResult>) challengeMapper.findSponsorChallengeList(challenge);
		return sponsorChallengeList;
	}

	@Override
	public Page<ChallengeResult> findReceiverChallengeList(ChallengeResult challenge, PageBean pageBean) {
		PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
		Page<ChallengeResult> recieverChallengeList = (Page<ChallengeResult>) challengeMapper.findReceiverChallengeList(challenge);
		return recieverChallengeList;
	}

	@Override
	public void updateIsMiss(String id, String useraccount) {
		if(StringUtils.isNotEmpty(id)) {
			Challenge challenge = challengeMapper.queryChallenge(id);
			if(useraccount.equals(challenge.getSponsor_useraccount())) {
				//登录人是发起人，则应战方爽约
				int count = challengeMapper.updateReceiverMiss(id);
				
				if(count < 1) {
					throw new AppRuntimeException("设置失败！");
				}
			}
			if(useraccount.equals(challenge.getReceiver_useraccount())) {
				//登录人是应战方，则发起人爽约
				int count = challengeMapper.updateSponsorMiss(id);
				
				if(count < 1) {
					throw new AppRuntimeException("设置失败！");
				}
			}
			
		}
		
	}

	@Override
	public void updateReceiveStatus(String id, Integer receiveStatus) {
		if(StringUtils.isEmpty(id)) {
			throw new AppRuntimeException("当前约战信息不存在！");
		}
		if(receiveStatus == null) {
			throw new AppRuntimeException("应战状态不能为空！");
		}
		if(!receiveStatus.equals(Constants.receive_status_agree)&&!receiveStatus.equals(Constants.receive_status_refuse) ){
			throw new AppRuntimeException("应战状态错误！");
		}
		
		Challenge ch = challengeMapper.selectByPrimaryKey(Long.valueOf(id));
		if(ch==null){
			throw new AppRuntimeException("当前约战信息不存在！");
		}
		if(ch.getReceive_status().toString().equals("2")){
			throw new AppRuntimeException("当前约战已拒绝！");
		}
		if(ch.getReceive_status().toString().equals("3")){
			throw new AppRuntimeException("当前约战已应战！");
		}
		//判断时间是否冲突
		timeConflict(ch);
		
		Challenge challenge = new Challenge();
		challenge.setId(Long.valueOf(id));
		challenge.setReceive_status(receiveStatus);
		challenge.setChallenge_status(receiveStatus);
//		int count = challengeMapper.updateReceiveStatus(challenge);
		int count = challengeMapper.updateByPrimaryKeySelective(challenge);
		if(count < 1) {
			throw new AppRuntimeException("设置失败！");
		}
	}
	
	private void timeConflict(Challenge challenge){
		Map<String,String> paramMap = new HashMap<>();
		paramMap.put("startDate", DateUtil.DateToString(DateUtil.addHour(challenge.getStart_time(), -2), "yyyy-MM-dd HH:mm:ss"));//开始前2小时
		paramMap.put("endDate", DateUtil.DateToString(DateUtil.addHour(challenge.getStart_time(), 3), "yyyy-MM-dd HH:mm:ss"));//开始后3小时
		paramMap.put("sponsor", challenge.getSponsor_useraccount());
		paramMap.put("receiver", challenge.getReceiver_useraccount());
		
		Date sttime = DateUtil.addMinute(challenge.getStart_time(),-challenge.getFloat_time());
		Date endtime = DateUtil.addMinute(challenge.getStart_time(),challenge.getFloat_time());
		
		List<ChallengeResult> challengeList = challengeMapper.queryTimeList(paramMap);
		for(ChallengeResult cr : challengeList){
			if(DateUtil.conflict(sttime, endtime
					, DateUtil.addMinute(cr.getStart_time(),-cr.getFloat_time()), DateUtil.addMinute(cr.getStart_time(),cr.getFloat_time()))){
				throw new AppRuntimeException("时间冲突不能约战");
			}
		}
	}

	@Override
	public Challenge queryChallenge(String id) {
		Challenge challenge = new Challenge();
		if(StringUtils.isNotEmpty(id)) {
			challenge = challengeMapper.queryChallenge(id);
			if(challenge == null) {
				throw new AppRuntimeException("当前约战信息不存在！");
			}
			
		}else {
			throw new AppRuntimeException("当前约战信息不存在！");
		}
		
		return challenge;
	}

	@Override
	public void setPassDueStatus(String id) {
		if(StringUtils.isNotEmpty(id)) {
			int count = challengeMapper.updateChallengeStatus(id);
			if(count < 1) {
				throw new AppRuntimeException("设置失败！");
			}
		}
	}
}
