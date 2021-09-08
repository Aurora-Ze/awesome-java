package aurora.com.ze.strategy;

import aurora.com.ze.annotation.LeetCode;
import aurora.com.ze.structure.Node;
import aurora.com.ze.structure.TreeNode;
import java.util.List;


// 提供树的各种方法
public interface ITree {

    @LeetCode(name = "树的子结构", desc = "判断B是否是A的子结构")
    boolean isSubStructure(TreeNode A, TreeNode B);

    @LeetCode(name = "二叉树的镜像", desc = "输入一棵树，返回它的镜像")
    TreeNode mirrorTree(TreeNode root);

    @LeetCode(name = "对称的二叉树", desc = "判断一个树是不是对称的")
    boolean isSymmetric(TreeNode root);

    @LeetCode(name = "二叉树中和为某一值的路径", desc = "从根节点到叶子节点的才算路径")
    List<List<Integer>> pathSum(TreeNode root, int target);

    @LeetCode(name = "二叉搜索树与双向链表", desc = "把一颗二叉搜索树原地变成双向链表")
    Node treeToDoublyList(Node root);
}
