package com.yqkj.zysoft.common.system;

import com.yqkj.zysoft.common.constants.CommonConstant;
import com.yqkj.zysoft.common.string.StringUtil;

/**
 * @ClassName PropertyTool
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2020/2/11 14:09
 * @Version 1.0
 **/
public class PropertyTool {
    /**
     * 获取当前运行运行的类路径
     * @return
     */
    public  static  String[] getBasePackage() {
        String property = System.getProperty(CommonConstant.CURRENT_CLASS);
        if (StringUtil.isNotBlank(property)) {
            String[]  basePackage = new String[]{property.substring(0,property.lastIndexOf("."))};
            return basePackage;
        }
        return  null;
    }

}
