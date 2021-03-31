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

import static com.yqkj.zysoft.common.constants.CommonIntConstant.*;

/**
 * @ClassName LogProcessor
 * @Description 日志处理器
 * @Author yangchao.cool@gmail.com
 * @Date 2021/3/19 10:21
 * @Version 1.0
 **/
public final class  LogProcessor  {
    /**
     * 私有构造方法
     */
    private LogProcessor() {

    }
    /**
     * 是否初始化成功
     */
    private   static   Boolean hasInit = Boolean.FALSE;
    /**
     *日志线程池
     */
    public  static  final  ExecutorService WRITELOG_EXECUTOR = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    /**
     * 日志持久化
     */
    private static IPersistentLog iPersistentLog;
    /**
     * 获取当前用户信息
     */
    private  static IQueryCurrentUser iQueryCurrentUser;

    private  static Integer  writeLog = TWO_THOUSAND;
    /**
     *  日志工作队列
     */
    private  static LinkedBlockingQueue<WriteLogDto> writeLogQueue;

    /**
     * 外界在初始之前可以初始化这个数据
     * @param writeLog 日志队列个数
     */
    public static void setWriteLog(Integer writeLog) {
        LogProcessor.writeLog = writeLog;
    }
    /**
     * 初始化
     * @param persistentLog 持久化
     * @param queryCurrentUser 当前用户
     */
    public  static void  init(IPersistentLog persistentLog, IQueryCurrentUser queryCurrentUser) {
        writeLogQueue = new LinkedBlockingQueue<>();
        iPersistentLog = persistentLog;
        iQueryCurrentUser = queryCurrentUser;
        if (!Objects.isNull(iPersistentLog) && !Objects.isNull(queryCurrentUser)) {
            hasInit = Boolean.TRUE;
        }
    }
    /**
     * 日志写入
     */
    public  static  void run() {
        WRITELOG_EXECUTOR.submit((Runnable) () -> {
            while (true) {
                try {
                    WriteLogDto poll = writeLogQueue.poll(TWENTY, TimeUnit.SECONDS);
                    if (!Objects.isNull(poll)) {
                        iQueryCurrentUser.getCurrentUser(poll);
                        poll.setInput(StringUtil.cutLenStr(poll.getInput(), TWO_THOUSAND));
                        poll.setContent(StringUtil.cutLenStr(poll.getContent(), TWO_THOUSAND));
                        poll.setIp(IPTool.getIpAddr(poll.getRequest()));
                        poll.setCreateTime(new Date());
                        poll.setExpirTime(DateTool.addMonths(poll.getCreateTime(), TWELVE));
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
     * @param writeLogDto 日志对象
     * @return 是否成功
     */
    public  static  Boolean write(WriteLogDto writeLogDto) {
        if (hasInit && !Objects.isNull(writeLogDto)) {
            if (Objects.isNull(writeLogDto.getRequest())) {
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                writeLogDto.setRequest(request);
            }

            writeLogQueue.add(writeLogDto);
        }
        return Boolean.TRUE;
    }

}
