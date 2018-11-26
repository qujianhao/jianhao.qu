package com.wangtiansoft.KingDarts.modules.funcatchPrize.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.RandomUtil;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.modules.funcatchPrize.service.FuncatchPrizeService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.FuncatchPrizeMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.FuncatchWinnerMapper;
import com.wangtiansoft.KingDarts.persistence.entity.FuncatchPrize;

import tk.mybatis.mapper.entity.Example;

/**
* Created by wt-templete-helper.
 */
@Transactional
@Service("funcatchPrizeService")
public class FuncatchPrizeServiceImpl extends BaseService<FuncatchPrize, Integer> implements FuncatchPrizeService{

    @Autowired
    private FuncatchPrizeMapper funcatchPrizeMapper;
    
    @Override
    public BaseMapper getBaseMapper() {
        return funcatchPrizeMapper;
    }

    @Override
    public Page<Map> queryFuncatchPrizePageList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) funcatchPrizeMapper.queryFuncatchPrizeList(paramMap);
    }
    
    public List<FuncatchPrize> findValidFuncatchPrizeList() {
    	Example example=new Example(FuncatchPrize.class);
    	List<String> types=new ArrayList<>();
    	types.add("Y");
    	types.add("N");
    	example.createCriteria().andIn("is_physical", types);
    	return findAllByExample(example);
    }
    
    @Override
	public FuncatchPrize returnWinPrize(List<FuncatchPrize> funcatchPrizes) {
		/*FuncatchPrize funcatchPrizeHit = new FuncatchPrize();
		//获取一个[0,1]之间的随机数
		Random rdm = new Random(System.currentTimeMillis());
		BigDecimal rd = new BigDecimal(rdm.nextInt(100)).divide(new BigDecimal(100));
		BigDecimal base = new BigDecimal(0.00);
		
		for(int i = 0; i < funcatchPrizes.size(); ++i) {
			BigDecimal winRate = funcatchPrizes.get(i).getWin_rate();
			BigDecimal min = base;
			BigDecimal max = base.add(winRate);
			
			if(i == funcatchPrizes.size() - 1) {
				max = new BigDecimal(1);
			}
			
			base = max;
			
			double dMin = min.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			double dMax = max.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			if(rd.doubleValue() >= dMin && rd.doubleValue() < dMax) {
				funcatchPrizeHit = funcatchPrizes.get(i);	//命中的奖品信息
				break;
			}
			
		}
		return funcatchPrizeHit;*/
    	
    	FuncatchPrize funcatchPrizeHit = new FuncatchPrize();
    	Integer random  = Integer.parseInt(RandomUtil.getRandomNumber(4));
    	int base = 0;
    	for(FuncatchPrize funcatchPrize : funcatchPrizes) {
    		int winRange = funcatchPrize.getWin_rate().multiply(new BigDecimal(10000)).intValue();
    		int min = base;
    		int max = base + winRange;
    		if(random>=min && random<max){
    			return funcatchPrize;	//命中
    		}
    		if("谢谢参与".equals(funcatchPrize.getPrize_name())){
    			funcatchPrizeHit = funcatchPrize;	//如果都没命中的默认值
    		}
    		base = max;
    	}
    	return funcatchPrizeHit;
	}
    
    

	@Override
	public BigDecimal returnBalancePrize(FuncatchPrize funcatchPrize) {
		BigDecimal consumBalance=getConsumeBalance();
		if(funcatchPrize.getIs_physical().equals("Y")) {
			return consumBalance;
		}else {
			return consumBalance.subtract(funcatchPrize.getWorth());
		}
	}
	
	@Override
	public BigDecimal getConsumeBalance() {
		FuncatchPrize record=new FuncatchPrize();
    	record.setIs_delete(0);
    	record.setPosition_num(5);
    	return funcatchPrizeMapper.selectOne(record).getWorth();
	}
	
	@Override
	public Integer countWinRateFuncatchPrizeList() {
		return funcatchPrizeMapper.countWinRateFuncatchPrizeList();
	}
	
}
