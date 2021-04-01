package com.yqkj.zysoft.common.db.common.config;

import com.yqkj.zysoft.common.db.common.constant.DbConstant;

import java.io.Serializable;

import static com.yqkj.zysoft.common.db.common.constant.DbConstant.MAXWAIT;

/**
 * @author yangchao.cool@gmail.com
 * @copyright: Copyright (c) 2020
 * @company: 扬起科技有限公司
 * @date 2020/9/27 16:25
 * @description: 数据库属性配置
 */
public abstract class DbConfig implements Serializable {
    /**
     * 连接URL
     */
    private String url;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 驱动类
     */
    private String driverClass;
    /**
     * 最大连接数
     */
    private Integer maxActive;
    /**
     * 初始化连接个数
     */
    private Integer initialSize;
    /**
     * 最小空闲连接数
     */
    private Integer minIdle;
    /**
     * 最大等待时间
     */
    private Integer maxWait = MAXWAIT;
    /**
     *配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
     */
    private Integer timeBetweenEvictionRunsMillis = DbConstant.TIMEBETWEENEVICTIONRUNSMILLIS;
    /**
     *配置一个连接在池中最小生存的时间，单位是毫秒
     */
    private Integer minEvictableIdleTimeMillis = DbConstant.MINEVICTABLEIDLETIMEMILLIS;
    /**
     * 检测语句
     */
    private String validationQuery = "SELECT 'x'";
    /**
     * 如果为true(默认truse)，当应用向连接池申请连接，
     * 并且testOnBorrow为false时 连接池将会判断连接是否处于空闲状态，
     * 如果是，则验证这条连接是否可用
     */
    private Boolean testWhileIdle = true;
    /**
     * 检测池里连接的可用性
     */
    private Boolean testOnBorrow = false;
    /**
     *归还连接时执行validationQuery检测连接是否有效
     */
    private Boolean testOnReturn = false;
    /**
     * 过滤器
     */
    private String filters = "stat,log4j2";
    /**
     * 是否开启PSCache
     */
    private Boolean poolPreparedStatements = false;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public Integer getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }

    public Integer getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(Integer initialSize) {
        this.initialSize = initialSize;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    public Integer getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(Integer maxWait) {
        this.maxWait = maxWait;
    }

    public Integer getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(Integer timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public Integer getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(Integer minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public Boolean getTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(Boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public Boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public Boolean getTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(Boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public Boolean getPoolPreparedStatements() {
        return poolPreparedStatements;
    }

    public void setPoolPreparedStatements(Boolean poolPreparedStatements) {
        this.poolPreparedStatements = poolPreparedStatements;
    }
}
