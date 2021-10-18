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
public class Q206_ReverseList {

    // TODO: 头插法
    public ListNode reverseList1(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = head;
        while(cur != null) {
            ListNode next = cur.next;
            cur.next = dummy.next;
            dummy.next = cur;
            cur = next;
        }
        return dummy.next;
    }

    // TODO：双指针
    public ListNode reverseList2(ListNode head) {
        ListNode pre = null, cur = head;
        while(cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    // TODO:
    //  关键在于反向工作：假设列表的其余部分已经被反转，现在我们应该如何反转它前面的部分？
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode nextNode = head.next;
        ListNode nextHead = reverseList(head.next);
        nextNode.next = head;
        head.next = null;
        return nextHead;
    }
}
