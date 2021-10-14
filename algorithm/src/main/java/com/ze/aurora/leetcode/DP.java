package com.ze.aurora.leetcode;


import com.ze.aurora.annotation.LeetCode;
import com.ze.aurora.annotation.NowCoder;

// 动态规划相关题目
public interface DP {

    @LeetCode(name = "斐波那契数列", desc = "返回该数列的第n项")
    int fib(int n);

    @LeetCode(name = "青蛙跳台阶问题", desc = "跳上n级台阶总共有多少种跳法")
    int numWays(int n);

    @LeetCode(name = "股票的最大利润", desc = "买卖一次的最大利润")
    int maxProfit(int[] prices);

    @LeetCode(name = "连续子数组的最大和", desc = "求所有连续的子数组中和的最大值")
    int maxSubArray(int[] nums);

    @LeetCode(name = "礼物的最大价值", desc = "计算从左上角拿到右下角的礼物最大总价值")
    int maxValue(int[][] grid);

    @LeetCode(name = "把数字翻译成字符串", desc = "0~25分别对应a~z，求给定数字有多少种字母表示方法")
    int translateNum(int num);

    @LeetCode(name = "最长不含重复字符的子字符串", desc = "返回长度")
    int lengthOfLongestSubstring(String s);

    @NowCoder(name = "最长公共子序列Ⅱ", desc = "返回两个字符串的最长公共子序列")
    String longestCommonSubsequence(String text1, String text2);

    @NowCoder(name = "最长公共子串", desc = "返回两个字符串的最长公共子串")
    String LCS(String str1, String str2);
}
