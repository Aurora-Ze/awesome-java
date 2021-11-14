package com.ze.aurora;

import com.ze.aurora.entity.Student;
import com.ze.aurora.testcase.IOC;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;

/**
 * @author Aurora
 * @date 2021/9/18 16:25
 */
@SpringBootApplication
@MapperScan("com.ze.aurora.mapper")
public class LearningApplication {
    public static void main(String[] args) {
        SpringApplication.run(LearningApplication.class, args);

        IOC.injectWithAnnotation();
        IOC.injectWithXML();

        IOC.testAssertSuccess();
        IOC.testAssertFail();
    }

    @Test
    public void test() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        String name = "com.ze.aurora.entity.Student";
        Class clazz = null;
        Constructor[] cons = null;
        Constructor con = null;
        try {
            clazz = Class.forName(name);
            cons = clazz.getConstructors();
            clazz.getConstructor(String.class, Integer.class);
        } catch (ClassNotFoundException | SecurityException | NoSuchMethodException e) {
            System.out.printf("Error occurs, err = %e\n", e);
            e.printStackTrace();
        }

        for (Constructor construct : cons) {
            Student s = (Student) construct.newInstance();
            System.out.println(s);
        }
    }
}
