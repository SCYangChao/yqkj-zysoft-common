package com.yqkj.zysoft.common.core.util;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName PathTool
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/1/25 16:11
 * @Version 1.0
 **/
public class ExpressionTool {

    /**
     * 上下文创建
     * @param argNames
     * @param args
     * @return
     */
    public  static  EvaluationContext getEvaluationContext(String[] argNames , Object[] args , Object result , String userName, HttpServletRequest request){
        EvaluationContext context = new StandardEvaluationContext();
        if(!Objects.isNull(argNames) && argNames.length > 0){
            for (int index =0; index< argNames.length; index ++ ){
                context.setVariable(argNames[index] , args[index]);
            }
        }
        if(!Objects.isNull(result)){
            if(result instanceof ResponseEntity){
                Object body = ((ResponseEntity) result).getBody();
                context.setVariable("result", body);
            }else {
                context.setVariable("result", result);
            }
        }
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String , String> requestMap = new HashMap<>();
        if(!CollectionUtils.isEmpty(parameterMap)){
            parameterMap.forEach((k,v)->{
                requestMap.put(k,request.getParameter(k));
            });
        }
        context.setVariable("envUserName" , userName);
        context.setVariable("requestMap",requestMap);
        return  context;
    }
    /**
     * 根据上下文获取数据
     * @param context
     * @param key
     * @return
     */
    public static String getSpelValue(EvaluationContext context, String key) {
        try {
            ExpressionParser parser = new SpelExpressionParser();
            Expression expression = parser.parseExpression(key ,new TemplateParserContext());
            return expression.getValue(context, String.class);
        }catch (Exception e){
            return  e.getMessage();
        }
    }
    /**
     * 根据上下文获取数据
     * @param context
     * @param key
     * @return
     */
    public static Boolean getSpelBooleanValue(EvaluationContext context, String key) {
        try {
            ExpressionParser parser = new SpelExpressionParser();
            Expression expression = parser.parseExpression(key);
            return expression.getValue(context, Boolean.class);
        }catch (Exception e){
            return  Boolean.FALSE;
        }
    }

}
