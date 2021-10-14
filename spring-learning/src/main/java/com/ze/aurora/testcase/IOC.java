package com.ze.aurora.testcase;

import com.ze.aurora.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

/**
 * @author Aurora
 * @date 2021/9/18 20:09
 */
public class IOC {

    public static void injectWithXML() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml");
        Student student1 = ctx.getBean("student1", Student.class);
        System.out.println(student1);
    }

    public static void injectWithAnnotation() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Student.class); // 不传class会报错

        Student student = ctx.getBean("getBean", Student.class);
        System.out.println(student);
    }


    /**
    * 断言，如果不符合条件则直接抛出异常
    */
    public static void testAssertSuccess() {
        Assert.isNull(null, "test assert success");
    }

    public static void testAssertFail() {
        Assert.isNull("123", "test assert fail");
    }

}
