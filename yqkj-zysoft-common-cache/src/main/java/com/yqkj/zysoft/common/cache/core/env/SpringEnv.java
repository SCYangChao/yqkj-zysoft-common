package com.yqkj.zysoft.common.cache.core.env;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @ClassName SpringEnv
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/1/25 15:38
 * @Version 1.0
 **/
@Component
public class SpringEnv implements EnvironmentAware, InitializingBean {

    private Environment environment;

    private String applicationName;

    @Override
    public void afterPropertiesSet() throws Exception {
        /**
         * 项目名称 如果获取不到 就直接抛异常
         */
        this.applicationName = this.environment.getProperty("spring.application.name");
    }

    public String getApplicationName() {
        return applicationName;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
