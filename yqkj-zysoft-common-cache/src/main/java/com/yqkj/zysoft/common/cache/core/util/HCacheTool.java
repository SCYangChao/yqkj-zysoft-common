package com.yqkj.zysoft.common.cache.core.util;

import com.yqkj.zysoft.common.cache.core.env.SpringEnv;
import org.apache.commons.lang3.StringUtils;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Objects;

/**
 * @ClassName PathTool
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/1/25 16:11
 * @Version 1.0
 **/
public final class HCacheTool {
    /**
     * 私有构造函数
     */
    private  HCacheTool() {

    }
    /**
     * 缓存KEY
     * @param springEnv 环境
     * @param path 路径
     * @param  name  方法名称
     * @return 返沪参数
     */
    public static String  path(SpringEnv springEnv, String path, String name) {
        StringBuilder keySb = new StringBuilder();

        keySb.append(springEnv.getApplicationName());
        if (StringUtils.isNotBlank(path)) {
            keySb.append(":").append(path);
        } else {
            keySb.append(":").append(name);
        }
        return keySb.toString();
    }
    /**
     * 上下文创建
     * @param argNames 参数名称
     * @param args 参数
     * @return EvaluationContext 上下文
     */
    public  static  EvaluationContext getEvaluationContext(String[] argNames, Object[] args) {
        EvaluationContext context = new StandardEvaluationContext();
        if (!Objects.isNull(argNames) && argNames.length > 0) {
            for (int index = 0; index < argNames.length; index++) {
                context.setVariable(argNames[index], args[index]);
            }
        }
        return  context;
    }
    /**
     * 获取KEY
     * @param context 上下文
     * @param key key
     * @param path 路径
     * @return  返回组装好的Key
     */
    public static String getKey(EvaluationContext context, String key, String path) {
        if (StringUtils.isBlank(key)) {
            return path;
        }
        String value = getSpelValue(context, key);
        if (StringUtils.isNotBlank(value)) {
            return String.format("%s:%s", path, value);
        }
        return path;
    }
    /**
     * 获取缓存KEY
     * @param context 上下文
     * @param key key
     * @param path  路径
     * @return 返回失效的key
     */
    public static String getEvictKey(EvaluationContext context, String key, String path) {
        if (StringUtils.isBlank(key)) {
            return String.format("%s:%s", path, "*");
        }
        String value = getSpelValue(context, key);
        if (StringUtils.isNotBlank(value)) {
            return String.format("%s:%s", path, value);
        }
        return String.format("%s:%s", path, "*");
    }

    /**
     * 根据上下文获取数据
     * @param context 上下文
     * @param key key
     * @return 返回spel值
     */
    private static String getSpelValue(EvaluationContext context, String key) {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(key);
        return expression.getValue(context, String.class);
    }

}
