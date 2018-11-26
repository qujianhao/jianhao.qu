package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import java.util.Date;
import javax.persistence.*;

@Table(name = "darts_rob_floors")
public class RobFloors extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    private String user_id;

    /**
     * 用户账号
     */
    private String useraccount;

    /**
     * 用户昵称
     */
    private String user_nickname;

    /**
     * 用户已领取的点数信息id，与sys_dict表中的id对应
     */
    private String get_point_id;

    /**
     * 领取游戏点数所需消耗的点数
     */
    private Integer expense_point;

    /**
     * 得到的游戏点数
     */
    private Integer point;

    /**
     * 1：每日；2：每周；3：每月
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * 设置用户id
     *
     * @param user_id 用户id
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * 获取用户账号
     *
     * @return useraccount - 用户账号
     */
    public String getUseraccount() {
        return useraccount;
    }

    /**
     * 设置用户账号
     *
     * @param useraccount 用户账号
     */
    public void setUseraccount(String useraccount) {
        this.useraccount = useraccount;
    }

    /**
     * 获取用户昵称
     *
     * @return user_nickname - 用户昵称
     */
    public String getUser_nickname() {
        return user_nickname;
    }

    /**
     * 设置用户昵称
     *
     * @param user_nickname 用户昵称
     */
    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    /**
     * 获取用户已领取的点数信息id，与sys_dict表中的id对应
     *
     * @return get_point_id - 用户已领取的点数信息id，与sys_dict表中的id对应
     */
    public String getGet_point_id() {
        return get_point_id;
    }

    /**
     * 设置用户已领取的点数信息id，与sys_dict表中的id对应
     *
     * @param get_point_id 用户已领取的点数信息id，与sys_dict表中的id对应
     */
    public void setGet_point_id(String get_point_id) {
        this.get_point_id = get_point_id;
    }

    /**
     * 获取领取游戏点数所需消耗的点数
     *
     * @return expense_point - 领取游戏点数所需消耗的点数
     */
    public Integer getExpense_point() {
        return expense_point;
    }

    /**
     * 设置领取游戏点数所需消耗的点数
     *
     * @param expense_point 领取游戏点数所需消耗的点数
     */
    public void setExpense_point(Integer expense_point) {
        this.expense_point = expense_point;
    }

    /**
     * 获取得到的游戏点数
     *
     * @return point - 得到的游戏点数
     */
    public Integer getPoint() {
        return point;
    }

    /**
     * 设置得到的游戏点数
     *
     * @param point 得到的游戏点数
     */
    public void setPoint(Integer point) {
        this.point = point;
    }

    /**
     * 获取1：每日；2：每周；3：每月
     *
     * @return status - 1：每日；2：每周；3：每月
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置1：每日；2：每周；3：每月
     *
     * @param status 1：每日；2：每周；3：每月
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreate_time() {
        return create_time;
    }

    /**
     * 设置创建时间
     *
     * @param create_time 创建时间
     */
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}