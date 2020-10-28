package dataStructure.list;

import dataStructure.ListNode;

/**
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 *
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 *
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q234_PalindromeLinkedList {
    public ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while(cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;
        ListNode l1 = head;
        ListNode mid = findMiddle(head);
        ListNode l2 = mid.next;
        mid.next = null;
        l2 = reverseList(l2);
        while(l1 != null && l2 != null) {
            if(l1.val != l2.val) return false;
            l1 = l1.next;
            l2 = l2.next;
        }
        return true;
    }

    public boolean isPalindrome2(ListNode head) {
        if(head == null || head.next == null) return true;

        ListNode pre = null, slow = head, fast = head, slowNext = null;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;

            slowNext = slow.next;
            slow.next = pre;
            pre = slow;
            slow = slowNext;
        }
        //System.out.println(pre.val + " " + slow.val);
        if(fast != null) {
            slow = slow.next;
        }

        while(pre != null && slow != null) {
            if(pre.val != slow.val) return false;
            pre = pre.next;
            slow = slow.next;
        }

        return true;
    }
}
