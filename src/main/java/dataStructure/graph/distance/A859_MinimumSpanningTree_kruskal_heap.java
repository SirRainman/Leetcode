package dataStructure.graph.distance;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 给定一个n个点m条边的无向图，图中可能存在重边和自环，边权可能为负数。
 * 求最小生成树的树边权重之和，如果最小生成树不存在则输出impossible。
 * 给定一张边带权的无向图G=(V, E)，其中V表示图中点的集合，E表示图中边的集合，n=|V|，m=|E|。
 * 由V中的全部n个顶点和E中n-1条边构成的无向连通子图被称为G的一棵生成树，其中边的权值之和最小的生成树被称为无向图G的最小生成树。
 *
 * 输入格式
 * 第一行包含两个整数n和m。
 * 接下来m行，每行包含三个整数u，v，w，表示点u和点v之间存在一条权值为w的边。
 *
 * 输出格式
 * 共一行，若存在最小生成树，则输出一个整数，表示最小生成树的树边权重之和，如果最小生成树不存在则输出impossible。
 *
 * 数据范围
 * 1≤n≤105,
 * 1≤m≤2∗105,
 * 图中涉及边的边权的绝对值均不超过1000。
 *
 * 输入样例：
 * 4 5
 * 1 2 1
 * 1 3 2
 * 1 4 3
 * 2 3 2
 * 3 4 4
 * 输出样例：
 * 6
 *
 * https://www.acwing.com/problem/content/861/
 */
public class A859_MinimumSpanningTree_kruskal_heap {
    static int INF = 0x3f3f3f3f;
    static int n, m;
    static int[] parent;

    public static int find(int x) {
        if(parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();

        parent = new int[n + 1];
        for(int i = 0; i <= n; i++) parent[i] = i;

        // TODO:注意堆优化之后的写法
        PriorityQueue<int[]> heap = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        for(int i = 0; i < m; i++) {
            int a = in.nextInt(), b = in.nextInt(), w = in.nextInt();
            heap.offer(new int[] {a, b, w});
        }

        int res = 0, count = 0;
        while(count < n - 1) {
            if(heap.isEmpty()) break;
            int[] edge = heap.poll();
            int a = edge[0], b = edge[1], w = edge[2];
            int pa = find(a), pb = find(b);
            if(pa != pb) {
                parent[pa] = pb;
                count++;
                res += w;
            }
        }
        System.out.print(count < n - 1 ? "impossible" : res);
    }
}