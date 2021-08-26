package com.ze.aurora.strategy.impl;

import com.ze.aurora.strategy.IArray;

public class ArrayImpl implements IArray {
    @Override
    public int[] exchange(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            if (nums[left]%2 == 0) {
                // 移动右指针，指向奇数
                while (right > left  && nums[right]%2 == 0) right --;
                if (right == left) return nums;

                // 交换
                swap(nums, left, right);
                // 更新指针
                left ++; right --;
            } else {
                left ++;
            }
        }
        return nums;
    }

    public int[] swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
        return nums;
    }

    @Override
    public int[] twoSum(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int[] result = new int[2];

        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[]{nums[left], nums[right]};
            } else if (sum > target) {
                right --;
            } else if (sum < target) {
                left ++;
            }
        }
        return null;
    }
}
