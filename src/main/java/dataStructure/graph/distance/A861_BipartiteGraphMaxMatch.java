package dataStructure.graph.distance;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定一个二分图，其中左半部包含n1个点（编号1~n1），右半部包含n2个点（编号1~n2），二分图共包含m条边。
 * 数据保证任意一条边的两个端点都不可能在同一部分中。
 * 请你求出二分图的最大匹配数。
 *
 * 二分图的匹配：给定一个二分图G，在G的一个子图M中，M的边集{E}中的任意两条边都不依附于同一个顶点，则称M是一个匹配。
 * 二分图的最大匹配：所有匹配中包含边数最多的一组匹配被称为二分图的最大匹配，其边数即为最大匹配数。
 *
 * 输入格式
 * 第一行包含三个整数 n1、 n2 和 m。
 * 接下来m行，每行包含两个整数u和v，表示左半部点集中的点u和右半部点集中的点v之间存在一条边。
 *
 * 输出格式
 * 输出一个整数，表示二分图的最大匹配数。
 *
 * 数据范围
 * 1≤n1,n2≤500,
 * 1≤u≤n1,
 * 1≤v≤n2,
 * 1≤m≤105
 * 输入样例：
 * 2 2 4
 * 1 1
 * 1 2
 * 2 1
 * 2 2
 * 输出样例：
 * 2
 */
public class A861_BipartiteGraphMaxMatch {
    static int N = 510, M = 100010;
    static int n1, n2, m;   // n1表示第一个集合中的点数，n2表示第二个集合中的点数
    static int idx = 0;
    static int[] e, next, head;

    static int[] matches;   // 存储第二个集合中的每个点当前匹配的第一个集合中的点是哪个
    static boolean[] st;    // 表示第二个集合中的每个点是否已经被遍历过

    public static void add(int a, int b) {
        e[idx] = b;
        next[idx] = head[a];
        head[a] = idx;
        idx++;
    }

    public static boolean find(int u ) {

        for(int i = head[u]; i != -1; i = next[i]) {
            int v = e[i];
            if(!st[v]) {
                st[v] = true;
                if(matches[v] == 0 || find(matches[v]) ) {
                    matches[v] = u;
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args ) {
        Scanner in = new Scanner(System.in);
        n1 = in.nextInt();
        n2 = in.nextInt();
        m = in.nextInt();

        e = new int[M];
        head = new int[n1 + n2 + 1];
        Arrays.fill(head, -1);
        next = new int[M];

        st = new boolean[n1 + n2 + 1];
        matches = new int[n1 + n2 + 1];

        for(int i = 0; i < m; i++) {
            int a = in.nextInt(), b = in.nextInt();
            add(a, b);
        }

        int res = 0;
        for(int i = 1; i <= n1; i++) { // 求最大匹配数，依次枚举第一个集合中的每个点能否匹配第二个集合中的点
            Arrays.fill(st, false);
            if(find(i)) res++;
        }
        System.out.print(res);
    }
}
