package com.wangtiansoft.KingDarts.modules.api.token;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

@Component
public class RedisTokenManager implements TokenManager{

	/*@Autowired
	private RedisTemplate redis;*/
	@Autowired
    private RedisTemplate redisTemplate;

	private Long TOKEN_EXPIRES_SECONDS = new Long(2*60*60);
	
	/*@Autowired
	public void setRedis(RedisTemplate redis) {
		this.redis = redis;
		//泛型设置成Long后必须更改对应的序列化方案
//		redis.setKeySerializer(new JdkSerializationRedisSerializer());
	}*/

	public Token createToken(String userId) {
		//使用uuid作为源token
		String token = UUID.randomUUID().toString().replace("-", "");
		Token model = new Token(userId, token);
		//存储到redis并设置过期时间
		redisTemplate.boundValueOps(userId).set(token, TOKEN_EXPIRES_SECONDS, TimeUnit.SECONDS);
		return model;
	}

	public Token getToken(String authentication) {
		if (authentication == null || authentication.length() == 0) {
			return null;
		}
		String[] param = authentication.split("_");
		if (param.length != 2) {
			return null;
		}
		//使用userId和源token简单拼接成的token，可以增加加密措施
//		long userId = Long.parseLong(param[0]);
		String userId = param[0];
		String token = param[1];
		return new Token(userId, token);
	}

	public boolean checkToken(Token model) {
		if (model == null) {
			return false;
		}
//		String token = redis.boundValueOps(model.getUserId()).get();
		Object token = redisTemplate.boundValueOps(model.getUserId()).get();
		if (token == null || !token.toString().equals(model.getToken())) {
			return false;
		}
		//如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
		redisTemplate.boundValueOps(model.getUserId()).expire(TOKEN_EXPIRES_SECONDS, TimeUnit.SECONDS);
		return true;
	}

	public void deleteToken(String userId) {
		redisTemplate.delete(userId);
	}
}
