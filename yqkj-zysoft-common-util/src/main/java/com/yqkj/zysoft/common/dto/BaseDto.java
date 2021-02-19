package com.yqkj.zysoft.common.dto;

import java.io.Serializable;
/**
 * @ClassName BaseDto
 * @Description 基础类
 * @Author yangchao.cool@gmail.com
 * @Date 2020/2/10 16:00
 * @Version 1.0
 **/
public abstract class BaseDto implements Serializable {
    /**
     * 用户信息
     */
    private UserInfo memoryUserInfo;
    /**
     * 商户信息
     */
    private  MerchantInfo memoryMerchantInfo;

    public UserInfo getMemoryUserInfo() {
        return memoryUserInfo;
    }

    public void setMemoryUserInfo(UserInfo memoryUserInfo) {
        this.memoryUserInfo = memoryUserInfo;
    }

    public MerchantInfo getMemoryMerchantInfo() {
        return memoryMerchantInfo;
    }

    public void setMemoryMerchantInfo(MerchantInfo memoryMerchantInfo) {
        this.memoryMerchantInfo = memoryMerchantInfo;
    }
}
