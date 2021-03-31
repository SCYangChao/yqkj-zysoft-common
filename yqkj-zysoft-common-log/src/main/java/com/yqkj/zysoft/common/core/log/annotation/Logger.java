package com.yqkj.zysoft.common.core.log.annotation;

import java.lang.annotation.*;

/**
 * @ClassName Logger
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/3/19 10:16
 * @Version 1.0
 **/
@Target(value = {ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Logger {
    /**
     *
     * @return 值
     */
    String value();
    /**
     * @return 是否入数据库
     */
    boolean hasDb() default  false;
    /**
     * @return 系统
     */
    String sys() default "station";
    /**
     * @return 模块
     */
    String model() default "station";
}
