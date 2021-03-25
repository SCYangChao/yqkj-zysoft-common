package com.yqkj.zysoft.common.cache.memory;

import com.yqkj.zysoft.common.cache.ICacheProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName RedisCacheProcessor
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/1/25 15:34
 * @Version 1.0
 **/
@Component
public class MemoryCacheProcessor implements ICacheProcessor {

    @Autowired
    public  static Map<String , Object> MEMORYMAP = new ConcurrentHashMap<>();

    @Override
    public void cache(String key, Object value, int time) {
        MEMORYMAP.put(key , value);
    }
    @Override
    public void evict(String key) {
        MEMORYMAP.remove(key);
    }

    @Override
    public Object get(String key) {
        return  MEMORYMAP.get(key);
    }
}
