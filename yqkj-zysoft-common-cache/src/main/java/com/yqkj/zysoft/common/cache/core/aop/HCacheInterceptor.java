package com.yqkj.zysoft.common.cache.core.aop;

import com.yqkj.zysoft.common.cache.ICacheProcessor;
import com.yqkj.zysoft.common.cache.core.annotation.HCache;
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
public class HCacheInterceptor implements Ordered , ApplicationContextAware {

    public static final Log log = LogFactory.getLog(HCacheInterceptor.class);

    @Autowired
    private SpringEnv springEnv;

    private ApplicationContext applicationContext;

    @Around("@annotation(tzCache)")
    public Object around(JoinPoint joinPoint, HCache tzCache) throws Throwable{
        if (!Objects.isNull(springEnv) && StringUtils.isNotBlank(springEnv.getApplicationName())) {
            String path = HCacheTool.path(springEnv, tzCache.path(), joinPoint.getSignature().getName());
            String key = tzCache.key();
            /**
             * 如果配置了Key 那么这个值就可能是一个PEL
             */
            String keyValue="";
            if (StringUtils.isNotBlank(key)) {
                EvaluationContext context = HCacheTool.getEvaluationContext(((MethodSignature)joinPoint.getSignature()).getParameterNames(), joinPoint.getArgs());
                keyValue = HCacheTool.getKey(context, key , path);
                ICacheProcessor bean = this.applicationContext.getBean(tzCache.strategy());
                if (!Objects.isNull(bean)) {
                    Object cacheValue = bean.get(keyValue);
                    if (!Objects.isNull(cacheValue)) {
                        return cacheValue;
                    }
                    Object obj = ((ProceedingJoinPoint) joinPoint).proceed();
                    if (!Objects.isNull(obj)) {
                        bean.cache(keyValue, obj, tzCache.time());
                    }
                    return obj;
                }

            }else {
                if (log.isInfoEnabled()) {
                    log.info(String.format("缓存没有配置成功 ,缺失KEY,%s" ,joinPoint));
                }
            }
        }else {
            if (log.isInfoEnabled()) {
                log.info(String.format("缓存没有配置成功,%s" ,joinPoint));
            }
        }
        return ((ProceedingJoinPoint) joinPoint).proceed();
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE+1;
    }
}
