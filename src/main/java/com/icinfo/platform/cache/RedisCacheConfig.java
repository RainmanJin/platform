//package com.icinfo.platform.cache;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//
///**
// * MyBatis 二级缓存配置.
// *
// * Created by wangxiao on 2017/4/13.
// */
//@Component
//@Lazy(false)
//public class RedisCacheConfig {
//
//    /**
//     * 注入 RedisTemplate.
//     *
//     * @param redisTemplate
//     */
//    @Autowired
//    public void setRedisTemplate(RedisTemplate redisTemplate) {
//        MybatisRedisCache.setStringRedisTemplate(redisTemplate);
//    }
//}
