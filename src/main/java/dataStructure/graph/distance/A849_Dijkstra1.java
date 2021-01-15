package dataStructure.graph.distance;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定一个n个点m条边的有向图，图中可能存在重边和自环，所有边权均为正值。
 * 请你求出1号点到n号点的最短距离，如果无法从1号点走到n号点，则输出-1。
 *
 * 输入格式
 * 第一行包含整数n和m。
 * 接下来m行每行包含三个整数x，y，z，表示存在一条从点x到点y的有向边，边长为z。
 *
 * 输出格式
 * 输出一个整数，表示1号点到n号点的最短距离。
 * 如果路径不存在，则输出-1。
 *
 * 数据范围
 * 1≤n≤500,
 * 1≤m≤105,
 * 图中涉及边长均不超过10000。
 *
 * 输入样例：
 * 3 3
 * 1 2 2
 * 2 3 1
 * 1 3 4
 * 输出样例：
 * 3
 *
 * https://www.acwing.com/problem/content/851/
 */
// TODO：稠密图
public class A849_Dijkstra1 {
    static int INF = 100010;
    static int n, m;
    static int[][] graph; // 稠密图
    static boolean[] st;
    static int[] dist;


    // TODO: 求1号点到n号点的最短路，如果不存在则返回-1
    //  时间复杂是 O(n^2+m), n 表示点数，m 表示边数
    public static int dijkstra() {
        dist = new int[n + 1];
        st = new boolean[n + 1]; // 存放确定了最小距离的点
        // 1.初始化距离
        Arrays.fill(dist, INF);

        dist[1] = 0;
        for(int i = 0; i < n; i++) {
            int t = -1;
            // 2.在还未确定最短路的点中，寻找从1号点到当前节点的距离最小的点
            for(int j = 1; j <= n; j++) {
                if(!st[j] && (t == -1 || dist[j] < dist[t])) {
                    t = j;
                }
            }
            // 3.确定了一个最短距离的点，加入到s中去
            st[t] = true;

            // 4.用t更新其他点的距离
            for(int j = 1; j <= n; j++) {
                dist[j] = Math.min(dist[j], dist[t] + graph[t][j]);
            }
        }

        return dist[n] == INF ? -1 : dist[n];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();

        graph = new int[n + 1][n + 1];

        for(int[] g : graph) Arrays.fill(g, INF);

        for(int i = 0; i < m; i++) {
            int u = in.nextInt(), v = in.nextInt(), value = in.nextInt();
            graph[u][v] = Math.min(graph[u][v], value);
        }
        int ans = dijkstra();
        System.out.println(ans);
    }





    static class Main {
        static int INF = 0x3f3f3f3f;
        static int n, m;
        static int[] e, head, next, weight;
        static int idx;

        public static void add(int u, int v, int w) {
            e[idx] = v;
            weight[idx] = w;
            next[idx] = head[u];
            head[u] = idx++;
        }

        public static int dijkstra() {
            boolean[] st = new boolean[n + 1];
            int[] dist = new int[n + 1];
            Arrays.fill(dist, INF);

            dist[1] = 0;
            for(int i = 0; i < n; i++) {
                int t = -1;
                for(int j = 1; j <= n; j++) {
                    if(!st[j] && (t == -1 || dist[t] > dist[j])) {
                        t = j;
                    }
                }
                st[t] = true;
                for(int j = head[t]; j != -1; j = next[j]) {
                    int v = e[j];
                    dist[v] = Math.min(dist[v], dist[t] + weight[j]);
                }
            }
            return dist[n] == INF ? -1 : dist[n];
        }

        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);

            n = in.nextInt(); m = in.nextInt();
            e = new int[m];
            next = new int[m];
            head = new int[n + 1];
            Arrays.fill(head, -1);
            weight = new int[m];

            for(int i = 0; i < m; i++) {
                int u = in.nextInt(), v = in.nextInt(), w = in.nextInt();
                add(u, v, w);
            }
            System.out.print(dijkstra());
        }
    }
}