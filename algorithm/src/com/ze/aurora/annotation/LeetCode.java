package com.ze.aurora.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)         // 注解可用于方法
@Retention(RetentionPolicy.SOURCE) //  注解只存在于源码
public @interface LeetCode {

    String url() default  "direct link";
    /**
     * 对应leetcode上的题目名
     * */
    String name() default "对应leetcode上的题目名";

    /**
     * 对题目的简单描述
     */
    String desc() default "对题目的简单描述";

    /**
     * 思路
     * */
    String idea() default "思路";
}
