package com.wangtiansoft.KingDarts.config.netty.constants;

public class Constants {

    //  全局配置
    public final static int False = 0;
    public final static int True = 1;

    public final static String code_Fail           = "0";          //  失败
    public final static String code_Success        = "1";          //  成功
    
    public final static String code_SessionError   = "401";       //  登陆超时
    public final static String code_UnAuth         = "2000";       //  没有权限
    public final static String code_MessageError   = "101";       //  消息错误

    //  认证信息
    public static final String kAuth_tokenResult = "tokenResult";
    public static final String kAuth_xAccessToken = "x-access-token";
    
    //在线状态长连接，map集合缓存
    public static final String online_channel = "online_channel";
    
    //消息失败
    public static final int message_fail = -1;
    
    public static final String message_pong = "pong";	//心跳返回
    
    public static final String message_type_active = "active";	//心跳
    public static final String message_type_ping = "ping";	//心跳
    public static final String message_type_login = "login";	//登录
    public static final String message_type_logout = "logout";	//注销
    public static final String message_type_order = "order";	//创建订单
    public static final String message_type_orderpay = "orderpay";	//订单支付推送
    public static final String message_type_orderstart = "orderstart";	//游戏开始
    public static final String message_type_orderresults = "orderresults";	//游戏结束
    public static final String message_type_hit = "hit";	//网络游戏飞镖数据推送
    
    public static final String message_type_startpush = "startpush";	//游戏开始推送
    public static final String message_type_hitpush = "hitpush";	//网络游戏飞镖数据推送
    
    
}
