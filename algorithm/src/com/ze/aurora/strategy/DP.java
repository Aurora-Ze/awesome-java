package com.ze.aurora.strategy;

import com.ze.aurora.annotation.LeetCode;

public interface DP {

    @LeetCode(name = "斐波那契数列", desc = "返回该数列的第n项")
    int fib(int n);

    @LeetCode(name = "青蛙跳台阶问题", desc = "跳上n级台阶总共有多少种跳法")
    int numWays(int n);

    @LeetCode(name = "股票的最大利润", desc = "买卖一次的最大利润")
    int maxProfit(int[] prices);
}
