package com.ze.aurora.strategy;

import com.ze.aurora.structure.TreeNode;
import com.ze.aurora.annotation.LeetCode;


// 提供树的各种方法
public interface ITree {

    @LeetCode(name = "树的子结构", desc = "判断B是否是A的子结构")
    boolean isSubStructure(TreeNode A, TreeNode B);

    @LeetCode(name = "二叉树的镜像", desc = "输入一棵树，返回它的镜像")
    TreeNode mirrorTree(TreeNode root);

    @LeetCode(name = "对称的二叉树", desc = "判断一个树是不是对称的")
    boolean isSymmetric(TreeNode root);
}
