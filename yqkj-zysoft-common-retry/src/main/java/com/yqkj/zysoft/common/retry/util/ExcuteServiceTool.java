package com.yqkj.zysoft.common.retry.util;

import com.yqkj.zysoft.common.exception.retry.ReTryException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import static com.yqkj.zysoft.common.constants.CommonIntConstant.FIVE_THOUSAND_AND_ONE;
import static com.yqkj.zysoft.common.constants.CommonIntConstant.FOUR;

/**
 * @ClassName ExcuteServiceTool
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/3/4 11:05
 * @Version 1.0
 **/
public final class ExcuteServiceTool {
    /**
     * 私有构造方法
     */
    private  ExcuteServiceTool() {

    }

    /**
     *
     * @param joinPoint 切面
     * @param count 次数
     * @return 返回执行数据
     * @throws Throwable 异常
     */
    public  static  Object  excute(JoinPoint joinPoint, Integer count) throws Throwable {
        for (int index = 0; index < count; index++) {
            try {
                Object obj = ((ProceedingJoinPoint) joinPoint).proceed();
                return obj;
            } catch (Throwable e) {
                e.printStackTrace();
                if (e instanceof ReTryException) {
                    ReTryException appException = (ReTryException) e;
                    try {
                        Thread.sleep((int) (Math.random() * FIVE_THOUSAND_AND_ONE) + FOUR);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    throw  e;
                }
            }
        }
        return null;
    }
}
