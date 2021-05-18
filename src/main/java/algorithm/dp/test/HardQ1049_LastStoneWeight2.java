package algorithm.dp.test;

/**
 * 有一堆石头，每块石头的重量都是正整数。
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。
 * 假设石头的重量分别为x 和y，且x <= y。
 *
 * 那么粉碎的可能结果如下：
 * 如果x == y，那么两块石头都会被完全粉碎；
 * 如果x != y，那么重量为x的石头将会完全粉碎，而重量为y的石头新重量为y-x。
 * 最后，最多只会剩下一块石头。返回此石头最小的可能重量。如果没有石头剩下，就返回 0。
 *
 * 示例：
 * 输入：[2,7,4,1,8,1]
 * 输出：1
 * 解释：
 * 组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
 * 组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
 * 组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
 * 组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
 * 
 *
 * 提示：
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/last-stone-weight-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ1049_LastStoneWeight2 {
    public int lastStoneWeightII1(int[] stones) {
        int n = stones.length;
        int sum = 0;
        for(int x : stones) sum += x;
        int[][] dp = new int[n + 1][sum / 2 + 1]; // TODO: 状态表示 f(i,j)表示当遇到第i个物品时，背包容量为j时，能获得的最大价值

        // TODO：转化为01背包
        //  有一堆石头，分成两堆，如何分才能使两堆石头之间的重量差距最小
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= sum / 2; j++) {
                if(stones[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stones[i - 1]] + stones[i - 1]);
                }
            }
        }
        return sum - 2 * dp[n][sum / 2];
    }

    // TODO: 空间优化
    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;

        int sum = 0;
        for(int x : stones) sum += x;

        int[] dp = new int[sum / 2 + 1];
        for(int i = 1; i <= n; i++) {
            for(int j = sum / 2; j >= stones[i - 1]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i - 1]] + stones[i - 1]);
            }
        }
        return sum - 2 * dp[sum / 2];
    }
}
