package com.yqkj.zysoft.common.core.log.config;


import com.yqkj.zysoft.common.core.log.core.LogProcessor;
import com.yqkj.zysoft.common.core.log.core.service.IPersistentLog;
import com.yqkj.zysoft.common.core.log.core.service.IQueryCurrentUser;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName InitLogConfig
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/3/19 14:29
 * @Version 1.0
 **/
@Component
public class InitLogConfig implements InitializingBean, ApplicationContextAware {
    /**
     * Spring 上下文
     */
    private ApplicationContext applicationContext;


    /**
     * Invoked by the containing {@code BeanFactory} after it has set all bean properties
     * and satisfied {@link }, {@code ApplicationContextAware} etc.
     * <p>This method allows the bean instance to perform validation of its overall
     * configuration and final initialization when all bean properties have been set.
     *
     * @throws Exception in the event of misconfiguration (such as failure to set an
     *                   essential property) or if initialization fails for any other reason
     */

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            IQueryCurrentUser queryCurrentUser = applicationContext.getBean(IQueryCurrentUser.class);
            IPersistentLog persistentLog = applicationContext.getBean(IPersistentLog.class);
            LogProcessor.init(persistentLog, queryCurrentUser);
            /**
             * 日志
             */
            LogProcessor.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Set the ApplicationContext that this object runs in.
     * Normally this call will be used to initialize the object.
     * <p>Invoked after population of normal bean properties but before an init callback such
     * as {@link InitializingBean#afterPropertiesSet()}
     * @throws BeansException              if thrown by application context methods
     * @see BeanInitializationException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
