package dataStructure.list;

import dataStructure.ListNode;

/**
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 */
public class Q206_ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        // TODO: 头插法
        ListNode newHead = new ListNode(-1), next = null;
        while (head != null) {
            next = head.next;
            head.next = newHead.next;
            newHead.next = head;
            head = next;
        }
        return newHead.next;
    }

    public ListNode reverseList2(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    // TODO: 递归版本没看懂
    //  关键在于反向工作：假设列表的其余部分已经被反转，现在我们应该如何反转它前面的部分？
    public ListNode reverseList3(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode next = head.next;
        ListNode newHead = reverseList2(next);
        next.next = head;
        head.next = null;
        return newHead;
    }
}
