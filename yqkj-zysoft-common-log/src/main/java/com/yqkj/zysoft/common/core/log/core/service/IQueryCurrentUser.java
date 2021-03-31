package com.yqkj.zysoft.common.core.log.core.service;

import com.yqkj.zysoft.common.core.log.core.dto.WriteLogDto;

/**
 * @ClassName IQueryCurrentUser
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/3/19 11:10
 * @Version 1.0
 **/
public interface IQueryCurrentUser {
    /**
     * 获取当前用户名称
     * @param writeLogDto 日志对象
     * @return true 处理成功 false 处理失败
     */
    public  Boolean  getCurrentUser(WriteLogDto writeLogDto);

}
