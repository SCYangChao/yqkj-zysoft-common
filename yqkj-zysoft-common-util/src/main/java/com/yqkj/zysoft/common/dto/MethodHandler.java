package com.yqkj.zysoft.common.dto;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * @ClassName MethodHandler
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2020/2/19 11:05
 * @Version 1.0
 **/
public class MethodHandler implements Serializable {
    /**
     * 要执行的方法
     */
    private  Method method;
    /**
     * 参数
     */
    private Object[] object;
    /**
     * 代理对象的引用
     */
    private  Object proxy;

    public MethodHandler(Method method, Object[] object, Object proxy) {
        this.method = method;
        this.object = object;
        this.proxy = proxy;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object[] getObject() {
        return object;
    }

    public void setObject(Object[] object) {
        this.object = object;
    }

    public Object getProxy() {
        return proxy;
    }

    public void setProxy(Object proxy) {
        this.proxy = proxy;
    }
}
