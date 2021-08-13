package com.ze.aurora.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface PoorPerformance {
    /**
    * 有待优化的原因
    * */
    String reason() default "有待优化的原因";
}
