package algorithm.dp.test;

/**
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 *
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4 
 * 解释: 最长的上升子序列是[2,3,7,101]，它的长度是 4。
 * 说明:
 *
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到O(n log n) 吗?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q300_LongestIncreasingSubsequence {
    // TODO：
    //  1.集合划分：集合所有以i结尾的上升子序列的长度
    //  2.属性：max
    //  3.状态计算：dp[i] = max(dp[i], dp[j] + 1), 其中0≤j<i且num[j]<num[i]
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (nums == null || n == 0) return 0;
        int[] dp = new int[n]; // dp[i] 为考虑前 i 个元素，以第 i 个数字结尾的最长上升子序列的长度
        int res = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
    //TODO:有没有更快的做法？？？ 贪心 + 二分
}
