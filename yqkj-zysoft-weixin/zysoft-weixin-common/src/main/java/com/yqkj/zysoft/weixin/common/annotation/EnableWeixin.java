package com.yqkj.zysoft.weixin.common.annotation;

import com.yqkj.zysoft.weixin.common.enums.ProxyEnum;

import java.lang.annotation.*;

/**
 * @ClassName EnableWeixin
 * @Description  加上这个注解 能开启微信功能
 * @Author yangchao.cool@gmail.com
 * @Date 2021/2/10 14:53
 * @Version 1.0
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableWeixin {
    /**
     * 扫描的包路径
     * @return
     */
    String[] basePackage() default {};
    /**
     * 代理类型
     * @return
     */
    ProxyEnum proxyType() default ProxyEnum.CGLIB;
    /**
     * 微信基础URL
     * @return
     */
    String baseUrl() default  "https://api.weixin.qq.com";

}
