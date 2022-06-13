package com.liuqing.lqmybatis.log;


import java.lang.annotation.*;

/**
 * 日志记录
 * @author liuqing01
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface LogRecord {

     String content() default "";

    String bizNo() default  "";

    String operator() default  "";
}
