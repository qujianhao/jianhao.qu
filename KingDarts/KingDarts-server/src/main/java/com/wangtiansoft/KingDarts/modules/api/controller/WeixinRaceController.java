package com.wangtiansoft.KingDarts.modules.api.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.club.service.ClubInfoService;
import com.wangtiansoft.KingDarts.modules.club.service.ClubPlaceService;
import com.wangtiansoft.KingDarts.modules.merchantAccount.service.MerchantAccountService;
import com.wangtiansoft.KingDarts.modules.race.service.RaceEnterforUserService;
import com.wangtiansoft.KingDarts.modules.race.service.RaceInfoService;
import com.wangtiansoft.KingDarts.persistence.entity.ClubInfo;
import com.wangtiansoft.KingDarts.persistence.entity.ClubPlace;
import com.wangtiansoft.KingDarts.persistence.entity.EquAuth;
import com.wangtiansoft.KingDarts.persistence.entity.MerchantAccount;
import com.wangtiansoft.KingDarts.persistence.entity.RaceEnterforUser;
import com.wangtiansoft.KingDarts.persistence.entity.RaceInfo;
import com.wangtiansoft.KingDarts.results.core.ClubInfoResult;
import com.wangtiansoft.KingDarts.results.core.RaceEnterforUserResult;
import com.wangtiansoft.KingDarts.results.core.RaceInfoResult;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 商户端比赛管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/race")
public class WeixinRaceController extends BaseController {

	private final static Logger logger = LoggerFactory.getLogger(WeixinRaceController.class);
	
	@Resource
	private RaceInfoService raceInfoService;
	
	@Resource
	private MerchantAccountService merchantAccountService;
	
	@Resource
	private RaceEnterforUserService raceEnterforUserService;
	
	@Resource
	private ClubInfoService clubInfoService;
	
	@Resource
	private ClubPlaceService clubPlaceService;
	
