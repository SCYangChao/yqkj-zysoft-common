package com.yqkj.zysoft.common.core.log.annotation;

import java.lang.annotation.*;

/**
 * @ClassName Logging
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/3/19 10:14
 * @Version 1.0
 **/
@Target(value={ElementType.FIELD,ElementType.PARAMETER,ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Logging {
 /**
  * 数据模块名称
   * @return
  */
 String value();
 /**
  * 返回的表达式是否能成功
  * @return
  */
 String hasResult() default "";
 /**
  * 输入 可支持EL表达式
  * @return
  */
 String input()  default  "";
 /**
  * 输出 可支持EL表达式
  * @return
  */
 String output() default "";

 /**
  * 是否入库
  * @return
  */
 boolean hasDb() default  true;
}
