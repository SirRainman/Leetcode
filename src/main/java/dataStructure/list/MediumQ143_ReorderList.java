package dataStructure.list;

import dataStructure.ListNode;

import java.util.List;

/**
 * 给定一个单链表L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例1:
 *
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 示例 2:
 *
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reorder-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MediumQ143_ReorderList {
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode mid = findMiddleInList(head);
        if(mid == null) return;
        ListNode l1 = head;
        ListNode l2 = mid.next;
        mid.next = null;
        l2 = reverseList(l2);
        head = mergeList(l1, l2);
    }

    // TODO: 利用 length = fast = 2 * slow
    //  注意返回的中间值是哪一个中间值(中间值的上一个），当为奇数时？当为偶数时？！！
    public ListNode findMiddleInList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverseList(ListNode head) {
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

    public ListNode mergeList(ListNode l1, ListNode l2) {
        ListNode head = l1;
        ListNode l1_next, l2_next;
        while(l1 != null && l2 != null) {
            l1_next = l1.next;
            l2_next = l2.next;

            l1.next = l2;
            l1 = l1_next;

            l2.next = l1;
            l2 = l2_next;
        }
        return head;
    }
}
