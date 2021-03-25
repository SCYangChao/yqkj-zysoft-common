package com.yqkj.zysoft.common.core.log.core.service;

import com.yqkj.zysoft.common.core.log.core.dto.WriteLogDto;

/**
 * @ClassName IQueryCurrentUser
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/3/19 11:10
 * @Version 1.0
 **/
public interface IPersistentLog {
    /**
     * 获取当前用户名称
     * @return
     */
    public  Boolean  save(WriteLogDto writeLogDto);
}
