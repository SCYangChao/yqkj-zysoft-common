package com.yqkj.zysoft.common.core.log.core.dto;
/**
 * @ClassName FactoryWriteLog
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/3/19 15:11
 * @Version 1.0
 **/
public class FactoryWriteLog {
    /**
     * 实例
     * @return
     */
    public  static  WriteLogDto instance() {
        return  new WriteLogDto();
    }
    /**
     * 实例
     * @return
     */
    public  static  WriteLogDto instance(String model , String fun) {
        WriteLogDto writeLogDto = new WriteLogDto();
        writeLogDto.setModel(model);
        writeLogDto.setModelInfo(String.format("%s->%s" , writeLogDto.getModel() , fun));
        return  writeLogDto;
    }
}
