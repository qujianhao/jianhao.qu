package com.wangtiansoft.KingDarts.core.extensions.token.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.wangtiansoft.KingDarts.common.auth.ApiTokenResult;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.extensions.token.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
@Service
public class TokenServiceImpl implements TokenService {

    private final Logger _logger = LoggerFactory.getLogger(TokenServiceImpl.class);

    private final static String secret = "waNgtIansOft";

    private JWTVerifier verifier;

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplete;

    @Override
    public ApiTokenResult createToken(String userId, String userUuid) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create().withClaim("uid", userUuid).sign(algorithm);
            ApiTokenResult result = new ApiTokenResult(userId, userUuid, token);
            redisTemplete.boundValueOps(userUuid).set(result, Constants.TOKEN_EXPIRES_DAY, TimeUnit.DAYS);
            return result;
        } catch (Exception exception) {
            throw new AppRuntimeException("token生成失败");
        }
    }

    @Override
    public ApiTokenResult getToken(String token) {
        return new ApiTokenResult("", token, token);
    }

    @Override
    public ApiTokenResult checkToken(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        DecodedJWT jwt = null;
        try {
            if (verifier == null) {
                Algorithm algorithm = Algorithm.HMAC256(secret);
                verifier = JWT.require(algorithm).build(); //Reusable verifier instance
            }
            jwt = verifier.verify(token);
            String uid = jwt.getClaims().get("uid").asString();
            ApiTokenResult result = (ApiTokenResult) redisTemplete.boundValueOps(uid).get();
            return result;
        } catch (Exception e) {
            _logger.error("checkToken error : {}", e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public void deleteToken(String userUuid) {
        redisTemplete.delete(userUuid);
    }


}
