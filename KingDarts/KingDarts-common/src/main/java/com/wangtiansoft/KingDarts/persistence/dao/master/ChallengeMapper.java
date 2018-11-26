package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Challenge;
import com.wangtiansoft.KingDarts.results.core.ChallengeResult;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

public interface ChallengeMapper extends BaseMapper<Challenge> {

    List<Map> queryChallengeList(Map paramMap);
    
    int updateSponsorMiss(String id);
	
	int updateReceiverMiss(String id);
	
	List<ChallengeResult> findReceiverChallengeList(ChallengeResult challenge);
	
	int updateReceiveStatus(Challenge challenge);
	
	Integer queryMissTimes(Map<String, Object> params);
	
	Integer updateChallengeStatus(String id);
	
	Challenge queryChallenge(String id);
	
	List<ChallengeResult> findAllList(ChallengeResult challenge);
	
	List<ChallengeResult> findSponsorChallengeList(ChallengeResult challenge);
	
	@Select("select * from darts_challenge where sponsor_order_no= #{orderNo} or receiver_order_no = #{orderNo}")
	Challenge queryByOrderNo(String orderNo);
	
	@Select("select * from darts_challenge where start_time >= str_to_date(#{startDate}, '%Y-%m-%d %H:%i:%s') "
			+ "and start_time <= str_to_date(#{endDate}, '%Y-%m-%d %H:%i:%s') "
			+ "and challenge_status = 3 "
			+ "and (sponsor_useraccount= #{sponsor} or receiver_useraccount = #{sponsor} or sponsor_useraccount = #{receiver} or receiver_useraccount = #{receiver})")
	List<ChallengeResult> queryTimeList(Map paramMap);
	
	@Select("select * from darts_challenge where start_time >= str_to_date(#{startDate}, '%Y-%m-%d %H:%i:%s') and start_time <= str_to_date(#{endDate}, '%Y-%m-%d %H:%i:%s') "
			+ "and (sponsor_useraccount= #{sponsor} or receiver_useraccount = #{receiver}) and challenge_status = 3")
	List<ChallengeResult> queryUserChallenge(Map paramMap);

}