package algorithm.dp;

import java.lang.reflect.Array;

/**
 *  给定一个整数数组 nums，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释:连续子数组[4,-1,2,1] 的和最大，为6。
 * 进阶:
 *
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class UnresolvedQ53_MaximumSubarray {
    public int maxSubArray2(int[] nums) {
        int[] subSum = new int[nums.length];
        int maxSubSum = Integer.MIN_VALUE;
        subSum[0] = nums[0];
        maxSubSum = Math.max(subSum[0], maxSubSum);
        for(int i = 1; i < nums.length; i++) {
            subSum[i] = Math.max(subSum[i-1] + nums[i], nums[i]);
            maxSubSum = Math.max(subSum[i], maxSubSum);
        }
        return maxSubSum;
    }

    public int maxSubArray(int[] nums) {
        int maxSubSum = nums[0];
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum = Math.max(sum + nums[i], nums[i]);
            maxSubSum = Math.max(sum, maxSubSum);
        }
        return maxSubSum;
    }

    // TODO:还有分治 没有进行探索
}
