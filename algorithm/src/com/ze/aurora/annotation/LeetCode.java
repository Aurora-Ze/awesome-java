package com.ze.aurora.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)         // 注解可用于方法
@Retention(RetentionPolicy.SOURCE) //  注解只存在于源码
public @interface LeetCode {
    @Deprecated
    String url() default  "direct link";
    String name() default "title of the question";
    String desc() default "to explain the question";
    String idea() default "so easy";
}
