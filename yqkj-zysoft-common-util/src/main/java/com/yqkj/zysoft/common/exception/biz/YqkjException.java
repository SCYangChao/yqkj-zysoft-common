package com.yqkj.zysoft.common.exception.biz;

/**
 *
  * class_name: YqkjException
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 下午11:10
  *
 **/
public class YqkjException extends RuntimeException {

    public static    String SYS_EXCEPTION_CODE = "500";

    public  static  String BIZ_EXCEPTION_CODE = "500";

    private String msg;

    private String code;


    public YqkjException(String code , String msg ) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public YqkjException(String code , String message, Throwable cause) {
        super(message, cause);
        this.msg = message;
        this.code = code;
    }

}
