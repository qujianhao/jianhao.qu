package com.wangtiansoft.KingDarts.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "darts_lft_pay")
public class LftPay {

	/**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * 消费者用户标识(1.支付宝支付时，要求上送用户在支付宝唯一用户号user_id，获取流程 2.微信支付时，要求上送用户在商户subAppid下唯一标识openid，)
     */
    private String open_id;
    
    /**
     * 联富通门店商户编号 
     */
    private String merchant_no;
    
    /**
     * 支付渠道ALI：支付宝 WX：微信支付
     */
    private String channel_type;
    
    /**
     * 商户订单号
     */
    private String out_trade_no;
    
    /**
     * 流水号
     */
    private String trade_no;
    
    /**
     * 订单金额 	单位为元
     */
    private BigDecimal total_amount;
    
    /**
     * 商品标题
     */
    private String subject;

    /**
     * 终端IP
     */
    private String spbill_create_ip;

    /**
     * 货币类型
     */
    private String fee_type;
    
    /**
     * 商户操作员编号
     */
    private String operator_id;

    /**
     * 商户门店编号
     */
    private String store_id;

    /**
     * 设备号
     */
    private String device_info;

    /**
     * 交易起始时间
     */
    private Date time_start;

    /**
     * 交易结束时间
     */
    private Date time_expire;

    /**
     * 指定支付方式(指定该笔订单的支付方式 no_credit：指定不能使用信用卡支付pcredit：使用花呗支付（仅支付宝）)
     */
    private String limit_pay;

    /**
     * 通知地址(暂未开通)
     */
    private String notify_url;

    /**
     * 子商户公众账号ID
     */
    private String sub_appid;

    /**
     * 附加数据
     */
    private String attach;

    /**
     * 商品标记
     */
    private String goods_tag;

    /**
     * 订单包含的商品列表信息
     */
    private String goods_detail;

    /**
     * 支付宝的店铺编号
     */
    private String alipay_store_id;

    /**
     * 参与优惠计算的金额/可打折金额
     */
    private String discountable_amount;

    /**
     * 不参与优惠计算的金额/不可打折金额
     */
    private String undiscountable_amount;

    /**
     * 签名
     */
    private String sign;

    /**
     * 商品描述
     */
    private String body;

    /**
     * 支付状态（0:未支付，1:支付中，2：已支付，3支付失败，4支付超时）
     */
    private Integer pay_status;

    /**
     * (1:有效;0:无效）
     */
    private Boolean isvalid;

    /**
     * 会员卡号
     */
    private String cardno;

    /**
     * 充值游戏点数
     */
    private BigDecimal game_balance;

    /**
     * 赠送游戏点数
     */
    private BigDecimal give_game_balance;
    
    /**
     * 扫描设备的订单编号
     */
    private String order_no;
    
    private Date create_time;
    
    private Date update_time;
    
    /**
     * 充值设备编码
     */
    private String equno;
    
    /**
     * 比赛编号
     */
    private String raceno;

	public Long getId() {
		return id;
	}

	
	public String getOrder_no() {
		return order_no;
	}


	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}


	public BigDecimal getGame_balance() {
		return game_balance;
	}


	public void setGame_balance(BigDecimal game_balance) {
		this.game_balance = game_balance;
	}


	public BigDecimal getGive_game_balance() {
		return give_game_balance;
	}


	public void setGive_game_balance(BigDecimal give_game_balance) {
		this.give_game_balance = give_game_balance;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getOpen_id() {
		return open_id;
	}

	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}

	public String getMerchant_no() {
		return merchant_no;
	}

	public void setMerchant_no(String merchant_no) {
		this.merchant_no = merchant_no;
	}

	public String getChannel_type() {
		return channel_type;
	}

	public void setChannel_type(String channel_type) {
		this.channel_type = channel_type;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public BigDecimal getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(BigDecimal total_amount) {
		this.total_amount = total_amount;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public String getOperator_id() {
		return operator_id;
	}

	public void setOperator_id(String operator_id) {
		this.operator_id = operator_id;
	}

	public String getStore_id() {
		return store_id;
	}

	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public Date getTime_start() {
		return time_start;
	}

	public void setTime_start(Date time_start) {
		this.time_start = time_start;
	}

	public Date getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(Date time_expire) {
		this.time_expire = time_expire;
	}

	public String getLimit_pay() {
		return limit_pay;
	}

	public void setLimit_pay(String limit_pay) {
		this.limit_pay = limit_pay;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getSub_appid() {
		return sub_appid;
	}

	public void setSub_appid(String sub_appid) {
		this.sub_appid = sub_appid;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getGoods_tag() {
		return goods_tag;
	}

	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}

	public String getGoods_detail() {
		return goods_detail;
	}

	public void setGoods_detail(String goods_detail) {
		this.goods_detail = goods_detail;
	}

	public String getAlipay_store_id() {
		return alipay_store_id;
	}

	public void setAlipay_store_id(String alipay_store_id) {
		this.alipay_store_id = alipay_store_id;
	}

	public String getDiscountable_amount() {
		return discountable_amount;
	}

	public void setDiscountable_amount(String discountable_amount) {
		this.discountable_amount = discountable_amount;
	}

	public String getUndiscountable_amount() {
		return undiscountable_amount;
	}

	public void setUndiscountable_amount(String undiscountable_amount) {
		this.undiscountable_amount = undiscountable_amount;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Integer getPay_status() {
		return pay_status;
	}

	public void setPay_status(Integer pay_status) {
		this.pay_status = pay_status;
	}

	public Boolean getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getEquno() {
		return equno;
	}

	public void setEquno(String equno) {
		this.equno = equno;
	}

	public String getRaceno() {
		return raceno;
	}

	public void setRaceno(String raceno) {
		this.raceno = raceno;
	}
    
    
}
