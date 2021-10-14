package com.ze.aurora.testcase;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

/**
 * @author Aurora
 * @date 2021/10/11 13:58
 */
public class TestString {
    static int count = 1;

    public static void main(String[] args) {
        TestString test = new TestString();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    test.print();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    test.print();
                }

            }

        }).start();
    }

    public synchronized void print() {
        notifyAll();
        System.out.println(count);
        count++;
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
