package dataStructure.tree.trie;

import java.util.Scanner;

/**
 * 在给定的N个整数A1，A2……AN中选出两个进行xor（异或）运算，得到的结果最大是多少？
 *
 * 输入格式
 * 第一行输入一个整数N。
 * 第二行输入N个整数A1～AN。
 *
 * 输出格式
 * 输出一个整数表示答案。
 *
 * 数据范围
 * 1≤N≤105,
 * 0≤Ai<231
 * 输入样例：
 * 3
 * 1 2 3
 * 输出样例：
 * 3
 *
 * https://www.acwing.com/problem/content/description/145/
 */
public class HardA143_MaxXOR {
    static int idx = 0, N = 100010;
    static int[][] trie = new int[N * 31][2];

    public static void insert(int x) {
        int parent = 0;
        for(int i = 30; i >= 0; i--) { // 从x的最高位开始存
            int bit = x >> i & 1;
            if(trie[parent][bit] == 0) trie[parent][bit] = ++idx;
            parent = trie[parent][bit];
        }
    }

    // TODO:想一想什么时候会有最大的异或值？？？？
    public static int query(int x) {
        int ans = 0;
        int parent = 0;
        for(int i = 30; i >= 0; i--) {
            int bit = x >> i & 1;
            if(trie[parent][bit ^ 1] != 0) { // 说明他的相反的方向有节点，每一次检索的时候,我们都走与当前这一位相反的位置走,也就是让Xor值最大
                parent = trie[parent][bit ^ 1];
                ans = ans * 2 + 1;
            } else { // 如果说没有路可以走的话,那么就走相同的路.
                parent = trie[parent][bit];
                ans = ans * 2;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int len = in.nextInt(), ans = 0;
        for(int i = 0; i < len; i++) {
            int x = in.nextInt();
            insert(x);
            ans = Math.max(ans, query(x));
        }
        System.out.print(ans);
    }
}
