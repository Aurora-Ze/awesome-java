package com.ze.aurora.strategy.impl;

import com.ze.aurora.annotation.LeetCode;
import com.ze.aurora.annotation.PoorPerformance;
import com.ze.aurora.strategy.ITree;
import com.ze.aurora.structure.Node;
import com.ze.aurora.structure.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class TreeImpl implements ITree {

    // 辅助函数，判断从第一个节点开始，A是否包含B
    public boolean isSubStructureSinceTop(TreeNode A, TreeNode B) {
        if (B == null) return true;
        if (A == null || A.val != B.val) return false;

        return isSubStructureSinceTop(A.left, B.left) && isSubStructureSinceTop(A.right, B.right);
    }

    @Override
    @PoorPerformance(reason = "从上到下递归时，很多节点重复判断")
    public boolean isSubStructure(TreeNode A, TreeNode B) {
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


    @Override
    @LeetCode(idea = "很自然的想到dfs，但是要确定是先序中序还是后序，这样递归代码就有了相应的结构")
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        pathSum(root, target, list, result);

        return result;
    }

    public void pathSum(TreeNode node, int target, List<Integer> list, List<List<Integer>> result) {
        if (node == null) {
            return;
        }
        // dfs：先序遍历
        int newValue = target - node.val;
        list.add(node.val);
        if (newValue == 0 && node.left == null && node.right == null) {
            result.add(new ArrayList<>(list)); // 拷贝一份新的！
            list.remove(list.size()-1);
            return;
        }
        pathSum(node.left, newValue, list, result);
        pathSum(node.right, newValue, list, result);
        // 回溯
        list.remove(list.size()-1);
    }


    /** 定义全局变量**/
    Node pre = null;
    Node head = null;

    @Override
    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        inOrderRecur(root);
        // pre最后指向尾节点
        head.left = pre;
        pre.right = head;
        return head;
    }

    public void inOrderRecur(Node cur) {
        if (cur == null) return;

        inOrderRecur(cur.left);
        if (pre == null) { // 为链表头节点
            head = cur;
        } else {
            pre.right = cur;
            cur.left = pre;
        }

        pre = cur;
        inOrderRecur(cur.right);
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        test(node);
        System.out.println(node.val);
    }

    public static void test(TreeNode node) {
        node = new TreeNode(2);
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
