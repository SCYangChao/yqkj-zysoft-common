package com.yqkj.zysoft.common.db.mybatisplus.core.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.yqkj.zysoft.common.db.common.config.ReadDbConfig;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author yangchao.cool@gmail.com
 * @copyright: Copyright (c) 2020
 * @company: 扬起科技有限公司
 * @date 2020/9/27 16:25
 * @description: 读数据库属性配置
 */
public class MybatisplusConfig {
    /**
     *
     * @return
     */
    public DataSource initMybatisplusConfig(ReadDbConfig dbConfig) throws Exception {
        Properties properties = new Properties();
        properties.put("name", dbConfig.hashCode());
        properties.put("username", dbConfig.getUsername());
        properties.put("password", dbConfig.getPassword());
        properties.put("url", dbConfig.getUrl());
        properties.put("filters", dbConfig.getFilters());
        properties.put("maxWait", dbConfig.getMaxWait());
        return  DruidDataSourceFactory.createDataSource(properties);
    }
}
