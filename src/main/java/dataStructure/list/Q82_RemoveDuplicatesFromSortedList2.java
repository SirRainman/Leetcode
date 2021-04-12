package dataStructure.list;

import dataStructure.ListNode;

/**
 * @program: Leetcode
 * @description:
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中没有重复出现的数字。
 *
 * 示例1:
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 *
 * 示例2:
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-03-11 22:33
 **/
public class Q82_RemoveDuplicatesFromSortedList2 {
    public ListNode deleteDuplicates1(ListNode head) {
        if(head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        boolean flag = false;
        ListNode pre = dummy, cur = pre.next;
        while(cur.next != null) {
            ListNode next = cur.next;
            if(cur.val != next.val) {
                if(flag) {
                    pre.next = next;
                    flag = false;
                } else {
                    pre = cur;
                }
            } else {
                flag = true;
            }
            cur = next;
        }
        if(flag) pre.next = cur.next;
        return dummy.next;
    }

    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return null;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        boolean flag = false;
        ListNode pre = dummy, cur = pre.next;
        while(cur != null) {
            ListNode diffNode = cur.next;
            int count = 0;
            while(diffNode != null && cur.val == diffNode.val) {
                diffNode = diffNode.next;
                count++;
            }
            if(count > 0) pre.next = diffNode;
            else pre = cur;
            cur = diffNode;
        }
        return dummy.next;
    }
}
