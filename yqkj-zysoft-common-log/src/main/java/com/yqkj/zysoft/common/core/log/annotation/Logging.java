package com.yqkj.zysoft.common.core.log.annotation;

import java.lang.annotation.*;

/**
 * @ClassName Logging
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/3/19 10:14
 * @Version 1.0
 **/
@Target(value = {ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Logging {
 /**
   * @return 数据模块名称
  */
 String value();
 /**
  * @return 返回的表达式是否能成功
  */
 String hasResult() default "";
 /**
  * @return 输入 可支持EL表达式
  */
 String input()  default  "";
 /**
  * @return 输出 可支持EL表达式
  */
 String output() default "";
 /**
  * @return 是否入库
  */
 boolean hasDb() default  true;
}
