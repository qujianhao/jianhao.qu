package com.wangtiansoft.KingDarts.modules.club.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.config.utils.MD5Util;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.modules.club.service.ClubInfoService;
import com.wangtiansoft.KingDarts.modules.club.service.ClubPlaceService;
import com.wangtiansoft.KingDarts.modules.equip.service.EquAuthService;
import com.wangtiansoft.KingDarts.modules.merchantAccount.service.MerchantAccountService;
import com.wangtiansoft.KingDarts.modules.sequence.service.SequenceService;
import com.wangtiansoft.KingDarts.persistence.base.BaseExample;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.ClubInfoMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.ClubPlaceMapper;
import com.wangtiansoft.KingDarts.persistence.entity.ClubInfo;
import com.wangtiansoft.KingDarts.persistence.entity.ClubPlace;
import com.wangtiansoft.KingDarts.persistence.entity.MerchantAccount;
import com.wangtiansoft.KingDarts.results.core.ClubInfoResult;
import com.wangtiansoft.KingDarts.results.core.ClubPlaceResult;
import com.wangtiansoft.KingDarts.results.core.EquAuthResult;

import tk.mybatis.mapper.entity.Example;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

import javax.annotation.Resource;

/**
* Created by wt-templete-helper.
 */
@Transactional
@Service("clubInfoService")
public class ClubInfoServiceImpl extends BaseService<ClubInfo, Integer> implements ClubInfoService{

    @Autowired
    private ClubInfoMapper clubInfoMapper;
    
    @Autowired
    private ClubPlaceMapper clubPlaceMapper;
    
    @Resource
    private ClubPlaceService clubPlaceService;
    
    @Resource
    private SequenceService sequenceService;
    
    @Resource
    private EquAuthService equAuthService;
    
    @Resource
	private MerchantAccountService merchantAccountService;

    @Override
    public BaseMapper getBaseMapper() {
        return clubInfoMapper;
    }

