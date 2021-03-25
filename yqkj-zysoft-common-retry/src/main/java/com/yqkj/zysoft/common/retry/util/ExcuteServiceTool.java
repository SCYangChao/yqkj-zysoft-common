package com.yqkj.zysoft.common.retry.util;

import com.yqkj.zysoft.common.exception.retry.ReTryException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @ClassName ExcuteServiceTool
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/3/4 11:05
 * @Version 1.0
 **/
public class ExcuteServiceTool {
    /**
     * 执行方法
     * @return
     */
    public  static  Object  excute(JoinPoint joinPoint , Integer count ) throws Throwable {
        for (int index = 0; index < count ; index++ ) {
            try {
                Object obj = ((ProceedingJoinPoint)joinPoint).proceed();
                return obj;
            }catch (Throwable e){
                e.printStackTrace();
                if(e instanceof ReTryException){
                    ReTryException appException = (ReTryException) e;
                    try {
                        Thread.sleep((int)(Math.random()*5001) +50);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }else {
                    throw  e;
                }
            }
        }
        return null;
    }
}
