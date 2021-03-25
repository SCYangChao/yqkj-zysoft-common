package com.yqkj.zysoft.common.core.log.core.dto;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName WriteLogDto
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/3/19 10:26
 * @Version 1.0
 **/
public class WriteLogDto implements Serializable {

    private Long id;
    //系统编码
    private String sys;
    //模块
    private String model;
    //输入
    private String input;
    //00 成功 500 失败
    private String logStatus;
    //用户
    private String userInfo;

    private String ip;
    //内容
    private String content;
    //耗时
    private Long takeTime;

    private Date createTime;
    //归档时间
    private Date expirTime;

    private HttpServletRequest request;

    private String modelInfo;

    public String getModelInfo() {
        return modelInfo;
    }

    public void setModelInfo(String modelInfo) {
        this.modelInfo = modelInfo;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSys() {
        return sys;
    }

    public WriteLogDto setSys(String sys) {
        this.sys = sys;
        return  this;
    }

    public String getModel() {
        return model;
    }

    public WriteLogDto setModel(String model) {
        this.model = model;
        return this;
    }

    public String getInput() {
        return input;
    }

    public WriteLogDto setInput(String input) {
        this.input = input;
        return this;
    }

    public String getLogStatus() {
        return logStatus;
    }

    public WriteLogDto setLogStatus(String logStatus) {
        this.logStatus = logStatus;
        return  this;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public WriteLogDto setUserInfo(String userInfo) {
        this.userInfo = userInfo;
        return  this;
    }

    public String getIp() {
        return ip;
    }

    public WriteLogDto setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getContent() {
        return content;
    }

    public WriteLogDto setContent(String content) {
        this.content = content;
        return this;
    }

    public Long getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(Long takeTime) {
        this.takeTime = takeTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExpirTime() {
        return expirTime;
    }

    public void setExpirTime(Date expirTime) {
        this.expirTime = expirTime;
    }
}
