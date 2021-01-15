package dataStructure.graph.distance;

import java.util.*;

/**
 * 给定一个n个点m条边的无向图，图中可能存在重边和自环。
 * 请你判断这个图是否是二分图。
 *
 * 输入格式
 * 第一行包含两个整数n和m。
 * 接下来m行，每行包含两个整数u和v，表示点u和点v之间存在一条边。
 *
 * 输出格式
 * 如果给定图是二分图，则输出“Yes”，否则输出“No”。
 *
 * 数据范围
 * 1≤n,m≤105
 * 输入样例：
 * 4 4
 * 1 3
 * 1 4
 * 2 3
 * 2 4
 * 输出样例：
 * Yes
 *
 * https://www.acwing.com/problem/content/description/862/
 */
public class A860_BipartiteGraph {
    static int N = 200010;
    static int n, m;
    static int idx = 0;
    static int[] e, head, next;

    static int[] color; // 表示每个点的颜色，-1表示为染色，0表示白色，1表示黑色

    public static void add(int a, int b) {
        e[idx] = b;
        next[idx] = head[a];
        head[a] = idx;
        idx++;
    }

    public static boolean dfs(int u, int c) {
        color[u] = c;
        for(int i = head[u]; i != -1; i = next[i]) {
            int v = e[i];
            if(color[v] == -1 && !dfs(v, 1 - c)) {
                return false;
            } else if(color[v] == c) {
                return false;
            }
        }
        return true;
    }

    public static boolean check() {
        for(int i = 1; i <= n; i++) {
            if(color[i] == -1 && !dfs(i, 0)) {
                return false;
            }
        }
        return true;
    }

    public static boolean bfs_check () {
        Deque<Integer> queue = new LinkedList<>();
        for(int i = 1; i <= n; i++) {
            if(color[i] == -1) {
                color[i] = 0;
                queue.offer(i);
                while(!queue.isEmpty()) {
                    int u = queue.poll();
                    for(int j = head[u]; j != -1; j = next[j]) {
                        int v = e[j];
                        if(color[v] == -1) {
                            color[v] = 1 - color[u];
                            queue.offer(v);
                        } else if(color[v] == color[u]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();

        e = new int[N];
        next = new int[N];
        head = new int[n + 1];
        Arrays.fill(head, -1);

        color = new int[n + 1];
        Arrays.fill(color, -1);

        for(int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            add(a, b);
            add(b, a);
        }

        System.out.print(check() ? "Yes" : "No");
    }
}
