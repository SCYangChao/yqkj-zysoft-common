package com.yqkj.zysoft.common.cache.core.annotation;

import com.yqkj.zysoft.common.cache.ICacheProcessor;
import com.yqkj.zysoft.common.cache.memory.MemoryCacheProcessor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName HCache
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/1/25 15:03
 * @Version 1.0
 **/
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface HCache {
    /**
     * 缓存路径 ， 如果没有就直接用 项目名称加方法名称
     * @return
     */
    String path() default "";
    /**
     * 如果没有设置KEY 通过入参缓存
     * @return
     */
    String key() default "";
    /**
     * 默认没有时间限制
     * @return
     */
    int time() default -1;
    /**
     * 策略
     * @return
     */
    Class<? extends ICacheProcessor> strategy() default MemoryCacheProcessor.class;
}
