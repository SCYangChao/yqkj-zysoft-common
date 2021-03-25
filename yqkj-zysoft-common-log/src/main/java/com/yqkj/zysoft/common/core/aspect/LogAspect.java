package com.yqkj.zysoft.common.core.aspect;


import com.yqkj.zysoft.common.core.log.annotation.Logger;
import com.yqkj.zysoft.common.core.log.annotation.Logging;
import com.yqkj.zysoft.common.core.log.core.LogProcessor;
import com.yqkj.zysoft.common.core.log.core.dto.FactoryWriteLog;
import com.yqkj.zysoft.common.core.log.core.dto.WriteLogDto;
import com.yqkj.zysoft.common.core.log.core.service.IQueryCurrentUser;
import com.yqkj.zysoft.common.core.util.ExpressionTool;
import com.yqkj.zysoft.common.string.StringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.expression.EvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @ClassName LogAspect
 * @Description 
 * @Author yangchao.cool@gmail.com
 * @Date 2021/3/25 16:43
 * @Version 1.0
 **/
@Aspect
@Component
public class LogAspect {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(LogAspect.class);

    @Autowired(required = false)
    private IQueryCurrentUser queryCurrentUser;

    private  static  final String SUCCESS = "200";

    /**
     * 正常日志
     *
     * @param jp
     * @throws Exception
     */
    @Around("@within(com.yqkj.zysoft.common.core.log.annotation.Logger)  && @annotation(com.yqkj.zysoft.common.core.log.annotation.Logging)")
    public Object doLogging(ProceedingJoinPoint jp) throws Throwable {
        long startTime = System.currentTimeMillis();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        Logger logger = AnnotationUtils.findAnnotation(jp.getTarget().getClass(), Logger.class);
        Method method = ((MethodSignature) jp.getSignature()).getMethod();
        Logging logging = AnnotationUtils.findAnnotation(method, Logging.class);

        Long takeTime = 0L;
        String ip = "";
        Object object = null;
        String errrorCode = SUCCESS;
        String errorMsg = "";
        try {
            object = jp.proceed();

            return object;
        } catch (Throwable e) {
            errorMsg = e.getMessage();
            errrorCode = "500";
            throw e;
        }finally {
            if(logger.hasDb() && logging.hasDb()) {
                WriteLogDto instance = FactoryWriteLog.instance();
                instance.setSys(logger.sys());
                instance.setModel(logger.model());
                instance.setTakeTime(takeTime);
                instance.setIp(ip);
                instance.setLogStatus(errrorCode);
                String input = logging.input();
                instance.setRequest(request);
                instance.setModelInfo(String.format("%s->%s" , logger.value(),logging.value()));
                if(!Objects.isNull(queryCurrentUser)){
                    queryCurrentUser.getCurrentUser(instance);
                }
                EvaluationContext context = ExpressionTool.getEvaluationContext(((MethodSignature)jp.getSignature()).getParameterNames(),
                        jp.getArgs() , object,instance.getUserInfo(), request);
                input = queryInputValue(input, context , request);
                instance.setInput(input);
                String output = logging.output();
                if(StringUtil.isNotBlank(output)){
                    output =  ExpressionTool.getSpelValue(context , output);
                }else {
                    output = Objects.isNull(object)?"":object.toString();
                }
                instance.setContent(output);
                String hasResult = logging.hasResult();
                LogProcessor.write(instance);
            }
        }
    }
    /**
     * 
     * @param input
     * @param context
     * @param request
     * @return
     * @throws IOException
     */
    private String queryInputValue(String input, EvaluationContext context , HttpServletRequest request) throws IOException {
        input =  ExpressionTool.getSpelValue(context , input);
        return input;
    }

}
