package com.yqkj.zysoft.common.core.log.core;

import com.yqkj.zysoft.common.date.DateTool;
import com.yqkj.zysoft.common.core.log.core.dto.WriteLogDto;
import com.yqkj.zysoft.common.core.log.core.service.IPersistentLog;
import com.yqkj.zysoft.common.core.log.core.service.IQueryCurrentUser;
import com.yqkj.zysoft.common.string.StringUtil;
import com.yqkj.zysoft.common.util.IPTool;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName LogProcessor
 * @Description 日志处理器
 * @Author yangchao.cool@gmail.com
 * @Date 2021/3/19 10:21
 * @Version 1.0
 **/
public class LogProcessor  {
    /**
     * 是否初始化成功
     */
    private   static   Boolean hasInit = Boolean.FALSE;
    /**
     *日志线程池
     */
    public  static  final  ExecutorService WRITELOG_EXECUTOR = new ThreadPoolExecutor(1, 1,0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    /**
     * 日志持久化
     */
    private static IPersistentLog iPersistentLog;
    /**
     * 获取当前用户信息
     */
    private  static IQueryCurrentUser iQueryCurrentUser;

    private  static Integer  writeLog = 2000;
    /**
     *  日志工作队列
     */
    private  static LinkedBlockingQueue<WriteLogDto>   WRITELOGQUEUE;

    /**
     * 外界在初始之前可以初始化这个数据
     * @param writeLog
     */
    public static void setWriteLog(Integer writeLog) {
        LogProcessor.writeLog = writeLog;
    }
    /**
     * 初始化
     */
    public  static void  init(IPersistentLog persistentLog , IQueryCurrentUser queryCurrentUser) {
        WRITELOGQUEUE = new LinkedBlockingQueue<>();
        iPersistentLog = persistentLog;
        iQueryCurrentUser = queryCurrentUser;
        if (Objects.isNull(iPersistentLog) || Objects.isNull(queryCurrentUser)) {
        }
    }
    /**
     * 日志写入
     */
    public  static  void run() {
        WRITELOG_EXECUTOR.submit((Runnable) () -> {
            while (true) {
                try {
                    WriteLogDto poll = WRITELOGQUEUE.poll(20, TimeUnit.SECONDS);
                    if (!Objects.isNull(poll)) {
                        iQueryCurrentUser.getCurrentUser(poll);
                        poll.setInput(StringUtil.cutLenStr(poll.getInput() , 512));
                        poll.setContent(StringUtil.cutLenStr(poll.getContent() , 2000));
                        poll.setIp(IPTool.getIpAddr(poll.getRequest()));
                        poll.setCreateTime(new Date());
                        poll.setExpirTime(DateTool.addMonths(poll.getCreateTime(), 12));
                        iPersistentLog.save(poll);
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        });
        hasInit = Boolean.TRUE;
    }
    /**
     * 日志写入到队列中
     * @param writeLogDto
     * @return
     */
    public  static  Boolean write(WriteLogDto writeLogDto) {
        if (hasInit && !Objects.isNull(writeLogDto)) {
            if (Objects.isNull(writeLogDto.getRequest())) {
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                writeLogDto.setRequest(request);
            }

            WRITELOGQUEUE.add(writeLogDto);
        }
        return Boolean.TRUE;
    }

}
