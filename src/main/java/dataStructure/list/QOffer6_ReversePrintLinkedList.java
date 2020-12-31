package dataStructure.list;

import dataStructure.ListNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 *
 * 示例 1：
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 *
 * 限制：
 * 0 <= 链表长度 <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class QOffer6_ReversePrintLinkedList {
    // TODO: 反转链表
    //  需要改变链表结构
    public int[] reversePrint1(ListNode head) {
        int n = 0;
        ListNode pre = null;
        while(head != null) {
            n++;
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        head = pre;

        int[] nums = new int[n];
        int i = 0;
        ListNode cur = head;
        while(cur != null) {
            nums[i++] = cur.val;
            cur = cur.next;
        }
        return nums;
    }

    // TODO：栈
    public int[] reversePrint(ListNode head) {
        Deque<Integer> stack = new LinkedList<>();
        ListNode cur = head;
        while(cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }

        int[] nums = new int[stack.size()];
        for(int i = 0; !stack.isEmpty(); i++) {
            nums[i] = stack.pop();
        }
        return nums;
    }

    // TODO: 递归法 能想到栈就一定能想到递归
    public int[] reversePrint3(ListNode head) {
        recur(head);
        return nums;
    }

    int size = 0;
    int index = 0;
    int[] nums;
    public void recur(ListNode cur) {
        if(cur == null) { nums = new int[size]; return; }
        size++;
        recur(cur.next);
        nums[index++] = cur.val;
    }

}
