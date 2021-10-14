package com.ze.aurora.leetcode;

import com.ze.aurora.annotation.LeetCode;

// 数组操作
public interface IArray {
    @LeetCode(name = "调整数组顺序使奇数位于偶数前面",
        idea = "双指针碰撞")
    int[] exchange(int[] nums);

    @LeetCode(name = "和为s的两个数字",
            desc = "在有序数组中找到一对和为target的数字",
            idea = "双指针碰撞")
    int[] twoSum(int[] nums, int target);

    @LeetCode(name = "矩阵中的路径", desc = "在矩阵中是否存在一条单词路线")
    boolean exist(char[][] board, String word);

    @LeetCode(name = "机器人的运动范围",
            desc = "求机器人在矩阵中能到达的范围，横纵坐标的各数位之和不大于k",
            idea = "注意，有些地方满足条件，但是不能够一步步到达。（不能飞过去！！）")
    int movingCount(int m, int n, int k);
}
