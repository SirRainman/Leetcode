package dataStructure.tree.heap;

import java.util.Scanner;

/**
 * 维护一个集合，初始时集合为空，支持如下几种操作：
 *
 * “I x”，插入一个数x；
 * “PM”，输出当前集合中的最小值；
 * “DM”，删除当前集合中的最小值（数据保证此时的最小值唯一）；
 * “D k”，删除第k个插入的数；
 * “C k x”，修改第k个插入的数，将其变为x；
 * 现在要进行N次操作，对于所有第2个操作，输出当前集合的最小值。
 *
 * 输入格式
 * 第一行包含整数N。
 *
 * 接下来N行，每行包含一个操作指令，操作指令为”I x”，”PM”，”DM”，”D k”或”C k x”中的一种。
 *
 * 输出格式
 * 对于每个输出指令“PM”，输出一个结果，表示当前集合中的最小值。
 *
 * 每个结果占一行。
 *
 * 数据范围
 * 1≤N≤105
 * −109≤x≤109
 * 数据保证合法。
 *
 * 输入样例：
 * 8
 * I -10
 * PM
 * I -10
 * D 1
 * C 2 8
 * I 6
 * PM
 * DM
 * 输出样例：
 * -10
 * 6
 *
 * https://www.acwing.com/problem/content/841/
 */
public class HardA839_SimulateHeap {
    static int N = 100010;
    // h[N]存储堆中的值, h[1]是堆顶，x的左儿子是2x, 右儿子是2x + 1
    // ph[k]存储第k个插入的点在堆中的位置 position heap
    // hp[k]存储堆中下标是k的点是第几个插入的heap position
    static int[] h = new int[N], ph = new int[N], hp = new int[N];
    static int size = 0, idx = 0;

    static void swap(int a, int b) {
        int temp = h[a];
        h[a] = h[b];
        h[b] = temp;
        temp = ph[hp[a]];
        ph[hp[a]] = ph[hp[b]];
        ph[hp[b]] = temp;
        temp = hp[a];
        hp[a] = hp[b];
        hp[b] = temp;
    }

    static void up(int u) {
        while (u / 2 > 0 && h[u / 2] > h[u]) {
            swap(u, u / 2);
            u /= 2;
        }
    }

    static void down(int u) {
        int t = u;
        if (u * 2 <= size && h[u * 2] < h[t]) t = u * 2;
        if (u * 2 + 1 <= size && h[u * 2 + 1] < h[t]) t = u * 2 + 1;
        if (u != t) {
            swap(t, u);
            down(t);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        while (n-- > 0) {
            String op = in.next();
            if (op.compareTo("I") == 0) {
                int x = in.nextInt();
                h[++size] = x;
                ph[++idx] = size;
                hp[size] = idx;
                up(size);
            } else if (op.compareTo("PM") == 0) {
                System.out.println(h[1]);
            } else if (op.compareTo("DM") == 0) {
                swap(1, size);
                size--;
                down(1);
            } else if (op.compareTo("D") == 0) {
                int k = in.nextInt();
                k = ph[k];
                swap(k, size--);
                down(k);
                up(k);
            } else if (op.compareTo("C") == 0) {
                int k = in.nextInt(), x = in.nextInt();
                k = ph[k];
                h[k] = x;
                down(k);
                up(k);
            }
        }
    }

}
