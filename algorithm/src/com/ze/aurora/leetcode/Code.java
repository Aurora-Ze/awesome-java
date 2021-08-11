package com.ze.aurora.leetcode;

public class Code {

    public static void main(String[] args) {
        char ch = Code.firstUniqChar("leetcode");
        System.out.println(ch);
    }

    class TreeNode {
        TreeNode(int val) {
            this.val = val;
        }
        int val;
        TreeNode left;
        TreeNode right;
    }

    // 返回字符串中第一个只出现一次的字符
    public static char firstUniqChar(String s) {
        int[] ch = new int[26];

        for (int i = 0; i < s.length(); i ++) {
            ch[s.charAt(i) - 'a'] ++;
        }

        for (int i = 0; i < s.length(); i ++) {
            if (ch[s.charAt(i) - 'a'] == 1) {
                return s.charAt(i);
            }
        }

        return ' ';
    }


    // 判断B是否是A的子结构 todo 可以优化，因为从上到下递归时，很多节点重复判断了
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null || A == null) return false;

        boolean match = isSubStructureSinceTop(A, B);
        if (match) {
            return true;
        } else {
            return isSubStructure(A.left, B) || isSubStructure(A.right, B);
        }
    }
    // 辅助函数，判断从第一个节点开始，A是否包含B
    public boolean isSubStructureSinceTop(TreeNode A, TreeNode B) {
        if (B == null) return true;
        if (A == null || A.val != B.val) return false;

        return isSubStructureSinceTop(A.left, B.left) && isSubStructureSinceTop(A.right, B.right);
    }


    public TreeNode mirrorTree(TreeNode root) {
        return null;
    }
}
