package com.ze.aurora.strategy.impl;

import com.ze.aurora.strategy.DP;

public class DPImpl implements DP {

    @Override
    public int fib(int n) {
        if (n < 2) return n;

        int i = 0, j = 1;
        int res = i + j;

        while (n >= 2) {
            res = (int)((i + j) % (1e9 + 7));
            i = j;
            j = res;

            n--;
        }
        return res;
    }

    @Override
    public int numWays(int n) {
        if (n < 2) return 1;
        int i = 1, j = 1;

        while (n >= 2) {
            int tmp = (int)((i + j)%(1e9+7));
            i = j;
            j = tmp;
            n--;
        }
        return j;
    }

    @Override
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        // 需要记录两个值：利润和股票最低价
        int minPrice = prices[0];
        int profit = 0;

        for (int i = 1; i < prices.length; i ++) {
            profit = Math.max(profit, prices[i]-minPrice);
            minPrice = Math.min(minPrice, prices[i]);
        }
        return profit;
    }
}
