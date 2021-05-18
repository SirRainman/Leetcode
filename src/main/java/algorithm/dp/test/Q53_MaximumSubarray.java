package algorithm.dp.test;

/**
 * 给定一个整数数组 nums，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释:连续子数组[4,-1,2,1] 的和最大，为6。
 *
 * 进阶:
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q53_MaximumSubarray {

    // TODO:
    //  集合划分：dp[i] 所有以i为结尾的连续子数组的和
    //  属性：max
    //  状态计算：dp[i] = max(dp[i - 1] + nums[i], nums[i])
    public int maxSubArray1(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        int maxSum = Integer.MIN_VALUE;
        for(int i = 1; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i - 1], nums[i - 1]);
            maxSum = Math.max(maxSum, dp[i]);
        }
        return maxSum;
    }

    // TODO: 空间优化
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int maxSum = Integer.MIN_VALUE;
        int lastSum = 0;
        for(int x : nums) {
            lastSum = Math.max(lastSum + x, x);
            maxSum = Math.max(maxSum, lastSum);
        }
        return maxSum;
    }

    // TODO:还有分治 线段树思想 没有进行探索
}
