package com.ze.aurora.testcase;

/**
 * @author Aurora
 * @date 2021/10/3 10:42
 */
public class TestJVM {

    Student s = new Student();
    Teacher t = new Teacher();

    class Student {
        void call() {
            t.call();
        }
    }

    class Teacher {
        void call() {
            s.call();
        }
    }

    public void testSOE() {
        s.call();
    }

    public static void main(String[] args) {
        TestJVM obj = new TestJVM();
        obj.testSOE();
    }
}
