//package com.icinfo.platform.cache;
//
//import org.apache.commons.codec.digest.DigestUtils;
//import org.apache.ibatis.cache.Cache;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//
//import java.nio.charset.Charset;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.locks.ReadWriteLock;
//import java.util.concurrent.locks.ReentrantReadWriteLock;
//
///**
// * MyBatis 二级缓存整合 Redis.
// *
// * Created by wangxiao on 2017/4/13.
// */
//public class MybatisRedisCache implements Cache {
//
//    private static final Logger logger = LoggerFactory.getLogger(MybatisRedisCache.class);
//
//    /**
//     * 读写锁.
//     */
//    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
//
//    /**
//     * redis 模板.
//     */
//    private static RedisTemplate<String, String> stringRedisTemplate;
//
//    /**
//     * 实例 ID
//     */
//    private String id;
//
////    private JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer();
//
//    private GenericJackson2JsonRedisSerializer jsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
//
//    /**
//     * key 的前缀.
//     */
//    private final String CACHE_KEY_PREFIX = "platform:cache:";
//
//    /**
//     * 使用的字符集.
//     */
//    private static final String CHARSET_NAME = "UTF-8";
//
//    /**
//     * 初始化.
//     *
//     * @param id 实例 ID
//     */
//    public MybatisRedisCache(final String id) {
//        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>> 初始化缓存, id: " + id);
//        if (id == null) {
//            throw new IllegalArgumentException("缓存实例必须要有一个 ID");
//        }
//        this.id = id;
//    }
//
//    /**
//     * 所有 key
//     * @return 返回所有 key
//     */
//    private String getKeys() {
//        return CACHE_KEY_PREFIX + this.id + ":*";
//    }
//
//    /**
//     * key 进行统一处理.
//     *
//     * @param key 键
//     * @return 处理后的 key
//     */
//    private String getKey(Object key) {
////        logger.info("原始 key: " + key);
//        return CACHE_KEY_PREFIX + this.id + ":"+ DigestUtils.md5Hex(String.valueOf(key));
//    }
//
//    @Override
//    public String getId() {
//        return this.id;
//    }
//
//    /**
//     * 存入缓存.
//     *
//     * @param key 键
//     * @param value 值
//     */
//    @Override
//    public void putObject(Object key, Object value) {
//        logger.info(">>>>>>>>>>>>>>>>>>> 存入缓存.");
//        if(value != null) {
////            stringRedisTemplate.opsForValue().set(getKey(key),
////                    new String(jdkSerializer.serialize(value), Charset.forName(CHARSET_NAME)));
//            stringRedisTemplate.opsForValue().set(getKey(key),
//                    new String(jsonRedisSerializer.serialize(value), Charset.forName(CHARSET_NAME)),
//                    30, TimeUnit.MINUTES);
//        }
//    }
//
//    /**
//     * 根据 key 获取 value.
//     *
//     * @param key 键
//     * @return 值
//     */
//    @Override
//    public Object getObject(Object key) {
//        logger.info(">>>>>>>>>>>>>>>>>>> 从缓存获取.");
//        try {
//            if(key != null) {
//                String value = stringRedisTemplate.opsForValue().get(getKey(key));
////                return jdkSerializer.deserialize(value.getBytes(CHARSET_NAME));
//                return jsonRedisSerializer.deserialize(value.getBytes(Charset.forName(CHARSET_NAME)));
//            }
//        } catch (Exception e) {
//            logger.info("redis 取值异常: " + e.getMessage());
//        }
//        return null;
//    }
//
//    /**
//     * 删除 key.
//     *
//     * @param key 键
//     * @return
//     */
//    @Override
//    public Object removeObject(Object key) {
//        logger.info(">>>>>>>>>>>>>>>>>>> 从缓存移除.");
//        try {
//            if(key != null){
////                redisTemplate.expire(getKey(key), 1, TimeUnit.SECONDS);
//                stringRedisTemplate.delete(getKey(key));
//            }
//        } catch (Exception e) {
//            logger.info("redis 删除异常: " + e.getMessage());
//        }
//        return null;
//    }
//
//    /**
//     * 清空缓存
//     */
//    @Override
//    public void clear() {
//        logger.info(">>>>>>>>>>>>>>>>>>> 缓存清空.");
//        Set<String> keys = stringRedisTemplate.keys(getKeys());
//        stringRedisTemplate.delete(keys);
//    }
//
//    /**
//     * 获取缓存 key 总数.
//     *
//     * @return key 的总数.
//     */
//    @Override
//    public int getSize() {
//        logger.info(">>>>>>>>>>>>>>>>>>> 缓存key总数.");
//        Set<String> keys = stringRedisTemplate.keys(getKeys());
//        return keys.size();
//    }
//
//    /**
//     * 获取锁.
//     *
//     * @return 锁.
//     */
//    @Override
//    public ReadWriteLock getReadWriteLock() {
//        return this.readWriteLock;
//    }
//
//    /**
//     * 注入 RedisTemplate.
//     * 该方法权限为 package.
//     *
//     * @param stringRedisTemplate redis模板.
//     */
//    static void setStringRedisTemplate(RedisTemplate<String, String> stringRedisTemplate) {
//        MybatisRedisCache.stringRedisTemplate = stringRedisTemplate;
//    }
//}
