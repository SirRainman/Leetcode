package algorithm.double_pointer;

import dataStructure.ListNode;

/**
 * 给定一个链表，删除链表的倒数第n个节点，并且返回链表的头结点。
 *
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 *
 * 说明：
 * 给定的 n保证是有效的。
 *
 * 进阶：
 * 你能尝试使用一趟扫描实现吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ19_RemoveNthNodeFromEndofList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // TODO：为什么要设头节点啊！！！！
        //  1.因为要找的是待删除结点的前一个节点，而当删除头节点时，头结点没有前一个节点，所以设置了一个虚拟节点
        //  2.防止出现只有一个结点的情况
        //  3.哨兵逻辑，边界问题好处理
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;
        // Advances fast pointer so that the gap between fast and slow is n nodes apart
        for (int i = 1; i <= n + 1; i++) {
            fast = fast.next;
        }
        // Move fast to the end, maintaining the gap
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
