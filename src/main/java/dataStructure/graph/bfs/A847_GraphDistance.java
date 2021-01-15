package dataStructure.graph.bfs;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 给定一个n个点m条边的有向图，图中可能存在重边和自环。
 * 所有边的长度都是1，点的编号为1~n。
 *
 * 请你求出1号点到n号点的最短距离，如果从1号点无法走到n号点，输出-1。
 *
 * 输入格式
 * 第一行包含两个整数n和m。
 * 接下来m行，每行包含两个整数a和b，表示存在一条从a走到b的长度为1的边。
 *
 * 输出格式
 * 输出一个整数，表示1号点到n号点的最短距离。
 *
 * 数据范围
 * 1≤n,m≤105
 * 输入样例：
 * 4 5
 * 1 2
 * 2 3
 * 3 4
 * 1 3
 * 1 4
 * 输出样例：
 * 1
 *
 *
 * https://www.acwing.com/problem/content/849/
 */
public class A847_GraphDistance {
    static int INF = 0x3f3f3f3f;
    static int n, m;
    static int idx;
    static int[] e, head, next;

    public static void add(int u, int v) {
        e[idx] = v;
        next[idx] = head[u];
        head[u] = idx++;
    }

    public static int bfs() {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        Deque<Integer> queue = new LinkedList<>();
        queue.offer(1);
        dist[1] = 0;
        while(!queue.isEmpty()) {
            int u = queue.poll();
            for(int i = head[u]; i != -1; i = next[i]) {
                int v = e[i];
                // if(dist[v] > dist[u] + 1) {
                //     queue.offer(v);
                //     dist[v] = dist[u] + 1;
                // }
                if(dist[v] != INF) continue;
                queue.offer(v);
                dist[v] = dist[u] + 1;
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
        for(int i = 0; i < m; i++) {
            int u = in.nextInt(), v = in.nextInt();
            add(u, v);
        }
        System.out.print(bfs());
    }
}
