package algorithm.dp.test;

/**
 * 给定一个未排序的整数数组，找到最长递增子序列的个数。
 *
 * 示例 1:
 *
 * 输入: [1,3,5,4,7]
 * 输出: 2
 * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 * 示例 2:
 *
 * 输入: [2,2,2,2,2]
 * 输出: 5
 * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ673_NumberLongestIncreasingSubsequence {
    // TODO:
    //  1.集合划分：0 - i中所有的递增子序列
    //  2.属性：max
    //  3.状态计算：dp[i] = max(dp[i], dp[j] + 1), nums[i] > nums[j]
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        if(nums == null || n == 0) return 0;

        int maxLen = 0;
        int[] count = new int[n];   // (0, i)中最长子序列的个数
        int[] dp = new int[n];      // (0, i)中最长子序列的长度
        for(int i = 0; i < n; i++) {
            dp[i] = 1;
            count[i] = 1;
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    if(dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j]; // 如果可以更新的话不是变成1，而是变成count[j]
                    } else if(dp[i] == dp[j] + 1) {
                        count[i] += count[j];
                    }
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        int res = 0;
        for(int i = 0; i < n; i++) {
            if(dp[i] == maxLen) {
                res += count[i];
            }
        }

        return res;
    }
}
