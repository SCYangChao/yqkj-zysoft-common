package com.yqkj.zysoft.weixin.spring.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

import java.lang.reflect.Method;

/**
 * @ClassName WeiXinFactoryBean
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/2/18 14:18
 * @Version 1.0
 **/
public class WeiXinFactoryBean<T> implements FactoryBean<T> {

    private Class<T> interfaceBean;

    public void setInterfaceBean(Class<T> interfaceBean) {
        this.interfaceBean = interfaceBean;
    }

    public WeiXinFactoryBean() {
    }

    @Override
    public T getObject() throws Exception {

        T proxyProducer = (T) Proxy.newProxyInstance(interfaceBean.getClassLoader(),
                new Class[]{interfaceBean},
                new InvocationHandler() {
                    /**
                     * 作用：执行被代理对象的任何接口方法都会经过该方法
                     * @param proxy  代理对象的引用
                     * @param method  当前执行的方法
                     * @param args    当前执行方法所需的参数
                     * @return    和被代理对象方法有相同的返回值
                     * @throws Throwable
                     */
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //提供增强代码
                        return   method.invoke(this,args);

//                        return  null;
                    }
                });
        return proxyProducer;
    }

    @Override
    public Class<?> getObjectType() {
        return interfaceBean;
    }

    @Override
    public String toString() {
        return "WeiXinFactoryBean{" +
                "interfaceBean=" + interfaceBean +
                '}';
    }
}
