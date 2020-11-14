package dataStructure.graph.bfs;

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
    static int N = 100010;
    static int n, m;
    static int[] e, head, next;
    static int idx = 0;
    static int[] inDegree;

    public static void add(int a, int b) {
        e[idx] = b;
        next[idx] = head[a];
        head[a] = idx;
        idx++;
    }

    public static Deque<Integer> topSort() {
        Deque<Integer> queue = new LinkedList<>();
        Deque<Integer> ans = new LinkedList<>();
        for(int u = 1; u <= n; u++) {
            if(inDegree[u] == 0) {
                queue.offer(u);
            }
        }

        while(!queue.isEmpty()) {
            int u = queue.poll();
            ans.offer(u);
            for(int v = head[u]; v != -1; v = next[v]) {
                inDegree[e[v]] --;
                if(inDegree[e[v]] == 0) queue.offer(e[v]);
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        m = input.nextInt();

        e = new int[N];
        next = new int[N];
        head = new int[n + 1];

        inDegree = new int[n + 1];

        Arrays.fill(head, -1);

        for (int i = 0; i < m; i++) {
            int a = input.nextInt(), b = input.nextInt();
            inDegree[b]++;
            add(a, b);
        }

        Deque<Integer> ans = topSort();
        if (ans.size() == n) {
            while (!ans.isEmpty()) System.out.print(ans.poll() + " ");
        } else {
            System.out.print("-1");
        }
    }
}
