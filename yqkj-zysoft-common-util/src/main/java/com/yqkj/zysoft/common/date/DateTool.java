package com.yqkj.zysoft.common.date;

import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName DateTool
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/3/25 16:30
 * @Version 1.0
 **/
public final class DateTool {
    /**
     * 私有构成函数
     */
    private  DateTool() { }
    /**
     * 加月份
     * @param date 时间
     * @param months 加月
     * @return 返回计算好的时间
     */
    public static Date addMonths(Date date, int months) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, months);
        return c.getTime();
    }
}
