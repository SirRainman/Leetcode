package dataStructure.array.bigNum;

import dataStructure.ListNode;

public class Q2_AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // TODO: 想一想为什么要用头节点？？？
        ListNode newHead = new ListNode(-1), pre = newHead, cur;
        newHead.next = null;
        int carry = 0;
        while(l1 != null || l2 != null || carry > 0) {
            int a = 0, b = 0;
            if(l1 != null){
                a = l1.val;
                l1 = l1.next;
            }
            if(l2 != null) {
                b = l2.val;
                l2 = l2.next;
            }
            cur = new ListNode((a + b + carry) % 10);
            carry = (a + b + carry) / 10;
            cur.next = null;
            pre.next = cur;
            pre = cur;
        }
        return newHead.next;
    }
}
