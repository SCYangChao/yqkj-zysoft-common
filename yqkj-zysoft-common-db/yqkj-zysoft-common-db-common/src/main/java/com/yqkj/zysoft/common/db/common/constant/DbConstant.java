package com.yqkj.zysoft.common.db.common.constant;
/**
 * @author yangchao.cool@gmail.com
 * @copyright: Copyright (c) 2020
 * @company: 扬起科技有限公司
 * @date 2020/9/27 16:25
 * @description: 数据库常量
 */
public final class DbConstant {
    /**
     * 私有构造方法
     */
    private DbConstant() {

    }
    /**
     * 最大等待时间
     */
    public static final Integer MAXWAIT = 60000;
    /**
     * 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
     */
    public static final Integer TIMEBETWEENEVICTIONRUNSMILLIS = 3000;
    /**
     * 配置一个连接在池中最小生存的时间，单位是毫秒
     */
    public static  final Integer MINEVICTABLEIDLETIMEMILLIS = 300000;

}
