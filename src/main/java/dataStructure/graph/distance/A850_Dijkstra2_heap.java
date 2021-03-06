package dataStructure.graph.distance;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 给定一个n个点m条边的有向图，图中可能存在重边和自环，所有边权均为非负值。
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
 * 1≤n,m≤1.5×105,
 * 图中涉及边长均不小于0，且不超过10000。
 *
 * 输入样例：
 * 3 3
 * 1 2 2
 * 2 3 1
 * 1 3 4
 * 输出样例：
 * 3
 *
 *
 * https://www.acwing.com/problem/content/852/
 */
public class A850_Dijkstra2_heap {

    // TODO:想一想为什么要把最大值设置成这样？？？？
    //  dist[v] > dist[vertex] + weight[i]
    //  当dist[vertex] + weight[i]很大则溢出，会变成负数，返回值本应是false，但结果为true
    static int INF = 0x3f3f3f3f; //
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

    public static int dijkstra() {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        // TODO:用堆进行维护最小值
        //  想一想堆优化之后各个步骤的时间复杂度是多少？
        // PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparing(x -> x[0]));
        PriorityQueue<int[]> heap = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        heap.offer(new int[] {1, dist[1]});

        boolean[] st = new boolean[n + 1];

        while(!heap.isEmpty()) {
            int[] t = heap.poll();
            // TODO:为什么从堆中取出来的点就是单源最短路径？
            //  因为目前离 v1顶点最近的是 v3顶点，并且这个图所有的边都是正数，那么肯定不可能通过第三个顶点中转，使得 v1顶点到 v3顶点的路程进一步缩短了。
            //  因为 v1顶点到其它顶点的路程肯定没有 v1到 v3顶点短.
            int u = t[0];
            if(!st[u]) {
                st[u] = true;
                for(int i = head[u]; i != -1; i = next[i]) {
                    int v = e[i];
                    if(dist[v] > dist[u] + weight[i]) {
                        dist[v] = dist[u] + weight[i];
                        heap.offer(new int[]{v, dist[v]});
                    }
                }
            }
        }

        return dist[n] == INF ? -1 : dist[n];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();

        head = new int[n + 1];
        Arrays.fill(head, -1);
        e = new int[m];
        weight = new int[m];
        next = new int[m];

        for(int i = 0; i < m; i++) {
            int a = in.nextInt(), b = in.nextInt(), value = in.nextInt();
            add(a, b, value);
        }

        int ans = dijkstra();
        System.out.print(ans);
    }
}
