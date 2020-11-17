package dataStructure.graph;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 给你一个 m x n 的网格图grid。grid中每个格子都有一个数字，对应着从该格子出发下一步走的方向。grid[i][j]中的数字可能为以下几种情况：
 *
 * 1，下一步往右走，也就是你会从 grid[i][j]走到 grid[i][j + 1]
 * 2，下一步往左走，也就是你会从 grid[i][j]走到 grid[i][j - 1]
 * 3，下一步往下走，也就是你会从 grid[i][j]走到 grid[i + 1][j]
 * 4，下一步往上走，也就是你会从 grid[i][j]走到 grid[i - 1][j]
 * 注意网格图中可能会有无效数字，因为它们可能指向grid以外的区域。
 *
 * 一开始，你会从最左上角的格子(0,0)出发。我们定义一条有效路径为从格子(0,0)出发，每一步都顺着数字对应方向走，最终在最右下角的格子(m - 1, n - 1)结束的路径。有效路径不需要是最短路径。
 * 你可以花费cost = 1的代价修改一个格子中的数字，但每个格子中的数字只能修改一次。
 * 请你返回让网格图至少有一条有效路径的最小代价。
 *
 *
 *提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ1368_MinimumValidPathCostInGrid {
    class Solution {
        int INF = 0x3f3f3f3f;

        public int dijkstra(int[][] grid) {
            int n = grid.length, m = grid[0].length;
            int[] dist = new int[m * n + 1];
            Arrays.fill(dist, INF);

            int[][] directions = new int[][] {{0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            boolean[] st = new boolean[m * n + 1];

            PriorityQueue<int[] > heap = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
            heap.offer(new int[] { 0, 0});
            dist[0] = 0;

            while(!heap.isEmpty()) {
                int[] t = heap.poll();
                int cur = t[0];
                int x = cur / m, y = cur % m;
                if(!st[cur]) {
                    st[cur] = true;
                    for(int i = 1; i <= 4; i++) {
                        int u = x + directions[i][0], v = y + directions[i][1];
                        if(u < 0 || u >= n || v < 0 || v >= m) continue;
                        int next = u * m + v;

                        int cost = grid[x][y] == i ? 0 : 1;
                        if(dist[next] > dist[cur] + cost) {
                            dist[next] = dist[cur] + cost;
                            heap.offer(new int[]{next, dist[next]});
                        }
                    }
                }
            }

            return dist[m * n - 1];
        }

        public int spfa(int[][] grid) {
            int n = grid.length, m = grid[0].length;
            int[] dist = new int[m * n + 1];
            Arrays.fill(dist, INF);

            int[][] directions = new int[][] {{0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            Deque<Integer> queue = new LinkedList<>();
            boolean[] st = new boolean[m * n + 1];

            queue.offer(0);
            dist[0] = 0;
            st[0] = true;

            while(!queue.isEmpty()) {
                int cur = queue.poll();
                st[cur] = false;
                int x = cur / m, y = cur % m;

                for(int i = 1; i <= 4; i++) {
                    int u = x + directions[i][0], v = y + directions[i][1];
                    int next = u * m + v;
                    if(u < 0 || u >= n || v < 0 || v >= m) continue;

                    int cost = grid[x][y] == i ? 0 : 1;
                    if(dist[next] > dist[cur] + cost) {
                        dist[next] = dist[cur] + cost;
                        if(!st[next]) {
                            st[next] = true;
                            queue.offer(next);
                        }
                    }
                }
            }

            return dist[m * n - 1];
        }

        public int minCost(int[][] grid) {
            return dijkstra(grid);
        }
    }
}
