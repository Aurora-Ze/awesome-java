package com.ze.aurora.strategy.impl;

import com.ze.aurora.annotation.LeetCode;
import com.ze.aurora.annotation.PoorPerformance;
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

    @PoorPerformance(reason = "长度n的dp数组可用一个滑动窗口变量代替")
    public int maxSubArray1(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        // dp[i]表示在nums[0]~nums[i]中，以nums[i]结尾的连续子数组的最大和
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int sum = nums[0];

        for (int i = 1; i < nums.length; i ++) {
            dp[i] = Math.max(dp[i-1]+nums[i], nums[i]);
            sum = Math.max(sum, dp[i]);
        }
        return sum;
    }

    @Override
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        // dp维护了nums数组中前一个元素结尾的连续子数组的最大和
        int dp = nums[0];
        int sum = nums[0];

        for (int i = 1; i < nums.length; i ++) {
            dp = Math.max(dp+nums[i], nums[i]);
            sum = Math.max(sum, dp);
        }
        return sum;
    }

    @Override
    @LeetCode(extra = "可以原地修改grid，把空间复杂度降到常数")
    public int maxValue(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int len = grid[0].length;

        // initialize the first line of the 2D-array
        // with n+1 size to escape accessing index -1
        int[] line = new int[len + 1];
        for (int j = 0; j < len; j ++) {
            line[j + 1] = grid[0][j] + line[j];
        }

        for (int i = 1; i < grid.length; i ++) {
            for (int j = 0; j < len; j ++) {
                // line index 1 ~ n
                line[j + 1] = grid[i][j] + Math.max(line[j], line[j + 1]);
            }
        }
        return line[len];
    }

}
