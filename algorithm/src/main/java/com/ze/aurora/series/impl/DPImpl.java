package com.ze.aurora.series.impl;

import com.ze.aurora.annotation.LeetCode;
import com.ze.aurora.annotation.PoorPerformance;
import com.ze.aurora.series.DP;

import java.util.HashMap;
import java.util.Map;

public class DPImpl implements DP {

    @Override
    public int fib(int n) {
        if (n < 2) return n;

        int i = 0, j = 1;
        int res = i + j;

        while (n >= 2) {
            res = (int) ((i + j) % (1e9 + 7));
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
            int tmp = (int) ((i + j) % (1e9 + 7));
            i = j;
            j = tmp;
            n--;
        }
        return j;
    }

    @Override
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        // record two values, one for profit and another for min price of stock
        int minPrice = prices[0];
        int profit = 0;

        for (int i = 1; i < prices.length; i++) {
            profit = Math.max(profit, prices[i] - minPrice);
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

        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
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

        for (int i = 1; i < nums.length; i++) {
            dp = Math.max(dp + nums[i], nums[i]);
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
        for (int j = 0; j < len; j++) {
            line[j + 1] = grid[0][j] + line[j];
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 0; j < len; j++) {
                // line index 1 ~ n
                line[j + 1] = grid[i][j] + Math.max(line[j], line[j + 1]);
            }
        }
        return line[len];
    }

    @Override
    @LeetCode(extra = "")
    public int translateNum(int num) {
        int x, y;
        // initialize variables, i for zero bit, j for one bit number
        int i = 1, j = 1, k = 0;

        while (num != 0) {
            y = num % 10;
            num /= 10;
            x = num % 10;

            int cur = x * 10 + y;
            k = (cur >= 10 && cur <= 25) ? i + j : j;
            // update variables for next loop
            i = j;
            j = k;
        }
        return k;
    }

    @Override
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;

        int start = 0, result = 1;
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            if (!map.containsKey(ch) || map.get(ch) < start) {
                result = Math.max(result, i - start + 1);
            } else {
                // set start to the next position of repeated element
                start = map.get(ch) + 1;
            }
            map.put(ch, i);
        }
        return result;
    }

    @Override
    public String longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()+1][text2.length()+1];

        for (int i = 1; i < text1.length() + 1; i ++) {
            for (int j = 1; j < text2.length() + 1; j ++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        int l1 = text1.length();
        int l2 = text2.length();
        while(l1 > 0 && l2 > 0) {
            if (text1.charAt(l1-1) == text2.charAt(l2-1)) {// dp数组从(1,1)~(l1,l2)，所以dp结束的下标要退后一步
                builder.append(text1.charAt(l1-1));
                l1 --;
                l2 --;
            } else {
                if (dp[l1-1][l2] > dp[l1][l2-1]) {
                    l1 --;
                } else {
                    l2 --;
                }
            }
        }
        return builder.reverse().toString();
    }

    @Override
    public String LCS(String str1, String str2) {
        // dp[i][j] = dp[i-1][j-1] + 1 or 0
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        // use end position and offset to get final String
        int x = 0;
        int maxLen = 0;

        for (int i = 1; i < str1.length() + 1; i++) {
            for (int j = 1; j < str2.length() + 1; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) { // i, j start at 1
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLen) {
                        x = i;
                        maxLen = dp[i][j];
                    }
                }
            }
        }

        if (maxLen == 0) {
            return "-1";
        }
        return str1.substring(x - maxLen, x);
    }

    @Override
    public int maxLength(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int start = 0;
        int result = 0;
        for (int i = 0; i < arr.length; i ++) {
            if (map.containsKey(arr[i]) && map.get(arr[i]) >= start) {
                result = Math.max(result, i - start);
                start = i;
            }
            map.put(arr[i], i);
        }
        result = Math.max(result, arr.length - start);

        return result;
    }


    public static void main(String[] args) {
        DPImpl impl = new DPImpl();
        int comm = impl.maxLength(new int[]{1,2,3,3,5,2,1,9});
        System.out.println(comm);
    }

}
