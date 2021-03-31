package com.yqkj.zysoft.common.core.log.core.dto;
/**
 * @ClassName FactoryWriteLog
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/3/19 15:11
 * @Version 1.0
 **/
public final class FactoryWriteLog {
    /**
     * 私有构造方法
     */
    private  FactoryWriteLog() {

    }
    /**
     * 实例
     * @return 实例
     */
    public  static  WriteLogDto instance() {
        return  new WriteLogDto();
    }

    /**
     * @param model 模块名称
     * @param fun 功能点
     * @return 返回 WriteLogDto 实例
     */
    public  static  WriteLogDto instance(String model, String fun) {
        WriteLogDto writeLogDto = new WriteLogDto();
        writeLogDto.setModel(model);
        writeLogDto.setModelInfo(String.format("%s->%s", writeLogDto.getModel(), fun));
        return  writeLogDto;
    }
}
