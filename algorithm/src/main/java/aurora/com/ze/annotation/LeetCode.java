package aurora.com.ze.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)        // annotation only used in Method
@Retention(RetentionPolicy.SOURCE) // annotation only exist in source code status
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

    /**
     * 补充信息
     * */
    String extra() default "补充信息";
}
