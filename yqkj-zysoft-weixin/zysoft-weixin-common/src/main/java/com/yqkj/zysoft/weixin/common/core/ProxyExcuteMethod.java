package com.yqkj.zysoft.weixin.common.core;

import com.yqkj.zysoft.weixin.common.annotation.http.WeinXinGet;
import com.yqkj.zysoft.weixin.common.annotation.http.WeinXinPost;
import com.yqkj.zysoft.weixin.common.exception.UnKnowMatchRequestMethodException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Objects;

/**
 * @ClassName ProxyExcuteMethod
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/2/19 15:21
 * @Version 1.0
 **/
public class ProxyExcuteMethod {
    /**
     *
     * @param proxy  代理对象的引用
     * @param method  当前执行的方法
     * 当前执行方法所需的参数
     * 和被代理对象方法有相同的返回值
     */
    public  static  Object proxyInvoke(Method method, Object[] object, Object proxy , String baseUrl){
        Annotation[] annotations = method.getAnnotations();
        WeinXinGet annotationGet = method.getAnnotation(WeinXinGet.class);
        WeinXinPost annotationPost = method.getAnnotation(WeinXinPost.class);
        if(!Objects.isNull(annotationGet) && !Objects.isNull(annotationPost)){
            throw  new UnKnowMatchRequestMethodException();
        }

        return  new HashMap<>();
    }

}
