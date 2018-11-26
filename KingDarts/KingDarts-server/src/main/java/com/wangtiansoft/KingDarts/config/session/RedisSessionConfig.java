package com.wangtiansoft.KingDarts.config.session;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by Administrator on 2017/9/9 0009.
 */
@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig {
}
