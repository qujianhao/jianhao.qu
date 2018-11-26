package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class WxPayResult extends BaseResult implements Serializable{

    private Integer id;   //  主键
    private String appid;   //  微信开放平台审核通过的应用APPID
    private String mch_id;   //  微信支付分配的商户号
    private String device_info;   //  设备号
    private String nonce_str;   //  随机字符串
    private String body;   //  APP——需传入应用市场上的APP名字-实际商品名称，天天爱消除-游戏充值
    private String detail;   //  商品名称明细列表
    private String attach;   //  附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
    private String out_trade_no;   //  商户订单号
    private String fee_type;   //  符合ISO 4217标准的三位字母代码，默认人民币：CNY
    private Integer total_fee;   //  金额(以分为单位)
    private String spbill_create_ip;   //  用户端实际ip
    private String time_start;   //  订单生成时间，格式为yyyyMMddHHmmss
    private String time_expire;   //  订单失效时间，格式为yyyyMMddHHmmss 最短失效时间间隔必须大于5分钟
    private String goods_tag;   //  商品标记，代金券或立减优惠功能的参数，说明详见
    private String trade_type;   //  支付类型
    private String limit_pay;   //  no_credit--指定不能使用信用卡支付
    private String sign;   //  签名
    private String return_code;   //  SUCCESS/FAIL此字段是通信标识，非交易标识，交易是否成功需要查看resultcode来判断
    private String return_msg;   //  返回信息
    private String result_code;   //  SUCCESS/FAIL
    private String err_code;   //  错误代码
    private String err_code_des;   //  错误信息描述
    private String openid;   //  用户在商户appid下的唯一标识
    private String is_subscribe;   //  用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
    private String return_sign;   //  返回信息签名
    private String transaction_id;   //  微信支付订单号
    private String time_end;   //  支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
    private String out_refund_no;   //  商户退款号
    private Integer refund_fee;   //  退款金额(分)
    private String op_user_id;   //  退款操作员
    private String refund_id;   //  微信退款号
    private String pay_status;   //  支付状态（0:未支付，1:已支付，2:支付失败,3:退款中,4:已退款,5:退款失败）
    private String isvalid;   //  (1:有效;0:无效）
    private String cardno;   //  会员卡号

    public WxPayResult() {
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getAppid() {
        return this.appid;
    }
    public void setAppid(String appid) {
        this.appid = appid;
    }
    public String getMch_id() {
        return this.mch_id;
    }
    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }
    public String getDevice_info() {
        return this.device_info;
    }
    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }
    public String getNonce_str() {
        return this.nonce_str;
    }
    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }
    public String getBody() {
        return this.body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getDetail() {
        return this.detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public String getAttach() {
        return this.attach;
    }
    public void setAttach(String attach) {
        this.attach = attach;
    }
    public String getOut_trade_no() {
        return this.out_trade_no;
    }
    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }
    public String getFee_type() {
        return this.fee_type;
    }
    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }
    public Integer getTotal_fee() {
        return this.total_fee;
    }
    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }
    public String getSpbill_create_ip() {
        return this.spbill_create_ip;
    }
    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }
    public String getTime_start() {
        return this.time_start;
    }
    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }
    public String getTime_expire() {
        return this.time_expire;
    }
    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }
    public String getGoods_tag() {
        return this.goods_tag;
    }
    public void setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
    }
    public String getTrade_type() {
        return this.trade_type;
    }
    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }
    public String getLimit_pay() {
        return this.limit_pay;
    }
    public void setLimit_pay(String limit_pay) {
        this.limit_pay = limit_pay;
    }
    public String getSign() {
        return this.sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
    public String getReturn_code() {
        return this.return_code;
    }
    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }
    public String getReturn_msg() {
        return this.return_msg;
    }
    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }
    public String getResult_code() {
        return this.result_code;
    }
    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }
    public String getErr_code() {
        return this.err_code;
    }
    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }
    public String getErr_code_des() {
        return this.err_code_des;
    }
    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }
    public String getOpenid() {
        return this.openid;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    public String getIs_subscribe() {
        return this.is_subscribe;
    }
    public void setIs_subscribe(String is_subscribe) {
        this.is_subscribe = is_subscribe;
    }
    public String getReturn_sign() {
        return this.return_sign;
    }
    public void setReturn_sign(String return_sign) {
        this.return_sign = return_sign;
    }
    public String getTransaction_id() {
        return this.transaction_id;
    }
    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }
    public String getTime_end() {
        return this.time_end;
    }
    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }
    public String getOut_refund_no() {
        return this.out_refund_no;
    }
    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }
    public Integer getRefund_fee() {
        return this.refund_fee;
    }
    public void setRefund_fee(Integer refund_fee) {
        this.refund_fee = refund_fee;
    }
    public String getOp_user_id() {
        return this.op_user_id;
    }
    public void setOp_user_id(String op_user_id) {
        this.op_user_id = op_user_id;
    }
    public String getRefund_id() {
        return this.refund_id;
    }
    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }
    public String getPay_status() {
        return this.pay_status;
    }
    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }
    public String getIsvalid() {
        return this.isvalid;
    }
    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }
    public String getCardno() {
        return this.cardno;
    }
    public void setCardno(String cardno) {
        this.cardno = cardno;
    }
}
