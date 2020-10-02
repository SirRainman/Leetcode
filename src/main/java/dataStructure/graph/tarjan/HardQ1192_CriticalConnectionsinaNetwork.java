package dataStructure.graph.tarjan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 力扣数据中心有n台服务器，分别按从0到n-1的方式进行了编号。
 *
 * 它们之间以「服务器到服务器」点对点的形式相互连接组成了一个内部集群，其中连接connections 是无向的。
 *
 * 从形式上讲，connections[i] = [a, b]表示服务器 a和 b之间形成连接。任何服务器都可以直接或者间接地通过网络到达任何其他服务器。
 *
 * 「关键连接」是在该集群中的重要连接，也就是说，假如我们将它移除，便会导致某些服务器无法访问其他服务器。
 *
 * 请你以任意顺序返回该集群内的所有 「关键连接」。
 *
 *  示例 1：
 * 输入：n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
 * 输出：[[1,3]]
 * 解释：[[3,1]] 也是正确的。
 *
 *
 * 提示：
 *
 * 1 <= n <= 10^5
 * n-1 <= connections.length <= 10^5
 * connections[i][0] != connections[i][1]
 * 不存在重复的连接
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/critical-connections-in-a-network
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ1192_CriticalConnectionsinaNetwork {
    int time;   // 记录最早访问的时间
    int[] dfn;  // 记录访问该节点的时间
    int[] low;  // 记录该节点可以回访到的最早时间
    List<Integer>[] adj; //记录该节点的邻居
    List<List<Integer>> ans; //记录关键连接

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        dfn = new int[n];
        low = new int[n];
        adj = new ArrayList[n];
        ans = new ArrayList<>();
        time = 1;
        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        // 记录该节点可以访问到的邻居的节点
        for(List<Integer> connection : connections) {
            int v1 = connection.get(0), v2 = connection.get(1);
            adj[v1].add(v2);
            adj[v2].add(v1);
        }

        tarjan(0, -1);

        return ans;
    }

    public void tarjan(int cur, int pre) {
        dfn[cur] = low[cur] = time++;
        // 遍历该节点的所有的邻居
        for(int next : adj[cur]) {
            if(next == pre) continue; // 如果访问的是父节点，则跳过
            if(dfn[next] == 0) { //如果没有访问过该邻居，则访问
                tarjan(next, cur);
                low[cur] = Math.min(low[cur], low[next]); // 取该节点和起邻居节点，能够回访到的最早时间
                // TODO: 想一想为什么这是关键路径(low[next] > dfn[cur])？ 如果求的是割点（low[next] >= dfn[cur]）应该怎么办？
                //  割边：next可以回访到的最早节点，比cur节点还要晚，所以cur -> next是割边。
                //  割点：next可以访问到的最早节点最多就是cur，所以cur是割点。
                if(low[next] > dfn[cur]) { // cur -> next 这条边是桥
                    ans.add(Arrays.asList(cur, next));
                }
            } else { // 如果访问过该邻居
                low[cur] = Math.min(low[cur], dfn[next]);
            }
        }
    }
}
