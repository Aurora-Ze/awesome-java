package com.ze.aurora.strategy;

import com.ze.aurora.annotation.LeetCode;

// 数组操作
public interface IArray {
    @LeetCode(name = "调整数组顺序使奇数位于偶数前面")
    int[] exchange(int[] nums);

    @LeetCode(name = "和为s的两个数字", desc = "在有序数组中找到一对和为target的数字")
    int[] twoSum(int[] nums, int target);


}
