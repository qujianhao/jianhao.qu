package com.wangtiansoft.KingDarts.constants;

/**
 * Created by Administrator on 2017/3/10 0010.
 */
public class Constants {

    //  全局配置
    public final static int False = 0;
    public final static int True = 1;

    public final static String kCode_Success        = "0";          //  成功
    public final static String kCode_Fail           = "1";          //  失败
    public final static String kCode_SessionError   = "1000";       //  登陆超时
    public final static String kCode_UnAuth         = "2000";       //  没有权限
    
    public final static String kCode_Null         = "30";       	//  参数为空
    public final static String kCode_NoWxAuth         = "40";       	//  微信授权失败
    public final static String kCode_NoUser         = "101";       //  用户不存在
    public final static String kCode_HasUser         = "102";       //  用户已存在
    public final static String kCode_balance_not_enough         = "103";       //  余额不足
    
    public final static String kCode_NoOrder         = "201";       //  订单不存在
    public final static String kCode_OrderStatusFail     = "202";       //  订单状态错误
    public final static String kCode_OrderPayStatusFail     = "203";       //  订单支付状态错误
    public final static String kCode_Boss_killed     = "205";       //  大富豪boss已被杀死
    
    public final static String kCode_NoEqu         = "401";       	//  登录失败
    public final static String kCode_HasLogin         = "402";       	//  设备已登录
    public final static String kCode_NotInit         = "403";       	//  服务器重启中
    
    public final static String price_def         = "5";       	//  默认5个游戏点1次

    //  后台管理系统单页记录数量
    public final static int kAdmin_PageSize = 20;
    public final static int kApi_PageSize = 20;

    //  接口token缓存
    public static final long TOKEN_EXPIRES_DAY = 7;

    //  认证信息
    public static final String kAuth_tokenResult = "tokenResult";
    public static final String kAuth_xAccessToken = "x-access-token";
    
    //在线状态长连接，map集合缓存
    public static final String online_channel = "online_channel";//缓存设备登录状态信息，主键equno
    public static final String channel_equno = "channel_equno";//设备编码与通道ID关系，主键channelid
    
    public static final String channel_unlogin = "channel_unlogin_";//长连接未登录状态记录
    public static final int channel_unlogin_num = 8;//长连接未登录超过次数，断开连接
    public static final Long channel_unlogin_time = 60L;//长连接未登录超时
    
    public static final String game_user_wait = "game_user_wait";//待匹配的约战用户
    public static final String game_equno_wait = "game_equno_wait_";//待匹配的设备,后面添加游戏编码
    public static final String equno_net = "equno_net";//网络对账匹配缓存
    
    public static final String challenge_orderno = "challenge_orderno";//约战缓存
    public static final String challenge_equno_wait = "challenge_equno_wait";//定时轮询，如果存在进行进行匹配
    
    //小程序商户类型
    public static final String merchant = "merchant";

    //支付中订单缓存
    public static final String pay_order_onpay = "onpay_order";//支付中
    public static final String pay_order_onpay_app = "onpay_order_app";//小程序支付中
    public static final String pay_order_notice = "pay_notice_";//支付成功或支付失败
    
    public static final String pay_order_jspay = "jspay_order";//js判断已经支付
    
    //1是 2否
	public static final int YES = 1;
	public static final int NO = 0;
    
    //设备离线状态
    public static final int off_type_normal = 1;//正常
    public static final int off_type_timeout = 2;//超时
    public static final int off_type_relogin = 3;//重新登录
    public static final int off_type_kill = 4;//强制离线
    
    public static final int equ_status_unauth = 0;//未授权
    public static final int equ_status_auth = 1;//已授权
    public static final int equ_status_back = 6;//回收
    
    public final static String session_account = "account";//登录账号
    public final static String session_member = "member";//会员账号
    public final static String session_openid = "openid";//会员openid
    
    public static final int merchant_club = 1;//俱乐部
    public static final int merchant_agent = 2;//代理商
    
    
    public static final int gorder_type_single = 1;//单次游戏
    public static final int gorder_type_booked = 2;//包机游戏
    
    public static final int gorder_status_create = 1;//订单创建
    public static final int gorder_status_waitbegin = 2;//登陆完等待开始游戏
    public static final int gorder_status_ingame = 3;//游戏中
    public static final int gorder_status_overgame = 4;//游戏结束
    public static final int gorder_status_loginfail = 5;// 没有登录成功
    public static final int gorder_status_loginout = 6;//登陆完退出游戏
    public static final int gorder_status_logout = 6;//登出
    
    
    public static final int gorder_paystatus_nopay = 0;//未支付
    public static final int gorder_paystatus_haspay = 1;//已支付
    public static final int gorder_paystatus_payfail = 2;//支付失败
    public static final int gorder_paystatus_refund = 3;//退款中
    public static final int gorder_paystatus_hasrefund = 3;//已退款
    public static final int gorder_paystatus_refundfail = 5;//退款失败
    
    public static final int gorder_payway_weixin = 1;//微信支付
    public static final int gorder_payway_alipay = 2;//支付宝支付
    public static final int gorder_payway_lft = 3;//其他支付
    
    public static final String consume_type_booked = "booked";//包机
    public static final String consume_type_single = "single";//余额支付
    
    
    public static final int amount_type_recharge = 1;//充值
    public static final int amount_type_consume = 2;//消费
    
    //约战
    public static final int receive_status_wait = 1;//未设置
    public static final int receive_status_refuse = 2;//拒绝
    public static final int receive_status_agree = 3;//应战
    
    public static final int challenge_status_def = 1;//未处理
    public static final int challenge_status_refuse = 2;//拒绝
    public static final int challenge_status_agree = 3;//应战
    public static final int challenge_status_over = 4;//结束
    public static final int challenge_status_overdue = 5;//过期
    
    public static final int wd_status_fail = 0;//支付失败
    public static final int wd_status_success = 1;//支付成功
    public static final int wd_status_wait = 2;//待审核
    public static final int wd_status_unpass = 3;//审核不通过
    
}
