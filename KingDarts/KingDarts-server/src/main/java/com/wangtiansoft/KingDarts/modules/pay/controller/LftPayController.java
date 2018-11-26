package com.wangtiansoft.KingDarts.modules.pay.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.config.lftpay.api.example.FrontClose;
import com.wangtiansoft.KingDarts.config.lftpay.api.example.FrontJsapi;
import com.wangtiansoft.KingDarts.config.lftpay.api.example.FrontQuery;
import com.wangtiansoft.KingDarts.config.lftpay.api.example.Main;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.pay.service.LftPayService;
import com.wangtiansoft.KingDarts.persistence.entity.LftPay;

@Controller
@RequestMapping("/a/lftPay")
public class LftPayController extends BaseController {

	@Resource
	private LftPayService lftPayService;
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	/**
     * 游戏点数支付充值
     * @param totalMoney 充值金额
     * @param game_balance 充值游戏点
     * @param give_game_balance 赠送游戏点
     * @return
     */
    @PreAuthorize("hasPermission('','_WXPAY:EDIT')")
    @PostMapping("/lftPay_EnrollPayParams")
    public
    @ResponseBody
    ApiResult lftPay_EnrollPayParams(final BigDecimal totalMoney,String openId,BigDecimal game_balance,BigDecimal give_game_balance,HttpServletResponse response) {
    	//商品描述
		String subject = "游戏点充值";
		String res = Main.requestAsPost(FrontJsapi.getReqparas(request,subject,String.valueOf(totalMoney),openId));
		System.out.println(res);
		//解析保存
		LftPay lftPay = new LftPay();
		//String json = StringUtils.substringAfter(res,"\n");
		JSONObject job = JSONObject.parseObject(res);
        JSONObject head = JSONObject.parseObject(job.get("head").toString());
        JSONObject body = JSONObject.parseObject(job.get("body").toString());
        lftPay.setMerchant_no(body.get("merchant_no").toString());
        lftPay.setSign(head.get("sign").toString());
        lftPay.setChannel_type(body.get("channel_type").toString());
        lftPay.setOut_trade_no(body.get("out_trade_no").toString());
        lftPay.setTrade_no(body.get("trade_no").toString());
        lftPay.setTotal_amount(totalMoney);
        lftPay.setFee_type("CNY");
        lftPay.setSpbill_create_ip(request.getRemoteAddr());
        lftPay.setSubject(subject);
        lftPay.setTime_start(new Date());
        lftPay.setPay_status(1);
        lftPay.setOpen_id("1634141234123411");
        lftPay.setGame_balance(game_balance);
        lftPay.setGive_game_balance(give_game_balance);
        lftPayService.save(lftPay);
        
        //加入缓存
        redisTemplate.opsForHash().put(Constants.pay_order_onpay, lftPay.getTrade_no(), JSON.toJSONString(lftPay));
        
		Map<String,Object> map = new HashMap<>();
		map.put("res", res);
        return ApiResult.success(map);
    }
    
    /**
     * 支付查询
     * @param out_trade_no 订单号
     * @param merchant_no 门店商户编号
     * @return
     */
    @PreAuthorize("hasPermission('','_WXPAY:EDIT')")
    @PostMapping("/lftPay_Orderquery")
    public
    @ResponseBody
    ApiResult lftPay_Orderquery(HttpServletResponse response) {
    	Map<String,Object> map = new HashMap<>();
    	List<LftPay> lftPayList = lftPayService.getLftPayByPayStatus(1);
    	for(LftPay lftPay : lftPayList) {
	        Boolean result = false;  
	        int count = 0;
	        while(!result) {
	            try {  
	                Thread.sleep(3 * 1000); //设置暂停的时间 3 秒  
	                count ++ ;  
	                String res = Main.requestAsPost(FrontQuery.getReqparas(lftPay.getOut_trade_no(),lftPay.getTrade_no())); 
	                String json = StringUtils.substringAfter(res,"\n");
	                JSONObject job = JSONObject.parseObject(json);
	                JSONObject body = JSONObject.parseObject(job.get("body").toString());
	                if(body.get("trade_status").equals("success")) {
	                	System.out.println("支付成功");
	                	LftPay lftPay1 = new LftPay();
	                	lftPay1.setOut_trade_no(lftPay.getOut_trade_no());
	                	lftPay1.setTrade_no(lftPay.getTrade_no());
	                	lftPay1.setPay_status(2);
	                	lftPay1.setTime_expire(new Date());
	                	lftPay1.setUpdate_time(new Date());
	                	lftPayService.updateLftPayOutTradeNoAndTradeNo(lftPay1);
	                	result = true;
	                	break ;
	                }else if(body.get("trade_status").equals("fail")) {
	                	System.out.println("支付失败");
	                	LftPay lftPay1 = new LftPay();
	                	lftPay1.setOut_trade_no(lftPay.getOut_trade_no());
	                	lftPay1.setTrade_no(lftPay.getTrade_no());
	                	lftPay1.setPay_status(3);
	                	lftPay1.setTime_expire(new Date());
	                	lftPay1.setUpdate_time(new Date());
	                	lftPayService.updateLftPayOutTradeNoAndTradeNo(lftPay1);
	                	result = true;
	                	break ;
	                }else if(body.get("trade_status").equals("closed")) {
	                	System.out.println("已关单");
	                	result = true;
	                	break ;
	                }else if(body.get("trade_status").equals("cancel")) {
	                	System.out.println("已撤销");
	                	result = true;
	                	break ;
	                }else if(body.get("trade_status").equals("paying")) {
	                	System.out.println("支付中");
	                }
	                //一分钟未支付完成则关单
	                if (count >= 20) {  
	                	System.out.println("支付超时，请重新支付");
	                	String closeMessage = Main.requestAsPost(FrontClose.getReqparas(lftPay.getOut_trade_no(),lftPay.getMerchant_no()));
	                	
	                	String json1 = StringUtils.substringAfter(closeMessage,"\n");
	            		JSONObject job1 = JSONObject.parseObject(json1);
	                    JSONObject body1 = JSONObject.parseObject(job1.get("body").toString());
	                    if(body1.get("is_success").equals("S")) {
	                    	LftPay lftPay1 = new LftPay();
		                	lftPay1.setOut_trade_no(lftPay.getOut_trade_no());
		                	lftPay1.setMerchant_no(lftPay.getMerchant_no());
		                	lftPay1.setPay_status(4);
		                	lftPay1.setTime_expire(new Date());
		                	lftPay1.setUpdate_time(new Date());
		                	lftPayService.updateLftPayOutTradeNoAndTradeNo(lftPay1);
		                	
		                	map.put("closeMessage", closeMessage);
		                    result = true;  
		                    break ;
	                    }
	                }
	            } catch (InterruptedException e) {
	                e.printStackTrace();  
	            }    
	        }
    	}
		//map.put("res", res);
        return ApiResult.success(map);
    }
}
