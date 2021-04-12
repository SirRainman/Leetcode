package algorithm.sort;

import dataStructure.ListNode;

import java.util.List;

/**
 * @program: Leetcode
 * @description:
 * 插入排序算法：
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 * 
 * 示例 1：
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 *
 * 示例2：
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insertion-sort-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-03-28 17:21
 **/
public class Q147_InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE, head);
        ListNode pre = dummy, cur = dummy.next;
        while(cur != null) {
            if(cur.val >= pre.val) {
                pre = cur;
                cur = cur.next;
            } else {
                pre.next = cur.next;
                cur.next = null;
                insertNode(dummy, cur);
                cur = pre.next;
            }
        }
        return dummy.next;
    }

    public void insertNode(ListNode dummy, ListNode node) {
        ListNode pre = dummy, cur = dummy.next;
        while(node.val > cur.val) {
            pre = cur;
            cur = cur.next;
        }
        node.next = pre.next;
        pre.next = node;
    }
}
