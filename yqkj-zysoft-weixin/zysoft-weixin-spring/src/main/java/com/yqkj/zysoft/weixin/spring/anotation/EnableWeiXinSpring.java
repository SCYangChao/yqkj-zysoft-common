package com.yqkj.zysoft.weixin.spring.anotation;

import com.yqkj.zysoft.weixin.common.annotation.EnableWeixin;
import com.yqkj.zysoft.weixin.common.enums.ProxyEnum;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;
/**
 * @ClassName EnableWeiXinSpring
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/2/18 10:42
 * @Version 1.0
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableWeixin
@Import(com.yqkj.zysoft.weixin.spring.core.WeiXinBeanDefinitionRegistrar.class )
public @interface EnableWeiXinSpring {
    /**
     * 扫描的基础路径
     * @return
     */
    @AliasFor(annotation = EnableWeixin.class , attribute = "basePackage")
    String[] basePackage() default {};
    /**
     * 代理类型
     * @return
     */
    @AliasFor(annotation = EnableWeixin.class , attribute = "proxyType")
    ProxyEnum proxyType() default ProxyEnum.CGLIB;
    /**
     * 微信基础URL
     * @return
     */
    @AliasFor(annotation = EnableWeixin.class , attribute = "baseUrl")
    String baseUrl() default  "https://api.weixin.qq.com";
}
