package com.yqkj.zysoft.weixin.common.enums;
/**
 * @ClassName ProxyEnum
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2020/2/17 14:49
 * @Version 1.0
 **/
public enum ProxyEnum implements BaseEnum {

    CGLIB("cglib","cglib","cglib");
    String code;
    String name;
    String enName;

    ProxyEnum(String code , String name , String enName){
        this.code=code;
        this.name=name;
        this.enName=enName;
    }
    /**
     * 编码
     *
     * @return
     */
    @Override
    public String getCode() {
        return code;
    }
    /**
     * 名称
     *
     * @return
     */
    @Override
    public String getName() {
        return name;
    }
    /**
     * 英文编码
     *
     * @return
     */
    @Override
    public String getEnName() {
        return enName;
    }
}
