package com.wangtiansoft.KingDarts.core.support.bean;

import java.io.Serializable;

/**
 * Created by weitong on 17/3/21.
 */
public class UserSearchBean implements Serializable {

    private String number;
    private String nickname;
    private String isPublish;
    private String createTime_TimeBegin;
    private String createTime_TimeEnd;

    public String getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(String isPublish) {
        this.isPublish = isPublish;
    }

    public UserSearchBean() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCreateTime_TimeBegin() {
        return createTime_TimeBegin;
    }

    public void setCreateTime_TimeBegin(String createTime_TimeBegin) {
        this.createTime_TimeBegin = createTime_TimeBegin;
    }

    public String getCreateTime_TimeEnd() {
        return createTime_TimeEnd;
    }

    public void setCreateTime_TimeEnd(String createTime_TimeEnd) {
        this.createTime_TimeEnd = createTime_TimeEnd;
    }
}
