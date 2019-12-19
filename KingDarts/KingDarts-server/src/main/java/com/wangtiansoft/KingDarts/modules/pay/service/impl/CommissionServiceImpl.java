package com.wangtiansoft.KingDarts.modules.pay.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.modules.agent.service.AgentInfoService;
import com.wangtiansoft.KingDarts.modules.club.service.ClubInfoService;
import com.wangtiansoft.KingDarts.modules.pay.service.CommissionService;
import com.wangtiansoft.KingDarts.modules.club.service.ClubMemberService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.CommissionMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.EquAuthMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Commission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

/**
* Created by wt-templete-helper.
 */
@Transactional
@Service("commissionService")
public class CommissionServiceImpl extends BaseService<Commission, Integer> implements CommissionService{

    @Autowired
    private CommissionMapper commissionMapper;
    @Autowired
    private EquAuthMapper equAuthMapper;
    @Autowired
    private ClubInfoService clubInfoService;
    @Autowired
    private AgentInfoService agentInfoService;
    @Autowired
    private ClubMemberService clubMemberService;

    @Override
    public BaseMapper getBaseMapper() {
        return commissionMapper;
    }

    @Override
    public Page<Map> queryClubCommissionList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) commissionMapper.queryClubCommissionList(paramMap);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveCommission(String userId,Long lftPayId,BigDecimal amount,String equno,String remark){
    	//判断设备编号是否为null，不等于null代表扫码充值，等于null代表微信充值
    	if(equno!=null) {
    		//扫码充值，可以取到设备编号
    		//查询设备所属俱乐部和代理商
        	Map<String,Object> paramMap = new HashMap<>();
        	paramMap.put("equno", equno);
        	paramMap.put("isvalid", 1);
        	List<Map> list = equAuthMapper.queryEquCommission(paramMap);
        	
        	Map equCommission = null;
        	if(list!=null&&list.size()>0){
        		equCommission = list.get(0);
        	}
        	if(equCommission==null){
        		return;
        	}
        	
        	int rate = 10;//换算比例，按10成分
        	
        	Commission commission = new Commission();
        	commission.setUser_id(userId);
        	commission.setLft_pay_id(lftPayId);
        	commission.setAmount(amount);//用户充值实际金额
        	commission.setEquno(equno);
        	if(isNotEmpty(equCommission,"agno")){
        		//有代理商
        		commission.setAgno(equCommission.get("agno").toString());
        		if(isNotEmpty(equCommission,"agentscale")){
        			//有代理商提成
        			commission.setAg_amount(amount.multiply(new BigDecimal(equCommission.get("agentscale").toString()).divide(new BigDecimal(rate))));
        		}else{
        			commission.setAg_amount(new BigDecimal(0));
        		}
        	}else{
        		commission.setAg_amount(new BigDecimal(0));
        	}
        	
        	commission.setCno(equCommission.get("auth_no").toString());
        	if(isNotEmpty(equCommission,"clubscale")){
        		//有俱乐部提成
        		if(commission.getAg_amount().compareTo(new BigDecimal(0)) == 1){
        			//如果代理商提成大于0，俱乐部需要从代理商提成中获取提成
        			commission.setC_amount(commission.getAg_amount().multiply(new BigDecimal(equCommission.get("clubscale").toString()).divide(new BigDecimal(rate))));
        			
        			commission.setAg_amount(commission.getAg_amount().multiply(new BigDecimal(equCommission.get("acompanyscale").toString()).divide(new BigDecimal(rate))));
        		}else{
        			//如果无代理商提成
        			commission.setC_amount(amount.multiply(new BigDecimal(equCommission.get("clubscale").toString()).divide(new BigDecimal(rate))));
        		}
        	}else{
        		commission.setC_amount(new BigDecimal(0));
        	}
        	commission.setPay_time(new Date());
        	commission.setRemark(remark);
        	commissionMapper.insert(commission);
        	
        	//更新代理商和俱乐部金额
        	if(commission.getC_amount().compareTo(new BigDecimal(0)) == 1){
        		//俱乐部提成大于0，更新俱乐部收入
        		clubInfoService.commission(commission.getCno(), commission.getC_amount());
        	}
        	if(commission.getAg_amount().compareTo(new BigDecimal(0)) == 1){
        		//代理商提成大于0，更新代理商收入
        		agentInfoService.commission(commission.getAgno(), commission.getAg_amount());
        	}
        	
        	//判断是否是会员，如果不是俱乐部会员则成为会员
        	clubMemberService.makeMember(commission.getCno(), userId);
    	}else {
    		//微信充值，归属平台俱乐部（凯帝狮爱镖体育）
    		Commission commission = new Commission();
        	commission.setUser_id(userId);
        	commission.setLft_pay_id(lftPayId);
        	commission.setAmount(amount);//用户充值实际金额
        	commission.setCno("11000059");
        	commission.setAgno(null);
        	commission.setEquno(null);
        	commission.setC_amount(new BigDecimal(0));
        	commission.setAg_amount(new BigDecimal(0));
        	commission.setPay_time(new Date());
        	commission.setRemark(remark);
        	commissionMapper.insert(commission);
    	}
    }
    
    @Override
    public Map getClubPutForwardAndRechargeAmount(String cno) {
    	Map map = new HashMap();
    	
    	//获取俱乐部可提现金额
    	BigDecimal cnoResum = clubInfoService.getResumByCno(cno);
    	
    	//查询充值金额总数
    	BigDecimal cnoTotalAmount = commissionMapper.getClubTotalAmountByCno(cno);
    	
    	map.put("cnoResum", cnoResum);
    	map.put("cnoTotalAmount", cnoTotalAmount);
    	return map;
    }
    
    @Override
    public Map getAgentPutForwardAndRechargeAmount(String agno) {
    	Map map = new HashMap();
    	
    	//获取代理商可提现金额
    	BigDecimal agnoResum = agentInfoService.getResumByAgno(agno);
    	
    	//查询充值金额总数
    	BigDecimal agnoTotalAmount = commissionMapper.getAgentTotalAmountByAgno(agno);
    	
    	map.put("agnoResum", agnoResum);
    	map.put("agnoTotalAmount", agnoTotalAmount);
    	return map;
    }
    
    @Override
    public Float getSencondProbability(String agno,String cno) {
    	return commissionMapper.getSencondProbability(agno, cno);
    }
    
    private boolean isNotEmpty(Map map,String name){
    	if(map.get(name)!=null&&!map.get(name).toString().equals("")&&!map.get(name).toString().equals("0")){
    		return true;
    	}
    	return false;
    }

	@Override
	public Page<Map> queryUserCommissionPageList(Map paramMap, PageBean pageBean) {
		paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) commissionMapper.queryUserCommissionList(paramMap);
	}
	
	@Override
	public BigDecimal getClubTotalAmountByCno(String cno) {
		return commissionMapper.getClubTotalAmountByCno(cno);
	}
	
	@Override
	public BigDecimal getAgentTotalAmountByAgno(String agno) {
		return commissionMapper.getAgentTotalAmountByAgno(agno);
	}
	
	@Override
	public BigDecimal getClubPartitionTotalAmountByCno(String cno) {
		return commissionMapper.getClubPartitionTotalAmountByCno(cno);
	}
	
	@Override
	public BigDecimal getAgentPartitionTotalAmountByAgno(String agno) {
		return commissionMapper.getAgentPartitionTotalAmountByAgno(agno);
	}

	@Override
	public List<Map> queryClubCommissionDetailList(List<String> timeList,Map paramMap,int type) {
		Map paramValue=new HashMap<>();
		if(timeList!=null) {
			List<Map> mapList=commissionMapper.queryClubCommissionDetailMonth(paramMap);
			int j=1;
			paramValue.put("query_month", paramMap.get("query_month"));
			for (String timeItme : timeList) {
				paramValue.put("pay_time", timeItme);
				List<Map> mapListChild = null;
				if(type == 1){//提成金额
					mapListChild = commissionMapper.queryClubCommissionDetailDay(paramValue);
				}else{//充值金额
					mapListChild = commissionMapper.queryClubCommissionPayDetailDay(paramValue);
				}
						
				for (int i = 0; i < mapList.size(); i++) {
					NumberFormat nf = NumberFormat.getInstance();
					mapList.get(i).put("row_num", nf.format(mapListChild.get(i).get("row_num")));
					if(mapList.get(i).get("month_amount")!=null 
							&& nf.format(mapList.get(i).get("month_amount")).equals("0")) {
						mapList.get(i).put("month_amount",null);
					}
					if(mapListChild.get(i).get("day_amount")!=null 
							&& !nf.format(mapListChild.get(i).get("day_amount")).equals("0")) {
						mapList.get(i).put("day"+j, mapListChild.get(i).get("day_amount"));
					}
				}
				j++;
			}
			return mapList;
		}else {
			return	commissionMapper.queryClubCommissionDetailMonth(paramMap);
		}
	}
	
	@Override
	public Page<Map> queryClubCommissionDetailPageList(Map paramMap,PageBean pageBean) {
		paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
		return (Page<Map>)commissionMapper.queryClubCommissionDetailMonth(paramMap);
	}

	@Override
	public int getDayRechargeCount(String agno,String dayTime) {
		int dayRechargeCount = commissionMapper.getDayRechargeCount(agno, dayTime);
		return dayRechargeCount;
	}

	@Override
	public int getMonthRechargeCount(String agno,String monthTime) {
		int monthRechargeCount = commissionMapper.getMonthRechargeCount(agno, monthTime);
		return monthRechargeCount;
	}
}
