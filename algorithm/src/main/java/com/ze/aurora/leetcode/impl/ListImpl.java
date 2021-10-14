package com.ze.aurora.leetcode.impl;

import com.ze.aurora.leetcode.IList;
import com.ze.aurora.structure.ListNode;


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

    @Override
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        ListNode slow, fast, node;
        slow = fast = node = head;
        while(fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 反转后面部分
        ListNode newHead = slow.next;
        slow.next = null;
        newHead = reverse(newHead);

        // 间隔插入前面
        while(newHead != null) {
            ListNode next = node.next;
            ListNode newNext = newHead.next;
            node.next = newHead;
            newHead.next = next;

            node = next;
            newHead = newNext;
        }
    }

    public ListNode reverse(ListNode node) {
        ListNode pre = null;
        while (node != null) {
            ListNode next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListImpl impl = new ListImpl();

        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;

        impl.reorderList(l1);

        for (;l1 != null; l1 = l1.next) {
            System.out.print(l1.val + " ");
        }
    }
}
