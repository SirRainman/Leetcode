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
public class Hard142_LinkedListCycle2 {

    // TODO:
    //  1.想一想为什么快慢指针会相遇
    //  2.想一想为什么相遇后一个从表头走 一个从相遇点走 这两个人会相遇？
    public ListNode detectCycle(ListNode head) {
        if(head == null) return null;
        ListNode slow = head, fast = slow;
        while(fast != null) {
            slow = slow.next;
            if(fast.next != null){
                fast = fast.next.next;
            } else {
                return null;
            }

            if(fast == slow) {
                ListNode cur = head;
                while(cur != slow) {
                    cur = cur.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }
}
