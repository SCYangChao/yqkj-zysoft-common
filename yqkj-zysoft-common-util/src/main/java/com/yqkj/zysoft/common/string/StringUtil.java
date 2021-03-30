package com.yqkj.zysoft.common.string;


import java.util.Objects;

/**
 * @author yangchao.cool@gmail.com
 * @copyright: Copyright (c) 2020
 * @company: 扬起科技有限公司
 * @date 2020/9/27 16:25
 * @description: 字符串处理子， 对字符串处理包装 后期字符串处理切换处理包，与出现性能问题方便进行优化
 */
public final class StringUtil {

    private StringUtil() {
    }

    /**
     * 根据字符串长度截取字符串长度
     * @param str
     * @param len
     * @return
     */
    public  static  String cutLenStr(String str , Integer len) {
        if (Objects.isNull(len)) {
            return str;
        }
        if (len(str) > len) {
            return  str.substring(0 , len);
        }
        return  str;
    }
    /**
     * 是否有字符长度 如果有则返回True 没有测试返回Fals
     * @param str
     * @return
     */
    public  static  Boolean hasLength(String str) {
        return  len(str) > 0;
    }
    /**
     * 字符串长度
     * @param str
     * @return
     */
    public  static  Integer len(String str) {
        if (isEmpty(str)) {
            return  Integer.valueOf(0);
        }
        return  str.length();
    }
    /**
     * 字符串为空判断 ， 空指针、长度(去掉两边的空格)为0 返回FALSE ， 其他返回 TRUE
     * @param str
     * @return
     */
    public static  Boolean isNotBlank(String str) {
        return  !isBlank(str);
    }
    /**
     * 字符串为空判断 ， 空指针、长度(去掉两边的空格)为0 返回TRUE ， 其他返回 FALSE
     * @param str
     * @return
     */
    public  static  Boolean isBlank(String str) {
        if (Objects.isNull(str)) {
            return  Boolean.TRUE;
        }
        return  isEmpty(str.trim());
    }
    /**
     * 字符串为非空判断 ， 空指针或长度为0 返回FALSE ， 其他返回 TRUE
     * @param str
     * @return
     */
    public  static  Boolean isNotEmpty(String str) {
        return  !isEmpty(str);
    }
    /**
     * 字符串为空判断 ， 空指针或长度为0 返回TRUE ， 其他返回 FALSE
     * @param str
     * @return
     */
    public static   Boolean  isEmpty(String str) {
        if (Objects.isNull(str)) {
            return Boolean.TRUE;
        }
        if (str.length()==0) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
