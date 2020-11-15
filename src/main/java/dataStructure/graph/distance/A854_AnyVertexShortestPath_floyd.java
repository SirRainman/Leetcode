package dataStructure.graph.distance;

import java.util.Scanner;

/**
 * 给定一个n个点m条边的有向图，图中可能存在重边和自环，边权可能为负数。
 * 再给定k个询问，每个询问包含两个整数x和y，表示查询从点x到点y的最短距离，如果路径不存在，则输出“impossible”。
 * 数据保证图中不存在负权回路。
 *
 * 输入格式
 * 第一行包含三个整数n，m，k
 * 接下来m行，每行包含三个整数x，y，z，表示存在一条从点x到点y的有向边，边长为z。
 * 接下来k行，每行包含两个整数x，y，表示询问点x到点y的最短距离。
 *
 * 输出格式
 * 共k行，每行输出一个整数，表示询问的结果，若询问两点间不存在路径，则输出“impossible”。
 *
 * 数据范围
 * 1≤n≤200,
 * 1≤k≤n2
 * 1≤m≤20000,
 * 图中涉及边长绝对值均不超过10000。
 *
 * 输入样例：
 * 3 3 2
 * 1 2 1
 * 2 3 2
 * 1 3 1
 * 2 1
 * 1 3
 * 输出样例：
 * impossible
 * 1
 *
 * https://www.acwing.com/problem/content/description/856/
 */
public class A854_AnyVertexShortestPath_floyd {
    static int INF = 0x3f3f3f3f;
    static int n, m, q;
    static int[][] graph;

    public static void floyd() {
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        q = in.nextInt();

        graph = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= n; j++)
                graph[i][j] = i == j ? 0 : INF;

        for(int i = 0; i < m; i++) {
            int a = in.nextInt(), b = in.nextInt(), w = in.nextInt();
            graph[a][b] = Math.min(graph[a][b], w);
        }

        floyd();

        while(q-- > 0) {
            int a = in.nextInt(), b = in.nextInt();
            System.out.println(graph[a][b] > INF / 2 ? "impossible" : graph[a][b]);
        }
    }
}
