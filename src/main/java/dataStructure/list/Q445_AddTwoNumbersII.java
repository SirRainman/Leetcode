package dataStructure.list;

import dataStructure.ListNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 *
 *  
 *
 * 进阶：
 *
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 *
 *  
 *
 * 示例：
 *
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 8 -> 0 -> 7
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q445_AddTwoNumbersII {
    // TODO: 这种做法不可以算大数
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        int a = 0, b = 0;
        ListNode cur = l1;
        while(cur != null) {
            a = a * 10 + cur.val;
            cur = cur.next;
        }
        cur = l2;
        while(cur != null) {
            b = b * 10 + cur.val;
            cur = cur.next;
        }
        a += b;
        ListNode dummy = new ListNode(-1);
        if(a == 0) return new ListNode(0);
        while(a > 0) {
            ListNode node = new ListNode(a % 10);
            node.next = dummy.next;
            dummy.next = node;
            a = (int) (a / 10);
        }
        return dummy.next;
    }



    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode la = l1, lb = l2;
        Deque<Integer> sa = new LinkedList<>();
        Deque<Integer> sb = new LinkedList<>();

        while(la != null || lb != null) {
            if(la != null) {
                sa.push(la.val);
                la = la.next;
            }
            if(lb != null) {
                sb.push(lb.val);
                lb = lb.next;
            }
        }

        ListNode dummy = new ListNode(-1);
        int carry = 0;
        while(!sa.isEmpty() || !sb.isEmpty() || carry > 0) {
            int a = 0, b = 0;
            if(!sa.isEmpty()) a = sa.pop();
            if(!sb.isEmpty()) b = sb.pop();
            int sum = a + b + carry;
            carry = sum >= 10 ? 1 : 0;
            ListNode node = new ListNode(sum % 10);
            node.next = dummy.next;
            dummy.next = node;
        }

        return dummy.next;
    }
}
