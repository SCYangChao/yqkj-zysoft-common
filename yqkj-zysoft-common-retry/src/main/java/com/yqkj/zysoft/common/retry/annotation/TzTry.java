package com.yqkj.zysoft.common.retry.annotation;

import com.yqkj.zysoft.common.retry.core.TryModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName TzTry
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/3/10 11:33
 * @Version 1.0
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface TzTry {
    /**
     * 是否打开重试
     * @return
     */
    boolean hasOpen() default  true;
    /**
     *重试次数 默认2次
     * @return
     */
    int   tryCount() default  4;
    /**
     * 尝试策略
     * @return
     */
    TryModel model() default TryModel.RANDOM;
}
