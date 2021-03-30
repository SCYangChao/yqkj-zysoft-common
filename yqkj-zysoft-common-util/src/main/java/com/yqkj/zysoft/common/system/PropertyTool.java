package com.yqkj.zysoft.common.system;

import com.yqkj.zysoft.common.constants.CommonConstant;
import com.yqkj.zysoft.common.string.StringUtil;

/**
 * @ClassName PropertyTool
 * @Description 根据sun.java.command Basepageck
 * @Author yangchao.cool@gmail.com
 * @Date 2020/2/11 14:09
 * @Version 1.0
 **/
public final class PropertyTool {
    /**
     * 私有构造函数
     */
    private  PropertyTool() {

    }
    /**
     * 获取当前运行运行的类路径
     * @return 目录数据组
     */
    public  static  String[] getBasePackage() {
        String property = System.getProperty(CommonConstant.CURRENT_CLASS);
        if (StringUtil.isNotBlank(property)) {
            String[]  basePackage = new String[]{property.substring(0, property.lastIndexOf("."))};
            return basePackage;
        }
        return  null;
    }

}
