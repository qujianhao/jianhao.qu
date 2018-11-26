package com.wangtiansoft.KingDarts.core.support.bean;

import java.io.Serializable;

/**
 * Created by weitong on 17/3/21.
 */
public class ActivitySearchBean implements Serializable {

    private String activityName;
    private String activityStatus;

    public ActivitySearchBean() {
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
    }

}
