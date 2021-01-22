package dataStructure.graph;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 给你一个points数组，表示 2D 平面上的一些点，其中points[i] = [xi, yi]。
 * 连接点[xi, yi] 和点[xj, yj]的费用为它们之间的 曼哈顿距离：|xi - xj| + |yi - yj|，其中|val|表示val的绝对值。
 * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有一条简单路径时，才认为所有点都已连接。
 *
 * 提示：
 * 1 <= points.length <= 1000
 * -106 <= xi, yi <= 106
 * 所有点 (xi, yi) 两两不同。
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-cost-to-connect-all-points
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 */
public class Q1584_MinCosttoConnectAllPoints {
    int INF = 0x3f3f3f3f;
    int[] parent;

    public int find(int a) {
        if(parent[a] != a) parent[a] = find(parent[a]);
        return parent[a];
    }

    public void union(int a, int b) {
        int pa = find(a), pb = find(b);
        if(pa == pb) return ;
        parent[pa] = pb;
    }

    public boolean isConnected(int a, int b) {
        return find(a) == find(b);
    }

    public int kruskal(int[][] points ) {
        int n = points.length;
        parent = new int[n];
        for(int i = 0; i < n; i++) parent[i] = i;

        int[][] edges = new int[n * (n - 1) / 2][3];
        int m = 0;
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                edges[m][0] = i;
                edges[m][1] = j;
                edges[m][2] = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                m++;
            }
        }
        Arrays.sort(edges, (o1, o2) -> o1[2] - o2[2]);

        int count = 0, res = 0;
        for(int i = 0; i < m && count < n - 1; i++) {
            if(!isConnected(edges[i][0], edges[i][1])) {
                union(edges[i][0], edges[i][1]);
                res += edges[i][2];
                count++;
            }
        }
        return res;
    }

    public int kruskal1(int[][] points ) {
        int n = points.length;
        parent = new int[n];
        for(int i = 0; i < n; i++) parent[i] = i;

        // TODO: kruskal必须从边的角度出发解决问题
        PriorityQueue<int[]> edges = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                edges.offer(new int[]{i, j, Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1])});
            }
        }

        int res = 0, count = 0;
        while(!edges.isEmpty() && count < n - 1) {
            int[] e = edges.poll();
            if(!isConnected(e[0], e[1])) {
                union(e[0], e[1]);
                res += e[2];
                count++;
            }
        }
        return count == n - 1 ? res : INF;
    }

    public int prim(int[][] points) {
        int n = points.length;
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        boolean[] st = new boolean[n];
        int res = 0;
        for(int i = 0; i < n; i++) {
            int t = -1;
            for(int j = 0; j < n; j++) {
                if(!st[j] && (t == -1 || dist[t] > dist[j])) {
                    t = j;
                }
            }
            st[t] = true;
            if(i > 0) {
                if(dist[t] == INF) return INF;
                res += dist[t];
            }
            for(int j = 0; j < n; j++) {
                if(!st[j]) {
                    int d = Math.abs(points[t][0] - points[j][0]) + Math.abs(points[t][1] - points[j][1]);
                    dist[j] = Math.min(dist[j], d);
                }
            }
        }
        return res;
    }
    public int minCostConnectPoints(int[][] points) {
        // return prim(points);
        return kruskal(points);
    }
}
