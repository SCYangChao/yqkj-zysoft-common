package com.yqkj.zysoft.weixin.demo.wein;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName WeiXinService
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/2/18 16:53
 * @Version 1.0
 **/
@RestController
public class WeiXinService {
    @Resource
    private WeinXinAuth weinXinAuth;
    @GetMapping("wexin")
    public  Boolean auth(){
        weinXinAuth.getAccessToken();
        System.out.println(weinXinAuth);
        return  Boolean.TRUE;
    }

}
