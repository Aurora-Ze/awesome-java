package com.ze.aurora.testcase;

/**
 * @author Aurora
 * @date 2021/10/14 14:21
 */
public class TestThreadLocal extends Thread{
    ThreadLocal tl;

    public TestThreadLocal(ThreadLocal tl) {
        this.tl = tl;
    }

    public void set(int i) {
        tl.set(i);
    }

    public int get() {
        return (int) tl.get();
    }

    public static void main(String[] args) {
        TestThreadLocal test = new TestThreadLocal(new ThreadLocal());
        test.start();
    }

    @Override
    public void run() {

    }
}
