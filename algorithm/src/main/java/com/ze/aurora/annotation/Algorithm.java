package com.ze.aurora.annotation;

import java.lang.annotation.*;

/**
 * @author Aurora
 * @date 2021/10/9 16:55
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Algorithm {
    String category() default "";

    String desc() default "";
}
