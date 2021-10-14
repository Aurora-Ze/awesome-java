package com.ze.aurora.testcase;

/**
 * @author Aurora
 * @date 2021/9/30 13:54
 */
public class TestLock {

    public static final Object obj = new Object();

    public synchronized void execute() throws InterruptedException{
        exec();
    }

    public void exec() {
        System.out.printf("%s get lock\n", Thread.currentThread());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *  测试 synchrnized 锁方法
     * @param
     * @return void
    */
    public void testLockOnMethod() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                exec();
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                exec();
            }
        });
        t2.start();
    }

    /**
     *  测试 synchrnized 锁对象
     * @param
     * @return void
    */
    public void testLockOnObject() {
       new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj) {
                    exec();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj) {
                    exec();
                }
            }
        }).start();
    }

    public static void main(String[] args) throws InterruptedException {
        TestLock lock = new TestLock();
//        lock.testLockOnObject();
        lock.testLockOnMethod();
    }
}
