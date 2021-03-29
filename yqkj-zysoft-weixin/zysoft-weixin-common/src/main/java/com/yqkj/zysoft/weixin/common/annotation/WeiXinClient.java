package com.yqkj.zysoft.weixin.common.annotation;

import java.lang.annotation.*;

/**
 * @ClassName WeiXinClient
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2019/2/18 10:45
 * @Version 1.0
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WeiXinClient {
    /**
     * 用户得默认接口地址
     * @return
     */
    String apiUrl() default "https://api.weixin.qq.com/";

}
