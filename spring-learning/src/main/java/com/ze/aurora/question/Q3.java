package com.ze.aurora.question;

import java.util.Scanner;

/**
 * @author Aurora
 * @date 2021/9/28 19:47
 */
public class Q3 {
    static int[] results;
    static int count;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cnt = Integer.parseInt(sc.nextLine());
        Q3 q = new Q3();

        results = new int[cnt];
        for (int i = 0; i < cnt; i ++) {
            count = 0;
            String[] head = sc.nextLine().split(" ");
            String[] val = sc.nextLine().split(" ");
            q.calculate(parseIntArray(val), Integer.parseInt(head[1]));
            results[i] = count;
        }
        sc.close();

        for (int i = 0; i < cnt; i ++) {
            System.out.println(results[i]);
        }
    }

    public void calculate(int[] nums, int k) {
        boolean[] visited = new boolean[nums.length];
        for (int i = 0; i < nums.length - k + 1; i ++) {
            dfs(nums, k, i, 0, visited);
        }

    }


    int ack = 0; // 累加和
    /**
     * times: 累计计算的元素个数
     * index: 遍历的下标
     * **/
    public void dfs(int[] nums, int k, int index, int times, boolean[] visited) {
        // 直接返回
        if (index == nums.length || visited[index] || times == k) {
            return;
        }

        // 累计
        visited[index] = true;
        ack += nums[index];
        if (++times == k && (ack & 1) == 0) {
            count++;
        }

        if (times != k) {
            for (int i = index + 1; i < nums.length; i ++) {
                dfs(nums, k, i, times, visited);
            }
        }
        visited[index] = false;
        ack -= nums[index];
        times --;
    }

    public static int[] parseIntArray(String[] str) {
        int[] nums = new int[str.length];
        for (int i = 0; i < str.length; i ++) {
            nums[i] = Integer.parseInt(str[i]);
        }
        return nums;
    }
}
