package dataStructure.list;

import dataStructure.ListNode;

/**
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 * 返回删除后的链表的头节点。
 * 注意：此题对比原题有改动
 *
 * 示例 1:
 * 输入: head = [4,5,1,9], val = 5
 * 输出: [4,1,9]
 * 解释: 给定你链表中值为5的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 * 
 * 示例 2:
 * 输入: head = [4,5,1,9], val = 1
 * 输出: [4,5,9]
 * 解释: 给定你链表中值为1的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
 *
 * 说明：
 * 题目保证链表中节点的值互不相同
 * 若使用 C 或 C++ 语言，你不需要 free 或 delete 被删除的节点
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class QOffer18_DeleteNode {
    public ListNode deleteNode1(ListNode head, int val) {
        // TODO: 想一想为什么要设置头节点？
        //  操作方便:如果删除的是第一个节点怎么办？
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy, cur = head;
        while(cur != null) {
            if(cur.val == val) pre.next = cur.next;
            pre = cur;
            cur = cur.next;
        }
        return dummy.next;
    }

    // TODO: 递归解法
    //  注意递归解法删除的是第一个val节点
    public ListNode deleteNode(ListNode head, int val) {
        if(head == null) return null;
        if(head.val == val) return head.next;
        head.next = deleteNode(head.next, val);
        return head;
    }
}
