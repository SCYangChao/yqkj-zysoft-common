package com.yqkj.zysoft.weixin.demo.wein;

import com.yqkj.zysoft.weixin.common.annotation.WeiXinClient;
import com.yqkj.zysoft.weixin.common.annotation.http.WeinXinGet;

import java.util.Map;

@WeiXinClient
public interface WeinXinAuth {

    @WeinXinGet
    Map<String , Object> getAccessToken();

}
