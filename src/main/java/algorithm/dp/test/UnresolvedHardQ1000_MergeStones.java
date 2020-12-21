package algorithm.dp.test;

/**
 * 有 N 堆石头排成一排，第 i 堆中有stones[i]块石头。
 *
 * 每次移动（move）需要将连续的K堆石头合并为一堆，而这个移动的成本为这K堆石头的总数。
 *
 * 找出把所有石头合并成一堆的最低成本。如果不可能，返回 -1 。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：stones = [3,2,4,1], K = 2
 * 输出：20
 * 解释：
 * 从 [3, 2, 4, 1] 开始。
 * 合并 [3, 2]，成本为 5，剩下 [5, 4, 1]。
 * 合并 [4, 1]，成本为 5，剩下 [5, 5]。
 * 合并 [5, 5]，成本为 10，剩下 [10]。
 * 总成本 20，这是可能的最小值。
 * 示例 2：
 *
 * 输入：stones = [3,2,4,1], K = 3
 * 输出：-1
 * 解释：任何合并操作后，都会剩下 2 堆，我们无法再进行合并。所以这项任务是不可能完成的。.
 * 示例 3：
 *
 * 输入：stones = [3,5,1,2,6], K = 3
 * 输出：25
 * 解释：
 * 从 [3, 5, 1, 2, 6] 开始。
 * 合并 [5, 1, 2]，成本为 8，剩下 [3, 8, 6]。
 * 合并 [3, 8, 6]，成本为 17，剩下 [17]。
 * 总成本 25，这是可能的最小值。
 * 
 *
 * 提示：
 *
 * 1 <= stones.length <= 30
 * 2 <= K <= 30
 * 1 <= stones[i] <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-cost-to-merge-stones
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 */
public class UnresolvedHardQ1000_MergeStones {
    // TODO:
    //  这道题非常难，建议看题解
    //  https://leetcode-cn.com/problems/minimum-cost-to-merge-stones/solution/yi-dong-you-yi-dao-nan-yi-bu-bu-shuo-ming-si-lu-he/
    //  集合划分：dp[i][j] 将第i堆至第j堆石子合并到一起堆方案
    //  属性：min
    //  状态计算：dp[i][j] = min(dp[i][j], dp[i][k] + dp[k + 1][j])
    public int mergeStones(int[] stones, int K) {
        int n = stones.length;
        if((n - 1) % (K - 1) != 0) return -1;
        for(int i = 1; i < n; i++) stones[i] += stones[i - 1];

        int[][] dp = new int[n][n];

        for(int len = K; len <= n; len++) {
            for(int i = 0; i + len - 1 < n; i++) {
                int left = i, right = i + len - 1;
                int cost = left == 0 ? stones[right] : stones[right] - stones[left - 1]; // TODO：注意stones是从1开始的
                dp[left][right] = Integer.MAX_VALUE;
                for(int p = left; p < right; p += K - 1) { // TODO: 注意p的取值，步长是K-1
                    dp[left][right] = Math.min(dp[left][right], dp[left][p] + dp[p + 1][right]);
                }
                if((right - left) % (K - 1) == 0) dp[left][right] += cost; // TODO：为什么只有这样才加上cost？？？
            }
        }

        return dp[0][n - 1];
    }
}
