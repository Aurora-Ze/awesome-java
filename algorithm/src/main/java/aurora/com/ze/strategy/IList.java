package aurora.com.ze.strategy;

import aurora.com.ze.annotation.LeetCode;
import aurora.com.ze.structure.ListNode;


// 提供链表的一些方法
public interface IList {

    @LeetCode(name = "删除链表的节点")
    ListNode deleteNode(ListNode head, int val);

    @LeetCode(name = "链表中倒数第k个节点", desc = "返回节点")
    ListNode getKthFromEnd(ListNode head, int k);

    @LeetCode(name = "合并两个排序的链表")
    ListNode mergeTwoLists(ListNode l1, ListNode l2);

    @LeetCode(name = "两个链表的第一个公共节点")
    ListNode getIntersectionNode(ListNode headA, ListNode headB);
}
