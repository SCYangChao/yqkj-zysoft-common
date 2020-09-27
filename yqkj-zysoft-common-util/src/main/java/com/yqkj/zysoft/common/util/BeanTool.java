package com.yqkj.zysoft.common.util;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class BeanTool {
    public static String SPIT_FLAG = "_";
    private static Logger log = Logger.getLogger(BeanTool.class);
    private static final Map<String, Object> EMPTY_MAP = new HashMap();
    private static Map<String, Map<String, String>> HEAD_CLASS = new ConcurrentHashMap();

    public BeanTool() {
    }

    public static Map<String, String> bulidTableHead(Class c) {
        if (!HEAD_CLASS.containsKey(c.getName())) {
            Field[] fields = c.getDeclaredFields();
            String value = "";
            Map<String, String> resultMap = new LinkedHashMap(16);
            Field[] var4 = fields;
            int var5 = fields.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                Field field = var4[var6];
                ApiModelProperty apiPro = (ApiModelProperty)field.getAnnotation(ApiModelProperty.class);
                if (!Objects.isNull(apiPro)) {
                    value = apiPro.value();
                    if (StringUtils.isNotBlank(value)) {
                        resultMap.put(field.getName(), value);
                    }
                }
            }

            HEAD_CLASS.put(c.getName(), resultMap);
            return resultMap;
        } else {
            return (Map)HEAD_CLASS.get(c.getName());
        }
    }


    public static <R, O> void copyObject(R source, O target) {
        if (!Objects.isNull(source)) {
            copyObject(source, target, (Class)null, (String[])null);
        }
    }

    public static <R, O> void copyObject(R source, O target, Class<?> editable, String... ignoreProperties) {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        Class<?> actualEditable = target.getClass();
        if (editable != null) {
            if (!editable.isInstance(target)) {
                throw new IllegalArgumentException("Target class [" + target.getClass().getName() + "] not assignable to Editable class [" + editable.getName() + "]");
            }

            actualEditable = editable;
        }

        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);
        List<String> ignoreList = ignoreProperties != null ? Arrays.asList(ignoreProperties) : null;
        PropertyDescriptor[] var7 = targetPds;
        int var8 = targetPds.length;

        for(int var9 = 0; var9 < var8; ++var9) {
            PropertyDescriptor targetPd = var7[var9];
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
                PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null && ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }

                            Object value = readMethod.invoke(source);
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }

                            writeMethod.invoke(target, value);
                        } catch (Throwable var15) {
                            log.info(String.format("copyObject error:%s", "Could not copy property '" + targetPd.getName() + "' from source to target"));
                        }
                    }
                }
            }
        }

    }
}
