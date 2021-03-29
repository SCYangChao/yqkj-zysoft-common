package com.yqkj.zysoft.weixin.demo.wein;

import com.yqkj.zysoft.weixin.common.annotation.WeiXinClient;
import com.yqkj.zysoft.weixin.common.annotation.http.WeinXinGet;
import com.yqkj.zysoft.weixin.common.annotation.http.WeinXinPost;

import java.util.Map;

@WeiXinClient
public interface WeinXinAuth {

    @WeinXinGet
    @WeinXinPost
    Map<String , Object> getAccessToken();

}
