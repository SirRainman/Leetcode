package dataStructure.graph.topsort;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *给定一个n个点m条边的有向图，点的编号是1到n，图中可能存在重边和自环。
 * 请输出任意一个该有向图的拓扑序列，如果拓扑序列不存在，则输出-1。
 * 若一个由图中所有点构成的序列A满足：对于图中的每条边(x, y)，x在A中都出现在y之前，则称A是该图的一个拓扑序列。
 *
 * 输入格式
 * 第一行包含两个整数n和m
 * 接下来m行，每行包含两个整数x和y，表示存在一条从点x到点y的有向边(x, y)。
 *
 * 输出格式
 * 共一行，如果存在拓扑序列，则输出任意一个合法的拓扑序列即可。
 *
 * 否则输出-1。
 *
 * 数据范围
 * 1≤n,m≤105
 * 输入样例：
 * 3 3
 * 1 2
 * 2 3
 * 1 3
 * 输出样例：
 * 1 2 3
 *
 * https://www.acwing.com/problem/content/850/
 */
public class A848_TopSort {
    static int n, m;
    static int[] e, head, next;
    static int idx;
    static int[] indegree;

    public static void add(int u, int v) {
        e[idx] = v;
        next[idx] = head[u];
        head[u] = idx++;
    }

    public static Deque<Integer> topSort() {
        Deque<Integer> res = new LinkedList<>();
        Deque<Integer> queue = new LinkedList<>();

        boolean[] st = new boolean[n + 1];

        for(int i = 1; i <= n; i++) {
            if(indegree[i] == 0) {
                queue.offer(i);
                st[i] = true;
            }
        }

        while(!queue.isEmpty()) {
            int u = queue.poll();
            res.offer(u);
            for(int i = head[u]; i != -1; i = next[i]) {
                if(st[e[i]]) continue;
                indegree[e[i]]--;
                if(indegree[e[i]] == 0) {
                    st[e[i]] = true;
                    queue.offer(e[i]);
                }
            }
        }

        return res;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        e = new int[m];
        next = new int[m];
        head = new int[n + 1];
        Arrays.fill(head, -1);
        indegree = new int[n + 1];

        for(int i = 0; i < m; i++) {
            int u = in.nextInt(), v = in.nextInt();
            add(u, v);
            indegree[v]++;
        }

        Deque<Integer> res = topSort();
        if(res.size() != n) {
            System.out.print(-1);
        } else {
            while(!res.isEmpty()) System.out.print(res.poll() + " ");
        }
    }
}
