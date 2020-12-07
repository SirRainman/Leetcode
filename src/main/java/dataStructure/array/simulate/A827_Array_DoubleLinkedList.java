package dataStructure.array.simulate;

import java.util.Scanner;

/**
 * 假定有一个无限长的数轴，数轴上每个坐标上的数都是0。
 * 现在，我们首先进行 n 次操作，每次操作将某一位置x上的数加c。
 * 接下来，进行 m 次询问，每个询问包含两个整数l和r，你需要求出在区间[l, r]之间的所有数的和。
 *
 * 输入格式
 * 第一行包含两个整数n和m。
 * 接下来 n 行，每行包含两个整数x和c。
 * 再接下里 m 行，每行包含两个整数l和r。
 *
 * 输出格式
 * 共m行，每行输出一个询问中所求的区间内数字和。
 *
 * 数据范围
 * −109≤x≤109,
 * 1≤n,m≤105,
 * −109≤l≤r≤109,
 * −10000≤c≤10000
 * 输入样例：
 * 3 3
 * 1 2
 * 3 6
 * 7 5
 * 1 3
 * 4 6
 * 7 8
 * 输出样例：
 * 8
 * 0
 * 5
 *
 *
 * https://www.acwing.com/problem/content/804/
 */

class ArrayDoubleLinkedList {
    int[] e;        //存储当前节点的值
    int[] left;    //存储左边节点的下标 = 指针
    int[] right;
    int idx;        // 存储当前节点的下标
    int head;       // 存储头节点的下标
    int tail;       // 存储尾节点的下标

    public ArrayDoubleLinkedList(int size) {
        e = new int[size];
        left = new int[size];
        right = new int[size];
        idx = 1;
        head = 0;
        tail = size - 1;
        left[head] = -1;
        left[tail] = head;
        right[head] = tail;
        right[tail] = -1;
    }

    // 在下标是k的节点的右边，插入x
    public void insert(int k, int x) {
        e[idx] = x;
        left[idx] = k;
        right[idx] = right[k];
        right[k] = idx;
        left[right[idx]] = idx;
        idx++;
    }

    // 删除下标是k的节点
    public void remove(int k) {
        right[left[k]] = right[k];
        left[right[k]] = left[k];
    }

    public void printList() {
        int cur = right[head];
        while (cur != tail) {
            System.out.print(e[cur] + " ");
            cur = right[cur];
        }
        System.out.println();
    }
}

public class A827_Array_DoubleLinkedList {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int times = in.nextInt();
        ArrayDoubleLinkedList list = new ArrayDoubleLinkedList(100010);
        while (times-- > 0) {
            String s = in.next();
            if (s.compareTo("L") == 0) {
                int x = in.nextInt();
                list.insert(0, x);
            } else if (s.compareTo("R") == 0) {
                int x = in.nextInt();
                list.insert(list.left[list.tail], x);
            } else if (s.compareTo("D") == 0) {
                int k = in.nextInt();
                list.remove(k);
            } else if (s.compareTo("IL") == 0) {
                int k = in.nextInt(), x = in.nextInt();
                list.insert(list.left[k], x);
            } else if (s.compareTo("IR") == 0) {
                int k = in.nextInt(), x = in.nextInt();
                list.insert(k, x);
            }
        }
        list.printList();
    }
}

