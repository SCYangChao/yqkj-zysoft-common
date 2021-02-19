package com.yqkj.zysoft.common.exception;

import java.util.Properties;

/**
 * @ClassName BaseException
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2020/2/19 14:01
 * @Version 1.0
 **/
public class ExceptionTool {
    /**
     *系统编码
     */
    private static String sysCode;
    /**
     * 系统错误编码
     */
    private static  Properties errorProperties;

    public static String getSysCode() {
        return sysCode;
    }

    public static void setSysCode(String sysCode) {
        ExceptionTool.sysCode = sysCode;
    }

    public static Properties getErrorProperties() {
        return errorProperties;
    }

    public static void setErrorProperties(Properties errorProperties) {
        ExceptionTool.errorProperties = errorProperties;
    }
}
