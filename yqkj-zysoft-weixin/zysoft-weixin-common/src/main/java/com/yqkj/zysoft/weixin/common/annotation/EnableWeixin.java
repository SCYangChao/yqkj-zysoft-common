package com.yqkj.zysoft.weixin.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName EnableWeixin
 * @Description  加上这个注解 能开启微信功能
 * @Author yangchao.cool@gmail.com
 * @Date 2021/2/10 14:53
 * @Version 1.0
 **/
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface EnableWeixin {
}