    @Override
    public Page<Map> queryClubInfoPageList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) clubInfoMapper.queryClubInfoList(paramMap);
    }
    
    @Override
    public Page<Map> queryAccountClubInfoList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) clubInfoMapper.queryAccountClubInfoList(paramMap);
    }
    
    @Override
    public Page<Map> queryAgentClubInfoList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) clubInfoMapper.queryAgentClubInfoList(paramMap);
    }
    
    @Override
    public ClubInfoResult saveClubInfo(ClubInfoResult clubInfoResult) {
    	ClubInfo clubInfo = new ClubInfo();
    	BeanUtils.copyProperties(clubInfoResult,clubInfo);
    	String cno = clubInfo.getCno();
		if (cno == null || "".equals(cno)) {
			cno = sequenceService.getNextSeq("club1", 1, 8);
			clubInfo.setCno(cno);
		}
		clubInfo.setResum(new BigDecimal(0));
		clubInfo.setIncomes(new BigDecimal(0));
		clubInfo.setExpends(new BigDecimal(0));
		clubInfo.setAdd_time(new Date());
		clubInfo.setIsvalid(1);
		//密码MD5加密
		MD5Util md5Util = new MD5Util();
		clubInfo.setC_password(md5Util.MD5Encode(clubInfo.getC_password(), "utf-8"));
		clubInfoMapper.insert(clubInfo);
		List<ClubPlaceResult> clubPlaceResultList = clubInfoResult.getClubPlaceResultList();
		for (int i = 0; i < clubPlaceResultList.size(); i++) {
			ClubPlace clubPlace = new ClubPlace();
			BeanUtils.copyProperties(clubPlaceResultList.get(i),clubPlace);
			String placeno = clubPlace.getPlaceno();
			if (placeno == null || "".equals(placeno)) {
				placeno = sequenceService.getNextSeq("clubPlace", 1, 8);
				clubPlace.setPlaceno(placeno);
			}
			if(clubPlace.getPlace_name()==null) {
				clubPlace.setPlace_name(clubInfo.getCname());
			}
			clubPlace.setCno(clubInfo.getCno());
			clubPlace.setAdd_time(new Date());
			clubPlace.setIsvalid(1);
			clubPlaceService.save(clubPlace);
		}
		//保存商户登录账户及密码
		MerchantAccount merchantAccount = new MerchantAccount();
		merchantAccount.setMerno(clubInfo.getCno());
		merchantAccount.setPassword(clubInfo.getC_password());
		merchantAccount.setMobile(clubInfo.getMobile());
		merchantAccount.setMertype(1);
		merchantAccount.setAccountnames(clubInfo.getCname());
		merchantAccount.setCreatetime(new Date());
		merchantAccount.setIsdelete(0);
		merchantAccountService.save(merchantAccount);
		return clubInfoResult;
    }
    
    @Override
    public Integer updateManagePrice(Integer id,BigDecimal manage_game_price,Date price_start_time,Date price_end_time) {
    	return clubInfoMapper.updateManagePrice(id, manage_game_price, price_start_time, price_end_time);
    }
    
    @Override
    public Map getClubInfoView(Integer id) {
    	Map map = new HashMap();
    	//查询俱乐部详情
    	ClubInfo clubInfo = clubInfoMapper.selectByPrimaryKey(id);
    	//查询营业场所详情
    	
    	Example example1 = new BaseExample(ClubPlace.class);
        example1.createCriteria().andEqualTo("cno", clubInfo.getCno());
        List<ClubPlace> clubPlaceList = clubPlaceMapper.selectByExample(example1);
    	
    	map.put("clubPlaceList", clubPlaceList);
    	map.put("clubInfo", clubInfo);
    	return map;
    }
    
    @Override
    public BigDecimal getPriceByEquNo(String equno) {
    	Map map = clubInfoMapper.getPriceByEquNo(equno);
    	String manage_game_price;
    	String game_price;
    	if(map!=null){
    		if(map.get("manage_game_price")!=null){
    			manage_game_price= map.get("manage_game_price").toString();
    			BigDecimal bd=new BigDecimal(manage_game_price);
    			return bd;
    		}
    		if(map.get("game_price")!=null){
    			game_price= map.get("game_price").toString();
    			BigDecimal bd=new BigDecimal(game_price);
    			return bd;
    		}
    	}
    	return null;
    }
    
    @Override
    public void commission(String cno,BigDecimal incomes){
    	Map<String,Object> paramMap = new HashMap<>();
    	paramMap.put("cno", cno);
    	paramMap.put("incomes", incomes);
    	paramMap.put("resum", incomes);
    	if(clubInfoMapper.rechargeCommission(paramMap)!=1){
    		throw new AppRuntimeException("更新俱乐部金额失败");
    	}
    }
    
    @Override
    public BigDecimal getResumByCno(String cno) {
    	return clubInfoMapper.getResumByCno(cno);
    }
    
    @Override
    public Page<Map> getClubListByAgno(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) clubInfoMapper.getClubListByAgno(paramMap);
    }
    
    @Override
    public ClubInfoResult updateCnoAuthByEquno(ClubInfoResult clubInfoResult) {
    	List<EquAuthResult> equAuthResultList = clubInfoResult.getEquAuthResultList();
    	for(EquAuthResult equAuthResult:equAuthResultList) {
    		if(equAuthResult.getEquno()==null||equAuthResult.getAuth_no()==null) {
    			throw new AppRuntimeException("系统错误，请联系管理员！");
    		}else {
    			equAuthService.updateCnoAuthByEquno(equAuthResult.getEquno(), equAuthResult.getAuth_no(),equAuthResult.getAuth_name());
    		}
    	}
    	return clubInfoResult;
    }
    
    @Override
    public Integer updatePasswordByCno(String c_password,String cno) {
    	return clubInfoMapper.updatePasswordByCno(c_password, cno);
    }
    
    @Override
    public void updateResumByCno(BigDecimal resum,String cno) {
    	Map<String,Object> paramMap = new HashMap<>();
    	paramMap.put("cno", cno);
    	paramMap.put("resum", resum);
    	if(clubInfoMapper.updateResumByCno(paramMap)!=1){
    		throw new AppRuntimeException("更新俱乐部金额失败");
    	}
    }
    
    @Override
    public Page<Map> queryClubOrder(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) clubInfoMapper.queryClubOrder(paramMap);
    }
}
