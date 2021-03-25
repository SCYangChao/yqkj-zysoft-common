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
public class DateTool {
    /**
     * 加月份
     * @param date
     * @param months
     * @return
     */
    public static Date addMonths(Date date, int months) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, months);
        return c.getTime();
    }
}
