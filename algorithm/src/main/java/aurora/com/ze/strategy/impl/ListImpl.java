package aurora.com.ze.strategy.impl;

import aurora.com.ze.strategy.IList;
import aurora.com.ze.structure.ListNode;


public class ListImpl implements IList {

    @Override
    public ListNode deleteNode(ListNode head, int val) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode pre = dummyHead;
        while (head != null) {
            if (head.val == val) {
                pre.next = head.next;
            }
            pre = head;
            head = head.next;
        }
        return dummyHead.next;
    }

    @Override
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode right = head;
        for (int i = 0; i < k; i ++) {
            right = right.next;
        }

        while (right != null) {
            right = right.next;
            head = head.next;
        }
        return head;
    }

    @Override
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode res = head;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                head.next = l1;
                l1 = l1.next;
            } else {
                head.next = l2;
                l2 = l2.next;
            }
            head = head.next;
        }

        if (l1 != null) head.next = l1;
        if (l2 != null) head.next = l2;
        return res.next;
    }

    @Override
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA, b = headB;
        while (a != b) {
           a = a == null ? headB : a.next;
           b = b == null ? headA : b.next;
        }
        return a;
    }
}
