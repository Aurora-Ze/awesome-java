package com.ze.aurora.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Aurora
 * @date 2021/10/10 10:22
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface NowCoder {
    String name() default "牛客题目";

    String desc() default "题目描述";

    String idea() default "思路";
}
