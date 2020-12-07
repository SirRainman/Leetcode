package dataStructure.array.simulate;

import java.util.Scanner;

/**
 * 实现一个单链表，链表初始为空，支持三种操作：
 * (1) 向链表头插入一个数；
 * (2) 删除第k个插入的数后面的数；
 * (3) 在第k个插入的数后插入一个数
 *
 * 现在要对该链表进行M次操作，进行完所有操作后，从头到尾输出整个链表。
 * 注意:题目中第k个插入的数并不是指当前链表的第k个数。
 * 例如操作过程中一共插入了n个数，则按照插入的时间顺序，这n个数依次为：第1个插入的数，第2个插入的数，…第n个插入的数。
 *
 * 输入格式
 * 第一行包含整数M，表示操作次数。
 * 接下来M行，每行包含一个操作命令，操作命令可能为以下几种：
 * (1) “H x”，表示向链表头插入一个数x。
 * (2) “D k”，表示删除第k个输入的数后面的数（当k为0时，表示删除头结点）。
 * (3) “I k x”，表示在第k个输入的数后面插入一个数x（此操作中k均大于0）。
 *
 * 输出格式
 * 共一行，将整个链表从头到尾输出。
 *
 * 数据范围
 * 1≤M≤100000
 * 所有操作保证合法。
 *
 * 输入样例：
 * 10
 * H 9
 * I 1 1
 * D 1
 * D 0
 * H 6
 * I 3 6
 * I 4 5
 * I 4 5
 * I 3 4
 * D 6
 * 输出样例：
 * 6 4 6 5
 *
 * https://www.acwing.com/problem/content/828/
 */

class ArrayLinkedList {
    int head;   // head 头节点的下标
    int idx;    // 当前节点的下标
    int[] e;    // 节点i的值
    int[] ne;   // 节点i的下一个节点的下标 = next

    public ArrayLinkedList(int size) {
        head = -1;
        idx = 1;
        e = new int[size];
        ne = new int[size];
    }

    // 将x插入到头节点
    public void addToHead(int x) {
        ne[idx] = head;
        head = idx;
        e[idx] = x;
        idx++;
    }

    //将x插入到下标是k的点的后面
    public void insert(int k, int x) {
        ne[idx] = ne[k];
        ne[k] = idx;
        e[idx] = x;
        idx++;
    }

    // 将下标是k的点的后面的点删除掉
    public void remove(int k) {
        if (k == 0) head = ne[head];
        ne[k] = ne[ne[k]];
    }

    public void printList() {
        int cur = head;
        while (cur != -1) {
            System.out.print(e[cur] + " ");
            cur = ne[cur];
        }
        System.out.println();
    }
}

public class A826_Array_LinkedList {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        ArrayLinkedList list = new ArrayLinkedList(100010);
        int times = in.nextInt();
        while (times-- > 0) {
            char c = in.next().charAt(0);
            if (c == 'H') {
                int x = in.nextInt();
                list.addToHead(x);
            } else if (c == 'D') {
                int k = in.nextInt();
                list.remove(k);
            } else {
                int k = in.nextInt(), x = in.nextInt();
                list.insert(k, x);
            }
        }

        list.printList();
    }
}
