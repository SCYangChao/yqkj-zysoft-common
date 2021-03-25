package com.yqkj.zysoft.common.cache.core.annotation;

import com.yqkj.zysoft.common.cache.ICacheProcessor;
import com.yqkj.zysoft.common.cache.memory.MemoryCacheProcessor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName HCacheEvict
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/1/25 15:05
 * @Version 1.0
 **/
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface HCacheEvict {
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
     * 策略
     * @return
     */
    Class<? extends ICacheProcessor> strategy() default MemoryCacheProcessor.class;
    /**
     * 是否根据前缀删除 ，如果是 path不能为空
     * @return
     */
    boolean hasPre() default  true;
}
