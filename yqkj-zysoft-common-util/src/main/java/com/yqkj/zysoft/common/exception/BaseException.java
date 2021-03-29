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
    protected String sysCode = "base";
    /**
     * 错误编码
     */
    protected String errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
