package dataStructure.list;

import dataStructure.ListNode;

/**
 * 编写一个程序，找到两个单链表相交的起始节点。
 *
 * 注意：
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ160_IntersectionOfTwoLinkedLists {
    // TODO:
    //  追及问题：单位时间内，两个人速度相同，走过的路程一定相同
    //      如果有公共节点，则会在第一个公共节点相遇
    //      如果没有公共节点，同时走过 A + B 后都为null
    //  当 node1 到达链表 headA 的末尾时，重新定位到链表 headB 的头结点；
    //  当 node2 到达链表 headB 的末尾时，重新定位到链表 headA 的头结点。
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headB == null || headA == null) return null;
        ListNode p = headA, q = headB;
        while(p != q) {
            p = p == null ? headB : p.next;
            q = q == null ? headA : q.next;
        }
        return p;
    }

    // TODO：想一想能不能利用栈的先进后出的特点解决问题？？？
}
