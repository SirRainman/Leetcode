package algorithm.dp.test;

/**
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4 
 * 解释: 最长的上升子序列是[2,3,7,101]，它的长度是 4。
 *
 * 说明:
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到O(n log n) 吗?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ300_LongestIncreasingSubsequence {
    // TODO：
    //  1.集合划分：集合所有以i结尾的上升子序列的长度
    //  2.属性：max
    //  3.状态计算：dp[i] = max(dp[i], dp[j] + 1), 其中0≤j<i且num[j]<num[i]
    public int lengthOfLIS1(int[] nums) {
        int n = nums.length, maxLen = 0;
        int[] dp = new int[n];
        for(int i = 0; i < n; i++) {
            dp[i] = 1;
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }

    // TODO: 有没有更快的做法？？？
    //  贪心 + 二分：如果要使上升子序列尽可能的长，则需要让序列上升得尽可能慢，因此希望每次在上升子序列最后加上的那个数尽可能的小。
    //      1 数组 d[i]表示长度为 i 的最长上升子序列的末尾元素的最小值，
    //          即在数组 1,2,3,4,5,6中长度为3的上升子序列可以为 1,2,3也可以为 2,3,4等等, 但是d[3]=3，即子序列末尾元素最小为3。
    //      2 证明数组d具有单调性
    //          即，使用反证法证明对于任意的下标 0 <= i < j < len ，都有 d[i] < d[j]。，
    //          假设存在 k < j时，存在 d[k] >= d[j]，
    //          则此时, 对d[k]而言，存在上升子序列 [a0, a1, ..., ak]
    //                 对d[j]而言，存在上升子序列 [b0, b1, ..., bk, ..., bj]
    //          由于ak >= bj,
    //          而在[b0, b1, ..., bk, ..., bj]中是严格递增的，则 bk < bj
    //          则 ak >= bj > bk
    //          即 bk < ak，所以ak 不是长度为k的最小的结尾元素，与题设相矛盾，因此可以证明其单调性
    public int lengthOfLIS(int[] nums) {
        int n = nums.length, len = 0;
        int[] d = new int[n + 1];
        for(int i = 0; i < n; i++) {
            // TODO: 找到 d[l] < nums[i] < d[l + 1]
            int left = 0, right = len;
            while(left < right) {
                int mid = left + right + 1 >> 1;
                if(nums[i] <= d[mid]) right = mid - 1;
                else left = mid;
            }
            len = Math.max(len, left + 1);
            d[left + 1] = nums[i];
        }
        return len;
    }
}
