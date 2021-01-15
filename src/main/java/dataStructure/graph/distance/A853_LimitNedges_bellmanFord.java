package dataStructure.graph.distance;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定一个n个点m条边的有向图，图中可能存在重边和自环， 边权可能为负数。
 * 请你求出从1号点到n号点的最多经过k条边的最短距离，如果无法从1号点走到n号点，输出impossible。
 * 注意：图中可能 存在负权回路 。
 *
 * 输入格式
 * 第一行包含三个整数n，m，k。
 * 接下来m行，每行包含三个整数x，y，z，表示存在一条从点x到点y的有向边，边长为z。
 *
 * 输出格式
 * 输出一个整数，表示从1号点到n号点的最多经过k条边的最短距离。
 * 如果不存在满足条件的路径，则输出“impossible”。
 *
 * 数据范围
 * 1≤n,k≤500,
 * 1≤m≤10000,
 * 任意边长的绝对值不超过10000。
 *
 * 输入样例：
 * 3 3 1
 * 1 2 1
 * 2 3 1
 * 1 3 3
 * 输出样例：
 * 3
 *
 * https://www.acwing.com/problem/content/855/
 */
public class A853_LimitNedges_bellmanFord {
    static int INF = 0x3f3f3f3f;
    static int n, m, k;
    static int[][] edges;

    // TODO：时间复杂度为 O(V*E)，其中 V 为顶点数量，E 为边的数量
    public static int bellmanFord() {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;
        for(int i = 0; i < k; i++) {// 走k条边
            // back[]数组是上一次迭代后dist[]数组的备份，
            // 由于是每个点同时向外出发，因此需要对dist[]数组进行备份，若不进行备份会因此发生串联效应，影响到下一个点。
            int[] backup = Arrays.copyOf(dist, dist.length);
            for(int[] e : edges) {
                int a = e[0], b = e[1], w = e[2];
                // TODO:下面这句话没有看懂
                //  连续进行松弛，在每次松弛时把每条边都更新一下，如果第n次迭代仍然会松弛三角不等式，
                //  就说明存在一条长度是n+1的最短路径，由抽屉原理，路径中至少存在两个相同的点，说明图中存在负权回路。
                //  图解：https://juejin.im/post/6844903661395509262
                dist[b] = Math.min(dist[b], backup[a] + w);
            }
        }

        // TODO：想一想为什么非法判断是  dist[n] > INF / 2   而不是 dist[n] == INF   ？？？
        //  就是考虑到了负权边的情况，INF + 负数 还是一个非常大的数，还是不可到达
        return dist[n] > INF / 2 ? -1 : dist[n];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        k = in.nextInt();

        edges = new int[m][3];

        for(int i = 0; i < m; i++) {
            edges[i][0] = in.nextInt();
            edges[i][1] = in.nextInt();
            edges[i][2] = in.nextInt();
        }

        int ans = bellmanFord();
        System.out.print(ans == -1 ? "impossible" : ans);
    }
}
