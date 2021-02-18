package com.yqkj.zysoft.weixin.spring.anotation;

import com.yqkj.zysoft.weixin.common.annotation.EnableWeixin;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;
/**
 * @ClassName EnableWeixinSpring
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
public @interface EnableWeixinSpring  {
}
