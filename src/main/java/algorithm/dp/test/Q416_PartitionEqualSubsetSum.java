package algorithm.dp.test;

import java.util.Arrays;

/**
 * 给定一个只包含正整数的非空数组。
 * 是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 注意:
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 *
 * 示例 1:
 * 输入: [1, 5, 11, 5]
 * 输出: true
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 *
 *
 * 示例2:
 * 输入: [1, 2, 3, 5]
 * 输出: false
 * 解释: 数组不能分割成两个元素和相等的子集.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-equal-subset-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q416_PartitionEqualSubsetSum {
    // TODO: 01背包的做法，背包容量sum/2
    //  从前i个树中选择某几个数，使得背包尽可能多的放下足够多的数，最后判断背包中的重量是否为sum/2
    //  高阶版的看Q494
    public boolean canPartition1(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for(int x : nums) sum += x;
        if(n <= 1 || sum % 2 == 1) return false;
        int[][] dp = new int[n + 1][sum / 2 + 1];
        for(int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum / 2; j++) {
                dp[i][j] = dp[i - 1][j];
                if(nums[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i - 1]] + nums[i - 1]);
                }
            }
        }
        return dp[n][sum / 2] == sum / 2;
    }

    // TODO:
    //  集合划分：dp[i][j]表示从数组的 [0,i]下标范围内选取若干个正整数（可以是0个）,是否存在一种选取方案使得被选取的正整数的和等于j。
    public boolean canPartition2(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for(int x : nums) sum += x;
        if(n <= 1 || sum % 2 == 1) return false;
        boolean[][] dp = new boolean[n + 1][sum / 2 + 1];
        for(int i = 0; i <= n; i++) dp[i][0] = true; // 0-i中，不选数就可以使他们的和为0。
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= sum / 2; j++) {
                if(j < nums[i]) { // 不能选择当前的数
                    dp[i][j] = dp[i - 1][j];
                } else { // 可以选择当前的数，也可以不选当前的数
                    // 前i-1个数能不能恰好装满j，如果不能，检查前i-1个物品能不能恰好装满j - nums[i]
                    dp[i][j] = (dp[i - 1][j] | dp[i - 1][j - nums[i]]);
                }
            }
        }
        return dp[n][sum / 2];
    }

    // TODO: 空间优化
    public boolean canPartition3(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for(int x : nums) sum += x;
        if(n <= 1 || sum % 2 == 1) return false;
        boolean[] dp = new boolean[sum / 2 + 1];
        dp[0] = true;
        for(int i = 1; i <= n; i++) {
            for(int j = sum / 2; j >= nums[i - 1]; j--) {
                dp[j] = dp[j] | dp[j - nums[i - 1]];
            }
        }
        return dp[sum / 2];
    }
}
