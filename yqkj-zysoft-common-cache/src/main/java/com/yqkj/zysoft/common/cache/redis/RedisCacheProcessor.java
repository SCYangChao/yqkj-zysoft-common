package com.yqkj.zysoft.common.cache.redis;

import com.yqkj.zysoft.common.cache.ICacheProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @ClassName RedisCacheProcessor
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/1/25 15:34
 * @Version 1.0
 **/
@Component
public class RedisCacheProcessor implements ICacheProcessor {

    @Autowired
    private RedisTemplate<String , Object> redisTemplate;

    @Override
    public void cache(String key, Object value, int time) {
        redisTemplate.opsForValue().set(key , value);
    }
    @Override
    public void evict(String key) {
        Set<String> keys = redisTemplate.keys(key);
        redisTemplate.delete(keys);
    }

    @Override
    public Object get(String key) {
        return  redisTemplate.opsForValue().get(key);
    }
}
