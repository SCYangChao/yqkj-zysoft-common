package com.yqkj.zysoft.common.constants;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CommonConstant
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2020/2/10 14:13
 * @Version 1.0
 **/
public final class CommonConstant {

    private CommonConstant() {
    }

    /**
     * 空集合
     */
    public static  final  List EMPTY_LIST = Collections.EMPTY_LIST;
    /**
     * 空MAp
     */
    public static  final   Map EMPTY_MAP = Collections.EMPTY_MAP;
    /**
     *正在执行的类
     */
    public static  final   String CURRENT_CLASS = "sun.java.command";
    /**
     * 空字符串
     */
    public static  final  String EMPTY_STRING = "";
    /**
     * -1
     */
    public static  final Long LOSS_ONE_LONG = -1L;
    /**
     * int -1
     */
    public static  final int LOSS_ONE_INT = -1;

    public  static  final  int FOUR_INT = 4;

}