	/**
	 * 俱乐部添加比赛
	 * @param raceInfoResult
	 * @return
	 */
	@RequestMapping(value="/addRace",consumes = "application/json; charset=utf-8")
	public@ResponseBody ApiResult addRace(@RequestBody final RaceInfoResult raceInfoResult) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String auth_no) throws Exception {
				Date date = new Date();
				//设置要获取到年月的时间
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
				Integer count = raceInfoService.getCountByMonth(auth_no, sdf.format(date));
				if(count==3||count>3) {
					throw new AppRuntimeException("该月最多举办三场比赛！");
				}
				if(raceInfoResult.getMinimum_num()!=8&&raceInfoResult.getMinimum_num()!=16&&raceInfoResult.getMinimum_num()!=32) {
					throw new AppRuntimeException("请重新选择最低报名人数！");
				}
				
				if(raceInfoResult.getMinimum_num()==16) {
					Integer count1 = raceInfoService.getCountByCnoAndPersonNum(auth_no, 8);
					if(count1<1) {
						throw new AppRuntimeException("您不具备发布最低报名人数为16人的比赛！");
					}
				}
				if(raceInfoResult.getMinimum_num()==32) {
					Integer count1 = raceInfoService.getCountByCnoAndPersonNum(auth_no, 16);
					if(count1<1) {
						throw new AppRuntimeException("您不具备发布最低报名人数为32人的比赛！");
					}
				}
				
				//将时间转换成Int类型再做比较
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
				String nowD = dateFormat.format(new Date());
				String regstart = dateFormat.format(raceInfoResult.getRegstart());
				String regend = dateFormat.format(raceInfoResult.getRegend());
				String racestart = dateFormat.format(raceInfoResult.getRacestart());
				String raceend = dateFormat.format(raceInfoResult.getRaceend());
				int nowD1 = Integer.parseInt(nowD);//当前时间
				int regstart1 = Integer.parseInt(regstart);//报名开始时间
				int regend1 = Integer.parseInt(regend);//报名结束时间
				int racestart1 = Integer.parseInt(racestart);//比赛开始时间
				int raceend1 = Integer.parseInt(raceend);//比赛结束时间
				
				if(raceInfoResult.getRegend().before(new Date())&&nowD1!=regend1) {
					throw new AppRuntimeException("报名结束时间必须大于当前时间，请合理分配时间！");
				}
				if(raceInfoResult.getRacestart().before(raceInfoResult.getRegend())||regend1==racestart1) {
					throw new AppRuntimeException("比赛开始时间必须大于报名结束时间，请合理分配时间！");
				}
				//判断当前登录用户是否存在，并获取俱乐部信息
				Example example1=new Example(ClubInfo.class);
		        Criteria cr1= example1.createCriteria();
		        cr1.andEqualTo("cno", auth_no);
		        ClubInfo clubInfo = clubInfoService.findOneByExample(example1);
		        if(clubInfo==null) {
		        	throw new AppRuntimeException("系统错误，请联系管理员！");
		        }
		        //获取俱乐部地址
		        Example example2=new Example(ClubPlace.class);
		        Criteria cr2= example2.createCriteria();
		        cr2.andEqualTo("cno", auth_no);
		        ClubPlace clubPlace = clubPlaceService.findOneByExample(example2);
		        
				raceInfoResult.setCname(clubInfo.getCname());
				raceInfoResult.setRaceplace(clubPlace.getAddress());
				raceInfoResult.setCno(auth_no);
		        if(nowD1<regstart1) {
		        	//待报名
		        	raceInfoResult.setDstatus(0);
		        }
		        if(regstart1<=nowD1&&regend1>=nowD1) {
					//报名中
		        	raceInfoResult.setDstatus(1);
				}
		        RaceInfo raceInfo = raceInfoService.saveRaceInfo(raceInfoResult);
				return raceInfo;
			}
		});
		return result;
	}
	
	/**
	 * 赛事管理
	 * @param page
	 * @param rows
	 * @param dstatus 0.待报名,1.报名中，2进行中，3已结束，4已取消，5已解散  (赛事管理下面不传)
	 * @return
	 */
	@RequestMapping("/racelist")
	public@ResponseBody ApiResult racelist(final String page,final String rows,final String dstatus) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String auth_no) throws Exception {
				Map<String,Object> paramMap=new HashMap<>();
				paramMap.put("cno", auth_no);
				if(dstatus!=null) {
					paramMap.put("dstatus", Integer.parseInt(dstatus));
				}
				PageBean pageBean=new PageBean();
				pageBean.setPage(Integer.parseInt(page));
				pageBean.setRows(Integer.parseInt(rows));
				Map<String,Object> map = new HashMap<>();
				
				Map<String,Object> Page=new HashMap<>();
				Page<Map> list=raceInfoService.queryRaceInfoList(paramMap,pageBean);
				Page.put("total", list.getTotal());
				Page.put("pageSize", list.getPageSize());
				Page.put("pageNum", list.getPageNum());
				Page.put("pages", list.getPages());
				Page.put("list", list);
				map.put("racePage", Page);
				return map;
			}
		});
		return result;
	}
	
	/**
	 * 赛事管理详情
	 * @param raceno赛事编号
	 * @return
	 */
	@RequestMapping("/racedetails")
	public@ResponseBody ApiResult racedetails(final String raceno) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String auth_no) throws Exception {
				int rankingStatus = 0;//是否发布排名
				Map<String,Object> map=new HashMap<>();
		        RaceInfoResult raceInfoResult = raceInfoService.getRaceByRaceNo(raceno);
		        
		        List<RaceEnterforUserResult> raceEnterforUserResultList = raceEnterforUserService.getRaceUserByRaceno(raceno);
		        
		        if(raceInfoResult.getDstatus()==3) {
		        	if(raceEnterforUserResultList!=null) {
		        		if(raceEnterforUserResultList.get(0).getRanking()==null) {
		        			rankingStatus = 1;
		        		}
		        	}
		        }
		        map.put("rankingStatus", rankingStatus);
		        map.put("raceInfoResult", raceInfoResult);
		        map.put("raceEnterforUserResultList", raceEnterforUserResultList);
				return map;
			}
		});
		return result;
	}
	
	/**
	 * 取消比赛
	 * @param raceno赛事编号
	 * @return
	 */
	@RequestMapping("/cancelRace")
	public@ResponseBody ApiResult cancelRace(final String raceno) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String auth_no) throws Exception {
				Map<String,Object> map = new HashMap<>();
				Example example1=new Example(RaceInfo.class);
		        Criteria cr1= example1.createCriteria();
		        cr1.andEqualTo("raceno", raceno);
		        RaceInfo raceInfo = raceInfoService.findOneByExample(example1);
		        if(raceInfo.getDstatus()==0||raceInfo.getDstatus()==1) {
		        	raceInfo.setDstatus(4);
		        	map.put("count", raceInfoService.updateByIdSelective(raceInfo));
		        	return map;
		        }else {
		        	throw new AppRuntimeException("赛事不可取消，请检查状态！");
		        }
			}
		});
		return result;
	}
	
	/**
	 * 发布到TOP榜  设置排名
	 * @param raceEnterforUserList
	 * @return
	 */
	@RequestMapping(value="/setRanking",consumes = "application/json; charset=utf-8")
	public @ResponseBody ApiResult setRanking(@RequestBody final RaceInfoResult raceInfoResult) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String auth_no) throws Exception {
				if(raceInfoResult.getRaceEnterforUserLists()==null) {
					throw new AppRuntimeException("系统错误，请联系管理员！");
				}
				
				Integer count = raceEnterforUserService.saveRanking(raceInfoResult.getRaceEnterforUserLists());
				return count;
			}
		});
		return result;
	}
}
