package com.wangtiansoft.KingDarts.modules.api.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.SmsUtil;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.agent.service.AgentInfoService;
import com.wangtiansoft.KingDarts.modules.club.service.ClubInfoService;
import com.wangtiansoft.KingDarts.modules.merchantAccount.service.MerchantAccountService;
import com.wangtiansoft.KingDarts.modules.merchantWdcash.service.MerchantWdcashService;
import com.wangtiansoft.KingDarts.modules.pay.service.CommissionService;
import com.wangtiansoft.KingDarts.modules.user.service.UserBalanceService;
import com.wangtiansoft.KingDarts.persistence.entity.MerchantAccount;
import com.wangtiansoft.KingDarts.persistence.entity.MerchantWdcash;

/**
 * 商户端财务管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/finance")
public class WeixinFinanceController extends BaseController {

	private final static Logger logger = LoggerFactory.getLogger(WeixinFinanceController.class);
	
	@Autowired
	private CommissionService commissionService;
	@Autowired
	private ClubInfoService clubInfoService;
	@Autowired
	private AgentInfoService agentInfoService;
	@Autowired
	private MerchantWdcashService merchantWdcashService;
	@Autowired
	private UserBalanceService userBalanceService;
	@Resource
	private MerchantAccountService merchantAccountService;
	@Autowired
    private RedisTemplate redisTemplate;
	
	private Long EXPIRES_SECONDS = new Long(300);//验证码超时时间
	private String cashCodeCache = "cashCodeCache";
	
	/**
	 * 根据代理商编号查询俱乐部列表并获取授权的设备数量
	 * @param agno  代理商编号
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/agentlist")
	public@ResponseBody ApiResult agentlist(final String page,final String rows) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String auth_no) throws Exception {
				Map<String,Object> paramMap=new HashMap<>();
				paramMap.put("agno", auth_no);
				PageBean pageBean=new PageBean();
				pageBean.setPage(Integer.parseInt(page));
				pageBean.setRows(Integer.parseInt(rows));
				Map<String,Object> map = new HashMap<>();
				
				Map<String,Object> clubPage=new HashMap<>();
				Page<Map> clublist=clubInfoService.getClubListByAgno(paramMap,pageBean);
				clubPage.put("total", clublist.getTotal());
				clubPage.put("pageSize", clublist.getPageSize());
				clubPage.put("pageNum", clublist.getPageNum());
				clubPage.put("pages", clublist.getPages());
				clubPage.put("clublist", clublist);
				map.put("clubPage", clubPage);
				return map;
			}
		});
		return result;
	}
	
	
	/*@RequestMapping("/test")
	public@ResponseBody ApiResult test(final String userId,final Long payId,BigDecimal amount,String equno) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String auth_no) throws Exception {
				Map<String,Object> paramMap=new HashMap<>();
				commissionService.saveCommission(userId, payId, amount, equno);
				return paramMap;
			}
		});
		return result;
	}*/
	
	/**
	 * 获取俱乐部充值记录列表
	 * @param cno 俱乐部编号
	 * @param pay_time 充值时间
	 * @param country 国家
	 * @param province 省份
	 * @param city 城市
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/rechargelist")
	public@ResponseBody ApiResult rechargelist(final String cno,final String pay_time,final String country,
			final String province,final String city,final String areas,final String page,final String rows) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String auth_no) throws Exception {
				Map<String,Object> paramMap=new HashMap<>();
				if(cno==null) {
					paramMap.put("cno", auth_no);
				}else {
					paramMap.put("cno", cno);
				}
				paramMap.put("pay_time", pay_time);
				paramMap.put("country", country);
				paramMap.put("province", StringUtils.isNotEmpty(province)?province.replace("省", "").replace("自治区", ""):province);
				paramMap.put("city", city);
				paramMap.put("areas", areas);
				PageBean pageBean=new PageBean();
				pageBean.setPage(Integer.parseInt(page));
				pageBean.setRows(Integer.parseInt(rows));
				Map<String,Object> map = new HashMap<>();
				
				Map<String,Object> rechargePage=new HashMap<>();
				Page<Map> rechargelist=commissionService.queryClubCommissionList(paramMap,pageBean);
				rechargePage.put("total", rechargelist.getTotal());
				rechargePage.put("pageSize", rechargelist.getPageSize());
				rechargePage.put("pageNum", rechargelist.getPageNum());
				rechargePage.put("pages", rechargelist.getPages());
				rechargePage.put("rechargelist", rechargelist);
				map.put("rechargePage", rechargePage);
				return map;
			}
		});
		return result;
	}
	
	/**
	 * 获取俱乐部消费明细列表
	 * @param cno 俱乐部编号
	 * @param log_time 消费时间
	 * @param country 国家
	 * @param province 省份
	 * @param city 城市
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/consumptionlist")
	public@ResponseBody ApiResult consumptionlist(final String cno,final String log_time,final String country,
			final String province,final String city,final String areas,final String page,final String rows) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String auth_no) throws Exception {
				Map<String,Object> paramMap=new HashMap<>();
				if(cno==null) {
					paramMap.put("cno", auth_no);
				}else {
					paramMap.put("cno", cno);
				}
				paramMap.put("log_time", log_time);
				paramMap.put("country", country);
				paramMap.put("province", StringUtils.isNotEmpty(province)?province.replace("省", "").replace("自治区", ""):province);
				paramMap.put("city", city);
				paramMap.put("areas", areas);
				PageBean pageBean=new PageBean();
				pageBean.setPage(Integer.parseInt(page));
				pageBean.setRows(Integer.parseInt(rows));
				Map<String,Object> map = new HashMap<>();
				
				Map<String,Object> consumptionPage=new HashMap<>();
				Page<Map> consumptionlist=userBalanceService.queryUserConsumptionDetails(paramMap,pageBean);
				consumptionPage.put("total", consumptionlist.getTotal());
				consumptionPage.put("pageSize", consumptionlist.getPageSize());
				consumptionPage.put("pageNum", consumptionlist.getPageNum());
				consumptionPage.put("pages", consumptionlist.getPages());
				consumptionPage.put("consumptionlist", consumptionlist);
				map.put("consumptionPage", consumptionPage);
				return map;
			}
		});
		return result;
	}
	
	
	/**
	 * 获取俱乐部会员充值金额总数及可提现金额
	 * @param cno 俱乐部编号
	 * @return  cnoResum可提现金额 ，cnoTotalAmount会员充值金额
	 */
	@RequestMapping("/clubAmount")
	public@ResponseBody ApiResult clubAmount(final String cno) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String auth_no) throws Exception {
				Map<String,Object> map = new HashMap<>();
				if(cno==null) {
					map.put("clubAmount", commissionService.getClubPutForwardAndRechargeAmount(auth_no));
				}else {
					map.put("clubAmount", commissionService.getClubPutForwardAndRechargeAmount(cno));
				}
				return map;
			}
		});
		return result;
	}
	
	/**
	 * 获取代理商下俱乐部会员充值金额总数及可提现金额
	 * @param agno 代理商编号
	 * @return  agnoResum可提现金额 ，agnoTotalAmount会员充值金额
	 */
	@RequestMapping("/agentAmount")
	public@ResponseBody ApiResult agentAmount() {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String auth_no) throws Exception {
				Map<String,Object> map = new HashMap<>();
				map.put("agentAmount", commissionService.getAgentPutForwardAndRechargeAmount(auth_no));
				return map;
			}
		});
		return result;
	}
	
	
	
	/**
	 * 截止目前当天充值总金额
	 * @return
	 */
	@RequestMapping(value="/dayRechargeCount", method = RequestMethod.POST)
	public @ResponseBody ApiResult dayRechargeCount(){
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			
			@Override
			public Object execute(String auth_no) throws Exception {
				SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
				String format = sFormat.format(new Date());
					Map<String,Object> map = new HashMap<>();
					int dayRechargeCount = 0;
					try {
						dayRechargeCount = commissionService.getDayRechargeCount(auth_no, format);
						map.put("code", 0);
						map.put("msg", "success");
					} catch (Exception e) {
						map.put("code", 1);
						map.put("msg", "fail");
					}
					map.put("rechargeNum", dayRechargeCount);
					return map;
			}
		});
		return result;
	}

	
	/**
	 * 截止日前月充值总金额
	 * @return
	 */
	@RequestMapping(value="/monthRechargeCount", method = RequestMethod.POST)
	public @ResponseBody ApiResult monthRechargeCount(){
		
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String auth_no) throws Exception {
				SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM");
				String format = sFormat.format(new Date());
				Map<String,Object> map = new HashMap<>();
				int monthRechargeCount = 0;
				try {
					map.put("code", 0);
					monthRechargeCount = commissionService.getMonthRechargeCount(auth_no, format);
					map.put("msg", "success");
				} catch (Exception e) {
					map.put("code", 1);
					map.put("msg", "fail");
				}
				map.put("rechargeNum", monthRechargeCount);
				return map;
			}
		});
		return result;
	}

	
	/**
	 * 微信提现校验码
	 */
	@RequestMapping(value="/cashcode", method = RequestMethod.POST)
	public @ResponseBody ApiResult verifyCode(HttpServletResponse response) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String auth_no) throws Exception {
				MerchantAccount merchantAccount = merchantAccountService.getUserByLoginName(auth_no);
				
				Random rand=new Random();
				int code=100000+rand.nextInt(900000);
				redisTemplate.boundValueOps(cashCodeCache+merchantAccount.getMobile()).set(code+"", EXPIRES_SECONDS, TimeUnit.SECONDS);
				
				SendSmsResponse response = SmsUtil.sendCashCodeSms(merchantAccount.getMobile(),code+"");
				if(!"OK".equals(response.getCode())){
					throw new AppRuntimeException("短信发送失败");
				}
				Map<String,Object> map = new HashMap<>();
				map.put("msg", "发送成功");
				return map;
				
			}
		});
		return result;
	}
	/**
	 * 微信提现
	 * @param merchantWdcash 商户提现
	 * cash_nums申请提现金额，wd_memos备注
	 * @return
	 */
	@RequestMapping("/cashApply")
	public@ResponseBody ApiResult cashApply(final MerchantWdcash merchantWdcash,final String code) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String auth_no) throws Exception {
				MerchantAccount merchantAccount = merchantAccountService.getUserByLoginName(auth_no);
				
				Object obj=redisTemplate.boundValueOps(cashCodeCache+merchantAccount.getMobile()).get();
				if(obj==null||!StringUtils.equals(obj.toString(), code)){
					throw new AppRuntimeException("验证码错误！");
				}
				BigDecimal resum01 = new BigDecimal("1");
				int resumcount = resum01.compareTo(merchantWdcash.getCash_nums());
				if(resumcount== -1) {
					if(merchantAccount.getMertype()==1) {
						merchantWdcash.setMer_type(1);//俱乐部
						BigDecimal cnoResum = clubInfoService.getResumByCno(auth_no);
						int resum = cnoResum.compareTo(merchantWdcash.getCash_nums());
						if(resum==0||resum==1) {
							//修改账户余额
							clubInfoService.updateResumByCno(merchantWdcash.getCash_nums().negate(), auth_no);
						}else {
							throw new AppRuntimeException("您输入的提现金额过大！");
						}
					}else {
						merchantWdcash.setMer_type(2);//代理商
						BigDecimal agnoResum = agentInfoService.getResumByAgno(auth_no);
						int resum = agnoResum.compareTo(merchantWdcash.getCash_nums());
						if(resum==0||resum==1) {
							//修改账户余额
							agentInfoService.updateResumByAgno(merchantWdcash.getCash_nums().negate(), auth_no);
						}else {
							throw new AppRuntimeException("您输入的提现金额过大！");
						}
					}
				}else {
					throw new AppRuntimeException("提现金额须大于1元！");
				}
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		        String newDate=sdf.format(new Date());
		        String result="";
		        Random random=new Random();
		        for(int i=0;i<3;i++){
		            result+=random.nextInt(10);
		        }
		        merchantWdcash.setPay_order_no(newDate+result);//生成订单编号
				merchantWdcash.setMerno(merchantAccount.getMerno());
				merchantWdcash.setMe_rmobile(merchantAccount.getMobile());
				merchantWdcash.setOpenid(merchantAccount.getOpenid());
				merchantWdcash.setWd_status(2);
				merchantWdcash.setPay_types(4);
				merchantWdcash.setApply_time(new Date());
				merchantWdcashService.save(merchantWdcash);
				Map<String,Object> map = new HashMap<>();
				map.put("Pay_order_no", merchantWdcash.getPay_order_no());
				return map;
			}
		});
		return result;
	}
	
	
	/*@InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }*/
}
