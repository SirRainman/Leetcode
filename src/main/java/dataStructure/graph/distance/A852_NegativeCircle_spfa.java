package dataStructure.graph.distance;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * 给定一个n个点m条边的有向图，图中可能存在重边和自环， 边权可能为负数。
 * 请你判断图中是否存在负权回路。
 *
 * 输入格式
 * 第一行包含整数n和m。
 * 接下来m行每行包含三个整数x，y，z，表示存在一条从点x到点y的有向边，边长为z。
 *
 * 输出格式
 * 如果图中存在负权回路，则输出“Yes”，否则输出“No”。
 *
 * 数据范围
 * 1≤n≤2000,
 * 1≤m≤10000,
 * 图中涉及边长绝对值均不超过10000。
 *
 * 输入样例：
 * 3 3
 * 1 2 -1
 * 2 3 4
 * 3 1 -4
 * 输出样例：
 * Yes
 *
 * https://www.acwing.com/problem/content/854/
 */
public class A852_NegativeCircle_spfa {
    static int N = 100010;
    static int INF = 0x3f3f3f3f;

    static int n, m;
    static int idx = 0;
    static int[] e, weight, head, next;

    public static void add (int a, int b, int w) {
        e[idx] = b;
        weight[idx] = w;
        next[idx] = head[a];
        head[a] = idx;
        idx++;
    }

    // TODO：SPFA（Shortest Path Faster Algorithm）（队列优化）算法是求单源最短路径的一种算法。
    //  1.SPFA是在Bellman-ford算法的基础上加上一个队列优化，减少了冗余的松弛操作，是一种高效的最短路算法。
    //      起点s到某一个点的最短路径的第一条边，必定是s与s的邻接点连成的边。所以当我们在第一次松弛（即第一次迭代求解时）时，松弛的边必定包含最短路径的第一条边。
    //      而最短路径的第二条边，必定是s的邻接点与s的邻接点的邻接点连成的边。这样以此类推。
    //  2.Bellman-Ford算法虽然可以处理负环，但是时间复杂度为O(ne)，e为图的边数，在图为稠密图的时候，是不可接受的。
    //      Bellman-Ford算法的缺点在于，当某一个迭代求解已经获得了所有的最短路径后，它还是会继续执行没有执行完的迭代求解。但其实不用这样。

    // TODO：求负环的常用方法，基于SPFA，一般都用方法 2（该题也是用方法 2）：
    //  方法 1：统计每个点入队的次数，如果某个点入队n次，则说明存在负环
    //  方法 2：统计当前每个点的最短路中所包含的边数，如果某点的最短路所包含的边数大于等于n，则也说明存在环
    public static boolean spfa() {
        int[] dist = new int[n  + 1];
        Arrays.fill(dist, INF);
        int[] count = new int[n  + 1]; // 记录从源点到该点的 最短路的边数
        boolean[] st = new boolean[n + 1];

        Deque<Integer> queue = new LinkedList<>();
        for(int i = 1; i <= n; i++) {
            queue.offer(i);
            st[i] = true;
        }

        while(!queue.isEmpty()) {
            int u = queue.poll();
            st[u] = false;
            for(int i = head[u]; i != -1; i = next[i]) {
                int v = e[i];
                if(dist[v] > dist[u] + weight[i]) {
                    dist[v] = dist[u] + weight[i];
                    count[v] = count[u] + 1;
                    if(count[v] >= n) return true;
                    if(!st[v] ) {
                        st[v] = true;
                        queue.offer(v);
                    }
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();

        e = new int[N];
        weight = new int[N];
        next = new int[N];
        head = new int[n + 1];
        Arrays.fill(head, -1);

        for(int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int w = in.nextInt();
            add(a, b, w);
        }

        System.out.print(spfa() ? "Yes" : "No");
    }
}
