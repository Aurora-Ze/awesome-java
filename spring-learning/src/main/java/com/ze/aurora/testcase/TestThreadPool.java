package com.ze.aurora.testcase;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Aurora
 * @date 2021/10/14 15:02
 */
public class TestThreadPool {
    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(5,
                10,
                1000,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                null,
                new ThreadPoolExecutor.CallerRunsPolicy());

        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
            }
        });
    }
}
