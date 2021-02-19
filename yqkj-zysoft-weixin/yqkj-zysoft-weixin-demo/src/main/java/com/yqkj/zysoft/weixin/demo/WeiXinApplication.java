package com.yqkj.zysoft.weixin.demo;

import com.yqkj.zysoft.weixin.demo.wein.WeinXinAuth;
import com.yqkj.zysoft.weixin.spring.anotation.EnableWeiXinSpring;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * @ClassName WeiXinApplication
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/2/18 11:33
 * @Version 1.0
 **/
@SpringBootApplication(scanBasePackages = "com")
@EnableWeiXinSpring
public class WeiXinApplication {

    @Resource
    private WeinXinAuth weinXinAuth;

    public static void main(String[] args) {
        SpringApplication.run(WeiXinApplication.class);
    }
}
