package com.ze.aurora.entity;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author Aurora
 * @date 2021/9/18 16:31
 */
@Configuration
public class Student {
    String name;
    int id;

    public Student(){};

    @Bean(autowireCandidate = false)
    public Student getBean() {
        return new Student("remiwu", 20185335);
    }

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
