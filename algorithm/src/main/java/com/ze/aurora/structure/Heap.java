package com.ze.aurora.structure;


/**
 * @author Aurora
 * @date 2021/9/9 20:11
 */
public class Heap {
    public static void sort(int[] nums) {
        int len = nums.length;

        // 建堆，从最后的父节点开始下沉
        for (int i = (len - 1) / 2; i >= 0; i--) {
            upToDown(nums, i, len - 1);
        }

        // 排序，逐渐缩小堆的范围
        for (int i = len - 1; i >= 0; i--) {
            swap(nums, 0, i);
            upToDown(nums, 0, i-1);
        }
    }

    // 下沉，超过end或遇到两个更小的子节点时，停止下沉
    private static void upToDown(int[] nums, int k, int end) {
        while (2 * k + 1 <= end) {
            int next = 2 * k + 1;
            // 取较大的子节点
            if (next <= end - 1 && nums[next] < nums[next+1]) {
                next++;
            }
            if (nums[k] >= nums[next]) {
                break;
            }
            swap(nums, k, next);
            k = next;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
