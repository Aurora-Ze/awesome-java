package com.ze.aurora.series.impl;

import com.ze.aurora.series.IRotationArray;

/**
 * @author Aurora
 * @date 2021/10/16 10:50
 */
public class RotationArrayImpl implements IRotationArray {

    public static void main(String[] args) {
        IRotationArray impl = new RotationArrayImpl();
        int res = impl.findMin(new int[]{5,6,0,1,2,3,4});
        System.out.println(res);
//        for (int i = 0; i < res.length; i++) {
//            System.out.print(res[i] + " ");
//        }
    }

    // nums = [0,1,2,3,4,5,6], k = 3  ==>  [4,5,6,0,1,2,3]
    @Override
    public int[] rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - k - 1);
        reverse(nums, nums.length - k, nums.length - 1);

        reverse(nums, 0, nums.length - 1);
        return nums;
    }

    public void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;

            left ++;
            right --;
        }
    }

    @Override
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        int minIndex = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[left] <= nums[minIndex]) {
                minIndex = left;
            }
            if (nums[left] < nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return minIndex;
    }

    // nums = [4,5,6,7,0,1,2], target = 0
    @Override
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) { // 正常退出循环即表示未找到
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                return mid;
            }

            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target <= nums[mid]) { // 取等号，防止target在边界时被忽略
                    right = mid;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] <= target && target <= nums[right]) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    @Override
    public int searchWithDuplicate(int[] nums, int target) {
        return 0;
    }
}
