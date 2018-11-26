package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import java.util.Date;
import javax.persistence.*;

@Table(name = "darts_wx_pay")
public class WxPay extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 微信开放平台审核通过的应用APPID
     */
    private String appid;

    /**
     * 微信支付分配的商户号
     */
    private String mch_id;

    /**
     * 设备号
     */
    private String device_info;

    /**
     * 随机字符串
     */
    private String nonce_str;

    /**
     * APP——需传入应用市场上的APP名字-实际商品名称，天天爱消除-游戏充值
     */
    private String body;

    /**
     * 商品名称明细列表
     */
    private String detail;

    /**
     * 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
     */
    private String attach;

    /**
     * 商户订单号
     */
    private String out_trade_no;

    /**
     * 符合ISO 4217标准的三位字母代码，默认人民币：CNY
     */
    private String fee_type;

    /**
     * 金额(以分为单位)
     */
    private Long total_fee;

    /**
     * 用户端实际ip
     */
    private String spbill_create_ip;

    /**
     * 订单生成时间，格式为yyyyMMddHHmmss
     */
    private String time_start;

    /**
     * 订单失效时间，格式为yyyyMMddHHmmss 最短失效时间间隔必须大于5分钟
     */
    private String time_expire;

    /**
     * 商品标记，代金券或立减优惠功能的参数，说明详见
     */
    private String goods_tag;

    /**
     * 支付类型
     */
    private String trade_type;

    /**
     * no_credit--指定不能使用信用卡支付
     */
    private String limit_pay;

    /**
     * 签名
     */
    private String sign;

    /**
     * SUCCESS/FAIL此字段是通信标识，非交易标识，交易是否成功需要查看resultcode来判断
     */
    private String return_code;

    /**
     * 返回信息
     */
    private String return_msg;

    /**
     * SUCCESS/FAIL
     */
    private String result_code;

    /**
     * 错误代码
     */
    private String err_code;

    /**
     * 错误信息描述
     */
    private String err_code_des;

    /**
     * 用户在商户appid下的唯一标识
     */
    private String openid;

    /**
     * 用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
     */
    private String is_subscribe;

    /**
     * 返回信息签名
     */
    private String return_sign;

    /**
     * 微信支付订单号
     */
    private String transaction_id;

    /**
     * 支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
     */
    private String time_end;

    /**
     * 商户退款号
     */
    private String out_refund_no;

    /**
     * 退款金额(分)
     */
    private Long refund_fee;

    /**
     * 退款操作员
     */
    private String op_user_id;

    /**
     * 微信退款号
     */
    private String refund_id;

    /**
     * 支付状态（0:未支付，1:已支付，2:支付失败,3:退款中,4:已退款,5:退款失败）
     */
    private Boolean pay_status;

    private Date add_time;

    /**
     * (1:有效;0:无效）
     */
    private Boolean isvalid;

    /**
     * 会员卡号
     */
    private String cardno;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取微信开放平台审核通过的应用APPID
     *
     * @return appid - 微信开放平台审核通过的应用APPID
     */
    public String getAppid() {
        return appid;
    }

    /**
     * 设置微信开放平台审核通过的应用APPID
     *
     * @param appid 微信开放平台审核通过的应用APPID
     */
    public void setAppid(String appid) {
        this.appid = appid;
    }

    /**
     * 获取微信支付分配的商户号
     *
     * @return mch_id - 微信支付分配的商户号
     */
    public String getMch_id() {
        return mch_id;
    }

    /**
     * 设置微信支付分配的商户号
     *
     * @param mch_id 微信支付分配的商户号
     */
    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    /**
     * 获取设备号
     *
     * @return device_info - 设备号
     */
    public String getDevice_info() {
        return device_info;
    }

    /**
     * 设置设备号
     *
     * @param device_info 设备号
     */
    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    /**
     * 获取随机字符串
     *
     * @return nonce_str - 随机字符串
     */
    public String getNonce_str() {
        return nonce_str;
    }

    /**
     * 设置随机字符串
     *
     * @param nonce_str 随机字符串
     */
    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    /**
     * 获取APP——需传入应用市场上的APP名字-实际商品名称，天天爱消除-游戏充值
     *
     * @return body - APP——需传入应用市场上的APP名字-实际商品名称，天天爱消除-游戏充值
     */
    public String getBody() {
        return body;
    }

    /**
     * 设置APP——需传入应用市场上的APP名字-实际商品名称，天天爱消除-游戏充值
     *
     * @param body APP——需传入应用市场上的APP名字-实际商品名称，天天爱消除-游戏充值
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * 获取商品名称明细列表
     *
     * @return detail - 商品名称明细列表
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 设置商品名称明细列表
     *
     * @param detail 商品名称明细列表
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * 获取附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
     *
     * @return attach - 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
     */
    public String getAttach() {
        return attach;
    }

    /**
     * 设置附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
     *
     * @param attach 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
     */
    public void setAttach(String attach) {
        this.attach = attach;
    }

    /**
     * 获取商户订单号
     *
     * @return out_trade_no - 商户订单号
     */
    public String getOut_trade_no() {
        return out_trade_no;
    }

    /**
     * 设置商户订单号
     *
     * @param out_trade_no 商户订单号
     */
    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    /**
     * 获取符合ISO 4217标准的三位字母代码，默认人民币：CNY
     *
     * @return fee_type - 符合ISO 4217标准的三位字母代码，默认人民币：CNY
     */
    public String getFee_type() {
        return fee_type;
    }

    /**
     * 设置符合ISO 4217标准的三位字母代码，默认人民币：CNY
     *
     * @param fee_type 符合ISO 4217标准的三位字母代码，默认人民币：CNY
     */
    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    /**
     * 获取金额(以分为单位)
     *
     * @return total_fee - 金额(以分为单位)
     */
    public Long getTotal_fee() {
        return total_fee;
    }

    /**
     * 设置金额(以分为单位)
     *
     * @param total_fee 金额(以分为单位)
     */
    public void setTotal_fee(Long total_fee) {
        this.total_fee = total_fee;
    }

    /**
     * 获取用户端实际ip
     *
     * @return spbill_create_ip - 用户端实际ip
     */
    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    /**
     * 设置用户端实际ip
     *
     * @param spbill_create_ip 用户端实际ip
     */
    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    /**
     * 获取订单生成时间，格式为yyyyMMddHHmmss
     *
     * @return time_start - 订单生成时间，格式为yyyyMMddHHmmss
     */
    public String getTime_start() {
        return time_start;
    }

    /**
     * 设置订单生成时间，格式为yyyyMMddHHmmss
     *
     * @param time_start 订单生成时间，格式为yyyyMMddHHmmss
     */
    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    /**
     * 获取订单失效时间，格式为yyyyMMddHHmmss 最短失效时间间隔必须大于5分钟
     *
     * @return time_expire - 订单失效时间，格式为yyyyMMddHHmmss 最短失效时间间隔必须大于5分钟
     */
    public String getTime_expire() {
        return time_expire;
    }

    /**
     * 设置订单失效时间，格式为yyyyMMddHHmmss 最短失效时间间隔必须大于5分钟
     *
     * @param time_expire 订单失效时间，格式为yyyyMMddHHmmss 最短失效时间间隔必须大于5分钟
     */
    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }

    /**
     * 获取商品标记，代金券或立减优惠功能的参数，说明详见
     *
     * @return goods_tag - 商品标记，代金券或立减优惠功能的参数，说明详见
     */
    public String getGoods_tag() {
        return goods_tag;
    }

    /**
     * 设置商品标记，代金券或立减优惠功能的参数，说明详见
     *
     * @param goods_tag 商品标记，代金券或立减优惠功能的参数，说明详见
     */
    public void setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
    }

    /**
     * 获取支付类型
     *
     * @return trade_type - 支付类型
     */
    public String getTrade_type() {
        return trade_type;
    }

    /**
     * 设置支付类型
     *
     * @param trade_type 支付类型
     */
    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    /**
     * 获取no_credit--指定不能使用信用卡支付
     *
     * @return limit_pay - no_credit--指定不能使用信用卡支付
     */
    public String getLimit_pay() {
        return limit_pay;
    }

    /**
     * 设置no_credit--指定不能使用信用卡支付
     *
     * @param limit_pay no_credit--指定不能使用信用卡支付
     */
    public void setLimit_pay(String limit_pay) {
        this.limit_pay = limit_pay;
    }

    /**
     * 获取签名
     *
     * @return sign - 签名
     */
    public String getSign() {
        return sign;
    }

    /**
     * 设置签名
     *
     * @param sign 签名
     */
    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * 获取SUCCESS/FAIL此字段是通信标识，非交易标识，交易是否成功需要查看resultcode来判断
     *
     * @return return_code - SUCCESS/FAIL此字段是通信标识，非交易标识，交易是否成功需要查看resultcode来判断
     */
    public String getReturn_code() {
        return return_code;
    }

    /**
     * 设置SUCCESS/FAIL此字段是通信标识，非交易标识，交易是否成功需要查看resultcode来判断
     *
     * @param return_code SUCCESS/FAIL此字段是通信标识，非交易标识，交易是否成功需要查看resultcode来判断
     */
    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    /**
     * 获取返回信息
     *
     * @return return_msg - 返回信息
     */
    public String getReturn_msg() {
        return return_msg;
    }

    /**
     * 设置返回信息
     *
     * @param return_msg 返回信息
     */
    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    /**
     * 获取SUCCESS/FAIL
     *
     * @return result_code - SUCCESS/FAIL
     */
    public String getResult_code() {
        return result_code;
    }

    /**
     * 设置SUCCESS/FAIL
     *
     * @param result_code SUCCESS/FAIL
     */
    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    /**
     * 获取错误代码
     *
     * @return err_code - 错误代码
     */
    public String getErr_code() {
        return err_code;
    }

    /**
     * 设置错误代码
     *
     * @param err_code 错误代码
     */
    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    /**
     * 获取错误信息描述
     *
     * @return err_code_des - 错误信息描述
     */
    public String getErr_code_des() {
        return err_code_des;
    }

    /**
     * 设置错误信息描述
     *
     * @param err_code_des 错误信息描述
     */
    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    /**
     * 获取用户在商户appid下的唯一标识
     *
     * @return openid - 用户在商户appid下的唯一标识
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设置用户在商户appid下的唯一标识
     *
     * @param openid 用户在商户appid下的唯一标识
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * 获取用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
     *
     * @return is_subscribe - 用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
     */
    public String getIs_subscribe() {
        return is_subscribe;
    }

    /**
     * 设置用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
     *
     * @param is_subscribe 用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
     */
    public void setIs_subscribe(String is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    /**
     * 获取返回信息签名
     *
     * @return return_sign - 返回信息签名
     */
    public String getReturn_sign() {
        return return_sign;
    }

    /**
     * 设置返回信息签名
     *
     * @param return_sign 返回信息签名
     */
    public void setReturn_sign(String return_sign) {
        this.return_sign = return_sign;
    }

    /**
     * 获取微信支付订单号
     *
     * @return transaction_id - 微信支付订单号
     */
    public String getTransaction_id() {
        return transaction_id;
    }

    /**
     * 设置微信支付订单号
     *
     * @param transaction_id 微信支付订单号
     */
    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    /**
     * 获取支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
     *
     * @return time_end - 支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
     */
    public String getTime_end() {
        return time_end;
    }

    /**
     * 设置支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
     *
     * @param time_end 支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
     */
    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    /**
     * 获取商户退款号
     *
     * @return out_refund_no - 商户退款号
     */
    public String getOut_refund_no() {
        return out_refund_no;
    }

    /**
     * 设置商户退款号
     *
     * @param out_refund_no 商户退款号
     */
    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    /**
     * 获取退款金额(分)
     *
     * @return refund_fee - 退款金额(分)
     */
    public Long getRefund_fee() {
        return refund_fee;
    }

    /**
     * 设置退款金额(分)
     *
     * @param refund_fee 退款金额(分)
     */
    public void setRefund_fee(Long refund_fee) {
        this.refund_fee = refund_fee;
    }

    /**
     * 获取退款操作员
     *
     * @return op_user_id - 退款操作员
     */
    public String getOp_user_id() {
        return op_user_id;
    }

    /**
     * 设置退款操作员
     *
     * @param op_user_id 退款操作员
     */
    public void setOp_user_id(String op_user_id) {
        this.op_user_id = op_user_id;
    }

    /**
     * 获取微信退款号
     *
     * @return refund_id - 微信退款号
     */
    public String getRefund_id() {
        return refund_id;
    }

    /**
     * 设置微信退款号
     *
     * @param refund_id 微信退款号
     */
    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }

    /**
     * 获取支付状态（0:未支付，1:已支付，2:支付失败,3:退款中,4:已退款,5:退款失败）
     *
     * @return pay_status - 支付状态（0:未支付，1:已支付，2:支付失败,3:退款中,4:已退款,5:退款失败）
     */
    public Boolean getPay_status() {
        return pay_status;
    }

    /**
     * 设置支付状态（0:未支付，1:已支付，2:支付失败,3:退款中,4:已退款,5:退款失败）
     *
     * @param pay_status 支付状态（0:未支付，1:已支付，2:支付失败,3:退款中,4:已退款,5:退款失败）
     */
    public void setPay_status(Boolean pay_status) {
        this.pay_status = pay_status;
    }

    /**
     * @return add_time
     */
    public Date getAdd_time() {
        return add_time;
    }

    /**
     * @param add_time
     */
    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }

    /**
     * 获取(1:有效;0:无效）
     *
     * @return isvalid - (1:有效;0:无效）
     */
    public Boolean getIsvalid() {
        return isvalid;
    }

    /**
     * 设置(1:有效;0:无效）
     *
     * @param isvalid (1:有效;0:无效）
     */
    public void setIsvalid(Boolean isvalid) {
        this.isvalid = isvalid;
    }

    /**
     * 获取会员卡号
     *
     * @return cardno - 会员卡号
     */
    public String getCardno() {
        return cardno;
    }

    /**
     * 设置会员卡号
     *
     * @param cardno 会员卡号
     */
    public void setCardno(String cardno) {
        this.cardno = cardno;
    }
}