package algorithm.double_pointer;

import dataStructure.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个链表，返回链表开始入环的第一个节点。
 * 如果链表无环，则返回null。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。
 * 注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
 * 说明：不允许修改给定的链表。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q142_LinkedListCycle2 {

    // TODO:
    //  1.想一想为什么相遇后，从相遇点走相同的步数后就相遇了？
    //      公式推导：设入环点为a，相遇点为b，环中剩余距离为c
    //          则相遇时：
    //              2 * (a + b) = a + n * (b + c) + b
    //              a = (n - 1) * (b + c) + c
    //              当n=1时， a=c，为什么n=1？？？
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) break;
        }
        if(fast == null || fast.next == null) return null;
        ListNode pos = head;
        while(pos != slow) {
            pos = pos.next;
            slow = slow.next;
        }
        return pos;
    }
}
