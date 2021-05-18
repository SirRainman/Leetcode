package algorithm.dp.test;

/**
 * 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组nums中。
 * 现在要求你戳破所有的气球。如果你戳破气球 i ，就可以获得nums[left] * nums[i] * nums[right]个硬币。
 * 这里的left和right代表和i相邻的两个气球的序号。
 * 注意当你戳破了气球 i 后，气球left和气球right就变成了相邻的气球。
 * 求所能获得硬币的最大数量。
 *
 * 说明:
 * 你可以假设nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 *
 * 示例:
 * 输入: [3,1,5,8]
 * 输出: 167 
 * 解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *     coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/burst-balloons
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 */
public class HardQ312_BurstBalloons {
    // TODO：判读能不能用动态规划解决问题，就是必须存在最优子结构
    //  集合划分：dp[i][j] 戳破i - j之间的气球的所有方案所获得的硬币数量
    //  属性：max
    //  状态计算：
    //      dp[i][j] = max(dp[i][j], dp[i][k - 1] + dp[k + 1][j] + cost),
    //      设 k 是区间内最后一个被戳破的气球
    //      cost = nums[i - 1] * nums[k] * nums[j + 1] 注意cost的计算，当把i - j的气球都戳爆时，相邻的气球是 [i - 1] 和 [j + 1]
    public int maxCoins1(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 2][n + 2]; // dp[i][j]戳破 [i, j] 之间的气球后，可以获得的最多的硬币数
        int[] coins = new int[n + 2];
        coins[0] = coins[n + 1] = 1;
        for(int i = 1; i <= n; i++) coins[i] = nums[i - 1];
        for(int len = 1; len <= n; len++) {
            for(int i = 1; i + len - 1 <= n; i++) {
                int j = i + len - 1;
                for(int k = i; k <= j; k++) { // 枚举 [i, j] 之间可以被戳破的气球，k是区间内最后一个被戳破的气球，计算可以获得的最多的硬币的数量
                    // 因为k是区间内最后一个被戳破的气球，所以要先戳破[i, k - 1] [k + 1, j]这两个区间的气球后，再戳破气球k
                    // 这时，气球k的两边的气球是i - 1 和 j + 1
                    dp[i][j] = Math.max(dp[i][j], dp[i][k - 1] + dp[k + 1][j] + coins[i - 1] * coins[k] * coins[j + 1]);
                }
            }
        }
        return dp[1][n];
    }
}
