package com.ze.aurora.testcase;

import org.junit.jupiter.api.Test;
import sun.misc.Unsafe;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Aurora
 * @date 2021/10/8 14:05
 */
public class TestJUC {
    static Object obj = new Object();
    static long l = 5;
    int a;
    int b;
    volatile int c;

    public static void main(String[] args) {
//        TestJUC test = new TestJUC();
//        test.testVolatile();
        Unsafe.getUnsafe().compareAndSwapInt(obj, l,1, 2);

    }


    public void testVolatile() {
        new Thread(() -> {
            while (true) {
                System.out.printf("a = %d, b = %d, c = %d\n", a, b, c);
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a = 1;
            b = a;
            c = a;
        }).start();


    }

    public void testJUCTools() throws InterruptedException, BrokenBarrierException {
        // 多线程执行后在执行主线程
        CountDownLatch latch = new CountDownLatch(5);
        latch.await();

        // 多线程执行后等待，然后再执行各自
        CyclicBarrier barrier = new CyclicBarrier(5);
        barrier.await();
    }

    public void testReentrantLock() {
        ReentrantLock lock = new ReentrantLock(true);
        Runnable task = () -> useLock(lock);

        for (int i = 0; i < 2; i++) {
            Thread t = new Thread(task);
            t.start();
        }
    }

    public void useLock(ReentrantLock lock) {
        lock.lock();
        System.out.println(Thread.currentThread() + " execute");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
    }

}
