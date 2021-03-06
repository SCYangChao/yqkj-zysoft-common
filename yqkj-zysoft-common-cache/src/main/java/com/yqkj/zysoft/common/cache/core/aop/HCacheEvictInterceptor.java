package com.yqkj.zysoft.common.cache.core.aop;

import com.yqkj.zysoft.common.cache.ICacheProcessor;
import com.yqkj.zysoft.common.cache.core.annotation.HCacheEvict;
import com.yqkj.zysoft.common.cache.core.env.SpringEnv;
import com.yqkj.zysoft.common.cache.core.util.HCacheTool;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.expression.EvaluationContext;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @ClassName HCacheInterceptor
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/1/25 15:14
 * @Version 1.0
 **/
@Aspect
@Component
public class HCacheEvictInterceptor implements Ordered, ApplicationContextAware {

    private ApplicationContext applicationContext;

    public static final Log LOG = LogFactory.getLog(HCacheInterceptor.class);

    @Autowired
    private SpringEnv springEnv;

    /**
     * 缓存注释
     * @param joinPoint 切面
     * @param tzCacheEvict 注解
     * @return 返回执行参数
     * @throws Throwable 异常
     */
    @Around("@annotation(tzCacheEvict)")
    public Object around(JoinPoint joinPoint, HCacheEvict tzCacheEvict) throws Throwable {
        if (!Objects.isNull(springEnv) && StringUtils.isNotBlank(springEnv.getApplicationName())) {

            String path = HCacheTool.path(springEnv, tzCacheEvict.path(), joinPoint.getSignature().getName());
            String key = tzCacheEvict.key();
            EvaluationContext context = HCacheTool.getEvaluationContext(((MethodSignature) joinPoint.getSignature()).getParameterNames(),
                                                    joinPoint.getArgs());
            String  keyValue = HCacheTool.getEvictKey(context, key, path);
            ICacheProcessor bean = this.applicationContext.getBean(tzCacheEvict.strategy());
            if (!Objects.isNull(bean)) {
                bean.evict(keyValue);
            }
            Object obj = ((ProceedingJoinPoint) joinPoint).proceed();
            return obj;
        } else {
            if (LOG.isInfoEnabled()) {
                LOG.info(String.format("缓存没有配置成功,%s", joinPoint));
            }
        }
        return ((ProceedingJoinPoint) joinPoint).proceed();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE - 1;
    }
}
