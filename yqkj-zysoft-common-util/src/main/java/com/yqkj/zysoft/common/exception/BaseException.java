package com.yqkj.zysoft.common.exception;

/**
 * @ClassName BaseException
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2020/2/19 14:01
 * @Version 1.0
 **/
public abstract class BaseException extends  RuntimeException{
    /**
     * 系统编码
     */
    protected String sysCode;
    /**
     * 错误编码
     */
    protected String errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;
}
