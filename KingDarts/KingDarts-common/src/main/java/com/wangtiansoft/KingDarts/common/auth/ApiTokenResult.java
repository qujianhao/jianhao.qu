package com.wangtiansoft.KingDarts.common.auth;

import java.io.Serializable;

/**
 * Created by weitong on 18/1/3.
 */
public class ApiTokenResult implements Serializable {

    private String userId;
    private String userUuid;
    private String token;

    public ApiTokenResult() {
    }

    public ApiTokenResult(String userId, String userUuid) {
        this.userId = userId;
        this.userUuid = userUuid;
    }

    public ApiTokenResult(String userId, String userUuid, String token) {
        this.userId = userId;
        this.userUuid = userUuid;
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
