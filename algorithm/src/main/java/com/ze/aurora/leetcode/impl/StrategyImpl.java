package com.ze.aurora.leetcode.impl;


import com.ze.aurora.leetcode.IStrategy;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Aurora
 * @date 2021/9/6 15:45
 */
public class StrategyImpl implements IStrategy {
    int mode = 1000000007;
    public int cuttingRope(int n) {
        if (n <= 3) return n-1;
        long result = 1;

        int count = n / 3;
        // 最后一个3先不要乘，避免之后除以3除不尽
        while (count > 1) {
            result = (result*3) % mode;
            count --;
        }

        int remain = n % 3;
        if (remain == 1) {
            result = (result*4) % mode;
        } else if (remain == 2) {
            result = (result*6) % mode;
        } else {
            result = result * 3 % mode;
        }
        return (int)result;
    }

    // 快速幂求余
    public static int fastPower(int base, int exp, int mode) {
        int result = 1;
        base = base % mode;

        while(exp > 0) {
            if ((exp & 1) == 1) {
                result = result * base % mode;
            }
            base = base * base % mode;
            exp = exp >> 1;
        }
        return result;
    }

    // 中位数
    class MedianFinder {
        Queue<Integer> A, B;
        MedianFinder() {
            A = new PriorityQueue<>((x,y) -> y - x);
            B = new PriorityQueue<>();
        }

        void addNum(int num) {
            if (B.size() == 0 || num >= B.peek()) {
                B.add(num);
            } else {
                A.add(num);
            }
            // 均衡
            while(A.size() > B.size()) {
                B.add(A.remove());
            }
            while(B.size() > A.size()+1) {
                A.add(B.remove());
            }
        }

        double findMedian() {
            if (B.size() == 0) {
                return 0;
            } else if (A.size() == 0) {
                return B.remove();
            } else if (A.size() == B.size()) {
                return (A.remove() + B.remove()) / 2.0;
            } else {
                return B.remove();
            }
        }
    }


    public static void main(String[] args) {
        ArrayImpl impl = new ArrayImpl();


    }

    public static void test(Object obj) {
        if (obj instanceof ArrayImpl) {

        }
    }

}
