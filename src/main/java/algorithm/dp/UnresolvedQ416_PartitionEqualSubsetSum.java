package algorithm.dp;

/**
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 注意:
 *
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * 示例 1:
 *
 * 输入: [1, 5, 11, 5]
 *
 * 输出: true
 *
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 *
 *
 * 示例2:
 *
 * 输入: [1, 2, 3, 5]
 *
 * 输出: false
 *
 * 解释: 数组不能分割成两个元素和相等的子集.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-equal-subset-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class UnresolvedQ416_PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        if(nums.length <= 1) return false;
        // 求和
        int sum = 0, max = 0;
        for(int a : nums) {
            sum += a;
            max = Math.max(a, max);
        }
        // sum不可划分
        if((sum & 1) == 1) {
            return false;
        }
        int target = sum / 2;

        if(max > target) { //最大的数比一半都大
            return false;
        }

        // 令dp[i][j] 为 0-i中是否可以选出若干个数 他们的和为j
        boolean[][] dp = new boolean[nums.length][target+1];
        dp[0][nums[0]] = true; // 只选第0个数时
        for(int i = 0; i < nums.length; i++) {
            dp[i][0] = true; // 0-i中，不选数就可以使他们的和为0
        }

        for(int i = 1; i < nums.length; i++) {
            for(int j = 1; j <= target; j++) {
                if(j < nums[i]) { // 不能选择当前的数
                    dp[i][j] = dp[i-1][j];
                } else { // 可以选择当前的数，也可以不选当前的数
                    dp[i][j] = (dp[i-1][j] | dp[i -1][ j - nums[i] ]);
                }
            }
        }

        return dp[nums.length-1][target];
    }

    // TODO: 怎么优化空间？？题解里有优化空间的做法
}
