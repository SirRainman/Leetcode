package algorithm.double_pointer;

import dataStructure.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个链表，判断链表中是否有环。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 *
 * 进阶：
 * 你能用 O(1)（即，常量）内存解决此问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q141_LinkedListCycle {

    // TODO: 如何证明速度差一倍的时候，快指针和慢指针一定会相遇？？
    //  证明 存在时间t，整数k，使得 t * V快 - t * V慢 = k * Circle长
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;
        ListNode slow = head, fast = head.next;
        while(fast == null || fast.next == null) {
            if(fast == slow) return true;
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
}
