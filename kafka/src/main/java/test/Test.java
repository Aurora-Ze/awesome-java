package test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Aurora
 * @date 2021/11/21 16:03
 */
public class Test {
    public static void main(String[] args) {
        AtomicInteger count = new AtomicInteger(0);
        System.out.println(count.getAndIncrement());
        System.out.println(count.incrementAndGet());

        System.out.println((Integer.MIN_VALUE + 1) & 0x7fffffff);
    }
}
