package com.wangtiansoft.KingDarts.core.extensions.token.service;

import com.wangtiansoft.KingDarts.common.auth.ApiTokenResult;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public interface TokenService {

    /**
     * 创建一个token关联上指定用户
     *
     * @param userId 指定用户的id
     * @return 生成的token
     */
    ApiTokenResult createToken(String userId, String userUuid);


    /**
     * 检查token是否有效
     * @param token
     * @return
     */
    ApiTokenResult checkToken(String token);

    /**
     * 从字符串中解析token
     *
     * @return
     */
    ApiTokenResult getToken(String token);

    /**
     * 清除token
     *
     * @param userUuid 登录用户的id
     */
    void deleteToken(String userUuid);
}
