package dataStructure.graph;

import java.util.Arrays;

/**
 * 有 n 个城市通过 m 个航班连接。每个航班都从城市 u 开始，以价格 w 抵达 v。
 * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到从 src 到 dst 最多经过 k站中转的最便宜的价格。
 * 如果没有这样的路线，则输出 -1。
 *
 * 示例 1：
 * 输入:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 *
 *  输出: 200
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cheapest-flights-within-k-stops
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q787_CheapestFlightsWithinKStops {
    class Solution {
        int INF = 0x3f3f3f3f;

        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {

            int[] dist = new int[n];
            Arrays.fill(dist, INF);

            dist[src] = 0;

            for(int i = 0; i < K + 1; i++) {
                int[] backup = Arrays.copyOf(dist, dist.length);
                for(int[] e : flights) {
                    int a = e[0], b = e[1], w = e[2];
                    dist[b] = Math.min(dist[b], backup[a] + w);
                }
            }

            return dist[dst] == INF ? -1 : dist[dst];
        }
    }
}
