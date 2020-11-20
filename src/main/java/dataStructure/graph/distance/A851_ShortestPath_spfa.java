package dataStructure.graph.distance;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 给定一个n个点m条边的有向图，图中可能存在重边和自环， 边权可能为负数。
 * 请你求出1号点到n号点的最短距离，如果无法从1号点走到n号点，则输出impossible。
 * 数据保证不存在负权回路。
 *
 * 输入格式
 * 第一行包含整数n和m。
 * 接下来m行每行包含三个整数x，y，z，表示存在一条从点x到点y的有向边，边长为z。
 *
 * 输出格式
 * 输出一个整数，表示1号点到n号点的最短距离。
 * 如果路径不存在，则输出”impossible”。
 *
 * 数据范围
 * 1≤n,m≤105,
 * 图中涉及边长绝对值均不超过10000。
 *
 * 输入样例：
 * 3 3
 * 1 2 5
 * 2 3 -3
 * 1 3 4
 * 输出样例：
 * 2
 *
 * https://www.acwing.com/problem/content/853/
 */
public class A851_ShortestPath_spfa {
    static int N = 100010;
    static int INF = 0x3f3f3f3f;

    static int n, m;
    static int idx = 0;
    static int[] e, weight, head, next;

    public static void add(int a, int b, int w) {
        e[idx] = b;
        weight[idx] = w;
        next[idx] = head[a];
        head[a] = idx;
        idx++;
    }
    // TODO: 动态逼近法：
    //  时间复杂度 平均情况下 O(m)，最坏情况下 O(nm), n 表示点数，m 表示边数
    //  设立一个先进先出的队列用来保存待优化的结点，优化时每次取出队首结点u，
    //  并且用u点当前的最短路径估计值对离开u点所指向的结点v进行松弛操作，
    //  如果v点的最短路径估计值有所调整，且v点不在当前的队列中，就将v点放入队尾。
    //  这样不断从队列中取出结点来进行松弛操作，直至队列空为止。
    public static int spfa() {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        boolean[] st = new boolean[n + 1]; //判断节点是否在队列里

        Deque<Integer> queue = new LinkedList<>();
        dist[1] = 0;
        st[1] = true;
        queue.offer(1);

        while (!queue.isEmpty()) {
            int u = queue.poll();
            st[u] = false;
            for (int i = head[u]; i != -1; i = next[i]) {
                int v = e[i];
                if (dist[v] > dist[u] + weight[i]) {
                    dist[v] = dist[u] + weight[i];
                    if (!st[v]) {
                        st[v] = true;
                        queue.offer(v);
                    }
                }
            }
        }

        return dist[n] > INF / 2 ? -1 : dist[n];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();

        e = new int[N];
        head = new int[n + 1];
        Arrays.fill(head, -1);
        next = new int[N];
        weight = new int[N];

        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int w = in.nextInt();
            add(a, b, w);
        }
        int ans = spfa();
        System.out.print(ans == -1 ? "impossible" : ans);
    }
}
