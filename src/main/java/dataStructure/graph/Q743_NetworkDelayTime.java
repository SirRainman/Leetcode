package dataStructure.graph;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 有N个网络节点，标记为1到N。
 * 给定一个列表times，表示信号经过有向边的传递时间。times[i] = (u, v, w)，
 * 其中u是源节点，v是目标节点， w是一个信号从源节点传递到目标节点的时间。
 * 现在，我们从某个节点K发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回-1。
 *
 * 示例：
 * 输入：times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
 * 输出：2
 * 
 *
 * 注意:
 * N的范围在[1, 100]之间。
 * K的范围在[1, N]之间。
 * times的长度在[1, 6000]之间。
 * 所有的边times[i] = (u, v, w)都有1 <= u, v <= N且0 <= w <= 100。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/network-delay-time
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q743_NetworkDelayTime {
    class Solution {
        int INF = 0x3f3f3f3f;
        int idx = 0;
        int[] e, weight, head, next;

        public void add(int a, int b, int w) {
            e[idx] = b;
            weight[idx] = w;
            next[idx] = head[a];
            head[a] = idx++;
        }

        public int spfa(int N, int K) {
            int[] dist = new int[N + 1];
            Arrays.fill(dist, INF);

            Deque<Integer> queue = new LinkedList<>();
            boolean[] st = new boolean[N + 1];

            dist[K] = 0;
            st[K] = true;
            queue.offer(K);


            while (!queue.isEmpty()) {
                int u = queue.poll();
                st[u] = false;
                for (int i = head[u]; i != -1; i = next[i]) {
                    int v = e[i];
                    if (dist[v] > dist[u] + weight[i]) {
                        dist[v] = dist[u] + weight[i];
                        if (!st[v]) {
                            st[v] = true;
                            queue.offer(v);
                        }
                    }
                }
            }

            int res = 0;
            for(int i = 1; i <= N; i++) {
                if(dist[i] == INF) return -1;
                res = Math.max(res, dist[i]);
            }
            return res;
        }

        public int networkDelayTime1(int[][] times, int N, int K) {
            e = new int[times.length];
            next = new int[times.length];
            weight = new int[times.length];
            head = new int[N + 1];
            Arrays.fill(head, -1);

            for(int[] e : times) {
                int a = e[0], b = e[1], w = e[2];
                add(a, b, w);
            }

            return spfa(N, K);
        }




        int[][] graph;
        public int dijkstra(int N, int K) {
            int[] dist = new int[N + 1];
            Arrays.fill(dist, INF);
            dist[K] = 0;

            boolean[] st = new boolean[N + 1];
            for(int i = 0; i < N; i++) {
                int t = -1;
                for(int j = 1; j <= N; j++) {
                    if(!st[j] && (t == -1 || dist[j] < dist[t])) {
                        t = j;
                    }
                }

                st[t] = true;

                for(int j = 1; j <= N; j++) {
                    dist[j] = Math.min(dist[j], dist[t] + graph[t][j]);
                }
            }

            int res = 0;
            for(int i = 1; i <= N; i++) {
                if(dist[i] == INF) return -1;
                res = Math.max(res, dist[i]);
            }
            return res;
        }

        public int networkDelayTime(int[][] times, int N, int K) {
            graph = new int[N + 1][N + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    graph[i][j] = i == j ? 0 : INF;
                }
            }

            for (int[] e : times) {
                int a = e[0], b = e[1], w = e[2];
                graph[a][b] = w;
            }

            return dijkstra(N, K);
        }
    }
}
