package dataStructure.list;

import dataStructure.ListNode;

public class QOffer18_DeleteNode {
    public ListNode deleteNode1(ListNode head, int val) {
        // TODO: 想一想为什么要设置头节点？操作方便
        ListNode empHead = new ListNode(-1);
        empHead.next = head;
        ListNode cur = head, pre = empHead;
        while(cur != null) {
            if(cur.val == val) {
                pre.next = cur.next;
                break;
            }
            pre = cur;
            cur = cur.next;
        }
        return empHead.next;
    }

    // TODO: 注意这种递归解法
    public ListNode deleteNode(ListNode head, int val) {
        if(head == null) return null;
        if(head.val == val) return head.next;
        head.next = deleteNode(head.next, val);
        return head;
    }
}
