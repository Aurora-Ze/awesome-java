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

    @Override
    public boolean exist(char[][] board, String word) {

        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board[0].length; j ++) {
                if (searchWord(board, i, j, word, 0)) return true;
            }
        }
        return false;
    }

    public boolean searchWord(char[][] board, int i, int j, String word, int charIndex) {
        if (!checkSearchCondition(board, i, j, charIndex, word)) {
            return false;
        }
        if (charIndex == word.length()-1) {
            return true;
        }

        // 当前位置置空，防止回头
        char tmp = board[i][j];
        board[i][j] = ' ';
        boolean find =  searchWord(board, i+1, j, word, charIndex+1) ||
                searchWord(board, i-1, j, word, charIndex+1) ||
                searchWord(board, i, j+1, word, charIndex+1) ||
                searchWord(board, i, j-1, word, charIndex+1);

        board[i][j] = tmp; // 还原
        return find;
    }

    public boolean checkSearchCondition(char[][] board, int i, int j, int charIndex, String word) {
        if (i < 0 || i > board.length-1 || j < 0 || j > board[0].length-1) {
            return false;
        }
        if (board[i][j] == ' ') {
            return false;
        }
        return board[i][j] == word.charAt(charIndex);
    }

    @Override
    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        return moving(0, 0, k, m, n, visited);
    }

    public int moving(int i, int j, int k, int m, int n, boolean[][] visited) {
        if (!checkInMovingBoard(i, j, m, n)) return 0;
        if (visited[i][j] || !canMoving(i, j, k)) return 0;
        // 可以移到当前位置

        visited[i][j] = true;
        return 1 + moving(i, j-1, k, m, n, visited)
                 + moving(i, j+1, k, m, n, visited)
                 + moving(i-1, j, k, m, n, visited)
                 + moving(i+1, j, k, m, n, visited);
    }

    public boolean checkInMovingBoard(int i, int j, int m, int n) {
        return i >= 0 && j >= 0 && i != m && j != n;
    }

    public boolean canMoving(int i, int j, int k) {
        return calBitCount(i) + calBitCount(j) <= k;
    }

    public int calBitCount(int number) {
        int sum = 0;
        while (number != 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
}
