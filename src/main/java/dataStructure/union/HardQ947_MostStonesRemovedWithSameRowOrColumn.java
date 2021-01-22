package dataStructure.union;

import java.util.HashSet;
import java.util.Set;

/**
 * n 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。
 * 如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。
 * 给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置，
 * 返回 可以移除的石子 的最大数量。
 *
 * 示例 1：
 * 输入：stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * 输出：5
 * 解释：一种移除 5 块石头的方法如下所示：
 * 1. 移除石头 [2,2] ，因为它和 [2,1] 同行。
 * 2. 移除石头 [2,1] ，因为它和 [0,1] 同列。
 * 3. 移除石头 [1,2] ，因为它和 [1,0] 同行。
 * 4. 移除石头 [1,0] ，因为它和 [0,0] 同列。
 * 5. 移除石头 [0,1] ，因为它和 [0,0] 同行。
 * 石头 [0,0] 不能移除，因为它没有与另一块石头同行/列。
 *
 * 示例 2：
 * 输入：stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * 输出：3
 * 解释：一种移除 3 块石头的方法如下所示：
 * 1. 移除石头 [2,2] ，因为它和 [2,0] 同行。
 * 2. 移除石头 [2,0] ，因为它和 [0,0] 同列。
 * 3. 移除石头 [0,2] ，因为它和 [0,0] 同行。
 * 石头 [0,0] 和 [1,1] 不能移除，因为它们没有与另一块石头同行/列。
 * 示例 3：
 *
 * 输入：stones = [[0,0]]
 * 输出：0
 * 解释：[0,0] 是平面上唯一一块石头，所以不可以移除它。
 * 
 *
 * 提示：
 * 1 <= stones.length <= 1000
 * 0 <= xi, yi <= 104
 * 不会有两块石头放在同一个坐标点上
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/most-stones-removed-with-same-row-or-column
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ947_MostStonesRemovedWithSameRowOrColumn {
    int[] parent;
    public int find(int a) {
        if(parent[a] != a) parent[a] = find(parent[a]);
        return parent[a];
    }

    public void union(int a, int b) {
        parent[find(a)] = find(b);
    }

    // TODO：按点合并
    public int removeStones1(int[][] stones) {
        int n = stones.length;
        parent = new int[n + 1];
        for(int i = 0; i < n; i++) parent[i] = i;
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if(stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    union(i, j);
                }
            }
        }

        int count = 0;
        for(int i = 0; i < n; i++) {
            if (parent[i] == i) count++;
        }
        return n - count;
    }

    // TODO: 按边合并, 重点关注
    //  并查集语义：所有横坐标为 x 的石头和所有纵坐标为 y 的石头都属于同一个连通分量。
    //  问题：石头的位置是「有序数对（二维）」，并查集的底层是「一维数组」，我们在并查集里应该如何区分横纵坐标呢？
    public int removeStones(int[][] stones) {
        parent = new int[20020];
        for(int i = 0; i < 20020; i++) parent[i] = i;
        for(int[] s : stones) {
            union(s[0], s[1] + 10001);
        }
        Set<Integer> parentSet = new HashSet<>();
        for(int[] s : stones) {
            parentSet.add(find(s[0]));
        }
        return stones.length - parentSet.size();
    }
}
