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
public class HardQ1584_MinCosttoConnectAllPoints {
    class Solution {
        int INF = 0x3f3f3f3f;

        public int prim(int[][] points) {
            int n = points.length;
            int[] dist = new int[n];
            Arrays.fill(dist, INF);
            boolean[] st = new boolean[n];

            int ans = 0;
            for(int i = 0; i < n; i++) {
                int t = -1;
                for(int j = 0; j < n; j++) {
                    if(!st[j] && (t == -1 || dist[t] > dist[j])) {
                        t = j;
                    }
                }
                st[t] = true;
                if(dist[t] != INF) ans += dist[t];
                for(int j = 0; j < n; j++) {
                    if(!st[j]) {
                        int x1 = points[t][0], y1 = points[t][1];
                        int x2 = points[j][0], y2 = points[j][1];
                        dist[j] = Math.min(dist[j], Math.abs(x1 - x2) + Math.abs(y2 - y1));
                    }
                }
            }

            return ans;
        }

        int[] parent;

        public int find(int x) {
            if(x != parent[x]) parent[x] = find(parent[x]);
            return parent[x];
        }

        public int kruskal(int[][] points) {
            int n = points.length;
            int ans = 0;
            parent = new int[n];
            for(int i = 0; i < n; i++) parent[i] = i;

            // TODO:注意这个堆优化版的kruskal算法
            PriorityQueue<int[]> heap = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(i != j) {
                        heap.offer(new int[] {i, j, Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1] ) });
                    }
                }
            }

            int count = 0;
            while(count < n - 1) {
                int[] edge = heap.poll();
                int a = edge[0], b = edge[1], w = edge[2];
                int pa = find(a);
                int pb = find(b);
                if(pa != pb) {
                    parent[pa] = pb;
                    ans += w;
                    count++;
                }
            }
            return ans;
        }

        public int minCostConnectPoints(int[][] points) {
            // return prim(points);
            return kruskal(points);
        }
    }
}
