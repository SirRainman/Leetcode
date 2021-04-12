package dataStructure.list;

import dataStructure.ListNode;

/**
 * @program: Leetcode
 * @description:
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 *
 * 示例 1：
 * 输入：head = [1,4,3,2,5,2], x = 3
 * 输出：[1,2,2,4,3,5]
 *
 * 示例 2：
 * 输入：head = [2,1], x = 2
 * 输出：[1,2]
 *
 * 提示：
 * 链表中节点的数目在范围 [0, 200] 内
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-03-24 14:32
 **/
public class HardQ86_PartitionList {
    public ListNode partition(ListNode head, int x) {
        ListNode dummy = new ListNode(-1, head);
        ListNode p = dummy;
        while(p.next != null && p.next.val < x) p = p.next;
        ListNode small = p;
        while(p != null && p.next != null) {
            if(p.next.val < x) {
                ListNode next = p.next;
                p.next = next.next;
                next.next = small.next;
                small.next = next;
                small = small.next;
            } else {
                p = p.next;
            }
        }
        return dummy.next;
    }
}
