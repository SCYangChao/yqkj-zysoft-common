package com.yqkj.zysoft.common.core.log.annotation;

import java.lang.annotation.*;

/**
 * @ClassName Logger
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/3/19 10:16
 * @Version 1.0
 **/
@Target(value={ElementType.FIELD,ElementType.PARAMETER,ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Logger {
    /**
     *
     * @return
     */
    String value();
    /**
     * 是否入库
     * @return
     */
    boolean hasDb() default  false;
    /**
     * 系统
     * @return
     */
    String sys() default "station";
    /**
     * 模块
     * @return
     */
    String model() default "station";
}
