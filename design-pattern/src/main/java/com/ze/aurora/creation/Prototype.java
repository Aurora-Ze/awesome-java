package com.ze.aurora.creation;

/**
 * 原型
 *
 * 每个对象自己提供克隆方法，可以让调用方得到一个克隆的对象，且与调用方逻辑解耦。
 * @author Aurora
 * @date 2021/11/9 18:02
 */
public class Prototype {

    interface Person {
        Person clone();
    }

    class Student implements Person {
        String name;
        String id;

        public Person clone() {
            Student student = new Student();
            student.name = this.name;
            student.id = this.id;
            return student;
        }
    }

    class Teacher implements Person {
        String name;
        String department;


        public Person clone() {
            Teacher teacher = new Teacher();
            teacher.name = this.name;
            teacher.department = this.department;
            return teacher;
        }
    }
}
