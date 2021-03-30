package com.yqkj.zysoft.common.retry.core;

import com.yqkj.zysoft.common.retry.annotation.TzTry;
import com.yqkj.zysoft.common.retry.util.ExcuteServiceTool;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;


/**
 * @ClassName TryModelAspect
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/3/10 11:41
 * @Version 1.0
 **/
@Aspect
@Component
public class TryModelAspect implements Ordered {

    public int getOrder() {
        return 2147483647;
    }

    @Around("@annotation(tzTry)")
    public Object around(JoinPoint joinPoint, TzTry tzTry) throws Throwable {

        boolean hasOpen = tzTry.hasOpen();

        if (hasOpen) {
            return  ExcuteServiceTool.excute(joinPoint,tzTry.tryCount());
        }else {
            return  ((ProceedingJoinPoint)joinPoint).proceed();
        }

    }

}
