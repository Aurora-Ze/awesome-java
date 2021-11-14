package com.ze.aurora.series;

import com.ze.aurora.annotation.LeetCode;

/**
 * 旋转数组系列题目
 * @author Aurora
 * @date 2021/10/16 10:50
 */
public interface IRotationArray {

    @LeetCode(name = "旋转数组")
    int[] rotate(int[] nums, int k);

    @LeetCode(name = "寻找旋转排序数组中的最小值")
    int findMin(int[] nums);

    @LeetCode(name = "搜索旋转排序数组", desc = "无重复")
    int search(int[] nums, int target);

    @LeetCode(name = "搜索旋转排序数组Ⅱ", desc = "有重复元素，返回下标最小的一个")
    int searchWithDuplicate(int[] nums, int target);
}
