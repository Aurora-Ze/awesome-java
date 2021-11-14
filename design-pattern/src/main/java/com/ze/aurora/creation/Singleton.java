package com.ze.aurora.creation;

/**
 * 单例
 *
 * 全局只有一个对象，实现方式：懒汉式、饿汉式、双重校验锁、静态内部类、枚举
 * @author Aurora
 * @date 2021/11/9 19:31
 */
public class Singleton {
    /**
     * 懒汉式：延迟初始化；线程不安全
     * */
    static class Singleton1 {
        private static Singleton1 instance;

        private Singleton1(){};

        public static Singleton1 getInstance() {
            if (instance == null) {
                instance = new Singleton1();
            }
            return instance;
        }
    }

    /**
     * 饿汉式：线程安全
     * */
    static class Singleton2 {
        private static Singleton2 instance = new Singleton2();

        private Singleton2(){};

        public static Singleton2 getInstance() {
            return instance;
        }
    }

    /**
     * 双重校验锁：延迟加载、线程安全
     * */
    static class Singleton3 {
        private static volatile Singleton3 instance;

        private Singleton3(){};

        public static Singleton3 getInstance() {
            if (instance == null) {
                synchronized (Singleton3.class) {
                    if (instance == null) {
                        instance = new Singleton3();
                    }
                }
            }
            return instance;
        }
    }

    /**
     * 静态内部类：延迟加载、线程安全（由JVM来保证）
     * */
     static class Singleton4 {
        private Singleton4(){};

        private static class SingletonInstance {
            private static Singleton4 instance = new Singleton4();
        }

        public static Singleton4 getInstance() {
            return SingletonInstance.instance;
        }
    }

    /**
     * 枚举
     * */
    enum Singleton5 {
        INSTANCE;
    }

    public void test() {
        Singleton1.getInstance();
        Singleton2.getInstance();
        Singleton3.getInstance();
        Singleton4.getInstance();
        Singleton5 instance = Singleton5.INSTANCE;
    }
}
