package com.ze.aurora.strategy.impl;

import com.ze.aurora.annotation.LeetCode;
import com.ze.aurora.strategy.ITree;
import com.ze.aurora.structure.TreeNode;

public class TreeImpl implements ITree {

    // 辅助函数，判断从第一个节点开始，A是否包含B
    public boolean isSubStructureSinceTop(TreeNode A, TreeNode B) {
        if (B == null) return true;
        if (A == null || A.val != B.val) return false;

        return isSubStructureSinceTop(A.left, B.left) && isSubStructureSinceTop(A.right, B.right);
    }

    @Override
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        // todo 可以优化，因为从上到下递归时，很多节点重复判断了
        if (B == null || A == null) return false;

        boolean match = isSubStructureSinceTop(A, B);
        return match ? true : isSubStructureSinceTop(A.left, B) || isSubStructureSinceTop(A.right, B);

    }

    @Override
    @LeetCode(idea = "做递归时，把mirrorTree看作一个已有方法，通过它来实现我们的目的")
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;
        // 利用递归先让节点的左右子树镜像，然后交换位置
        TreeNode left = mirrorTree(root.left);
        TreeNode right = mirrorTree(root.right);

        root.left = right;
        root.right = left;
        return root;
    }

    @Override
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left, root.right);
    }

    // 判断两棵树是不是对称的
    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        return left.val == right.val && isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

/**          先序、中序、后序遍历           **/
    public void preOrder(TreeNode root) {
        if (root == null) return;

        System.out.println(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    public void inOrder(TreeNode root) {
        if (root == null) return;

        inOrder(root.left);
        System.out.println(root.val);
        inOrder(root.right);
    }

    public void postOrder(TreeNode root) {
        if (root == null) return;

        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.val);
    }


}
