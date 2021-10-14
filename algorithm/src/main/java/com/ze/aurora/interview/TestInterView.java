package com.ze.aurora.interview;

import java.util.*;

/**
 * @author Aurora
 * @date 2021/9/11 19:47
 */
public class TestInterView {
    public int[] find1 (int[] arg) {
        List<Integer> result = new ArrayList<>();
        if (arg == null || arg.length < 1) {

        } else if (arg.length == 1) {
            result.add(arg[0]);
        } else {
            Arrays.sort(arg);
            int slow = 0;
            int fast = 1;
            while (slow < arg.length && fast < arg.length) {
                if (arg[slow] == arg[fast]) {
                    fast ++;
                    continue;
                }
                if (slow == fast - 1) {
                    result.add(arg[slow]);
                    slow = fast;
                } else if (slow != fast - 1) {
                    slow = fast;
                }
                fast ++;
            }

            if (slow == arg.length - 1) {
                result.add(arg[slow]);
            }
        }

        int[] nums = new int[result.size()];
        for (int i = 0; i < result.size(); i ++) {
            nums[i] = result.get(i);
        }
        return nums;
    }

    public int[] find (int[] arg) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arg.length; i ++) {
            map.put(arg[i], map.getOrDefault(arg[i], 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                result.add(entry.getKey());
            }
        }
        int[] nums = new int[result.size()];
        for (int i = 0; i < result.size(); i ++) {
            nums[i] = result.get(i);
        }
        return nums;
    }

    public static void main(String[] args) {
        TreeMap<Integer, Integer> map = new TreeMap();
        map.put(1,2);
        map.get(1);
    }

    public int[] subArraySum (int[] Array, int arrayLen, int subArrayLen) {
        if (arrayLen == 1 && subArrayLen == 1) {
            return new int[]{0, Array[0]};
        }

        int[] dp = new int[arrayLen];
        dp[0] = Array[0];
        int[] result = new int[2];
        for (int i = 1; i < arrayLen; i ++) {
            if (i < subArrayLen) {
                dp[i] = Array[i] + dp[i-1];
            } else {
                dp[i] = dp[i-1] - Array[i - subArrayLen] + Array[i];
                if (dp[i] > result[1]) {
                    result[1] = dp[i];
                    result[0] = i-subArrayLen+1;
                }
            }
        }
        return result;
    }

    public int getTeams (int[] heros) {
        int[] nums = new int[5];
        for (int i = 0; i < heros.length; i ++) {
            nums[heros[i]] ++;
        }
        return nums[0]*nums[1]*nums[2]*nums[3]*nums[4];
    }
}
