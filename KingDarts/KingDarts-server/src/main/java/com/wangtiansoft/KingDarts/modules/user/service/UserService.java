package com.wangtiansoft.KingDarts.modules.user.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.User;
import com.wangtiansoft.KingDarts.results.core.UserResult;

/**
* Created by wt-templete-helper.
 */
public interface UserService extends IBaseService<User, Integer> {

    // 分页查询User
    Page<Map> queryUserPageList(Map paramMap, PageBean pageBean);

	/**
	 * 通过uuid获取用户信息
	 * @param uuid
	 * @return
	 */
	UserResult getUserByUuid(String uuid);

	/**
	 * 通过微信联合主键 获取用户信息
	 * @param unionid
	 * @return
	 */
	UserResult getUserByUnionid(String unionid);

	/**
	 * 通过微信openid获取用户信息
	 * @param openid
	 * @return
	 */
	UserResult getUserByOpenid(String openid);

	/**
	 * 客户端用户新增，修改
	 * @param userResult
	 */
	void saveOrUpdate(UserResult userResult);

	/**
	 * 通过微信接口新增或更新用户信息
	 * @param openId	
	 * @param nickName	昵称
	 * @param unionid	
	 * @param headimgurl	头像
	 * @param country	国家
	 * @param city		市
	 * @param province	省
	 * @param gender	性别
	 * @param id
	 * @param hisUser	历史用户数据
	 */
	void addOrUpdateFromWeiXin(String appid,String openId, String nickName, String unionid, String headimgurl, String country,
			String city, String province, Integer gender, Integer id, Map hisUser);


	/**
	 * 游戏支付扣款
	 * @param userId  用户uuid
	 * @param orderId 订单id
	 * @param consumeNum 消费金额
	 * @param orderType 订单类型，1单次游戏，2包机游戏
	 * @param gameCode 游戏编码
	 * @param gameMode 游戏模式
	 * @param equno 设备编码
	 */
	void consume(String userId, Long orderId, BigDecimal consume, Integer orderType, String gameCode,
			Integer gameMode, String equno);

	
	/**
	 * 充值
	 * @param userId	用户id
	 * @param payId		支付订单id
	 * @param orderId	游戏订单id
	 * @param amount	实际支付金额
	 * @param payAmount 赠送游戏点
	 * @param equno		设备编号
	 */
	void recharge(String userId, Long payId, BigDecimal amount, BigDecimal give_balance,Long orderId, String equno,String remark);

	/**
	 * 游戏点变动
	 * @param userId 用户uuid
	 * @param amount 变动金额
	 * @param remark 变动说明
	 */
	void balanceChange(String userId, BigDecimal amount, String remark);

	/**
	 * 修改用户余额（游戏点）
	 * @param userId	用户uuid
	 * @param amount	变动金额（游戏点）
	 * @param remark	变动说明
	 * @param orderId	游戏订单id
	 */
	void balanceChange(String userId, BigDecimal amount, String remark, Long orderId);
	
	/**
	 * 查询用户游戏点数
	 * @param paramMap
	 * @param pageBean
	 * @return
	 */
	public Page<Map> queryUserBalancePageList(Map paramMap,PageBean pageBean);
	
	/**
	 * 查询用户积分
	 * @param paramMap
	 * @param pageBean
	 * @return
	 */
	public Page<Map> queryUserPointsPageList(Map paramMap,PageBean pageBean);
	
	/**
	 * 根据条件获取分页月度排名或者当前用户排名
	 */
	Page<Map> queryUserRankByPointsPageList(Map paramMap, PageBean pageBean);

	/**
	 * 根据条件获取分页历史月度排名或者历史月度用户排名
	 */
	Page<Map> queryUserRankByMonthPointsPageList(Map paramMap, PageBean pageBean);
	
	/**
	 * 插入月度历史用户积分记录 定时器调用
	 */
	void insertUserPointsMonth(Map paramMap);

	/**
	 * 查询历史用户
	 * @param unionid
	 * @return
	 */
	Map getHisUserByUuid(String unionid);

	/**
	 * 更新历史用户unionid
	 * @param openid
	 * @param unionid
	 */
	void updateHisUser(String openid, String unionid);

	/**
	 * 根据openid查询历史用户信息，用于历史用户数据迁移
	 * @param openid
	 * @return
	 */
	Map getHisUserByOpenid(String openid);

	/**
	 * 保存用户openid和appid关系
	 * @param user
	 * @param openId
	 * @param appId
	 */
	void addOrUpdateUserOpenId(UserResult user, String openId, String appId);

	String hasWxOpenid(UserResult user);



	void balanceChangedd(String userId);



	
}

