package algorithm.diff_prefixSum;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: Leetcode
 * @description:
 * 给定一个数组 nums 和一个目标值 k，找到和等于 k 的最长子数组长度。如果不存在任意一个符合要求的子数组，则返回 0。
 * 注意:
 * nums 数组的总和是一定在 32 位有符号整数范围之内的。
 *
 * 示例 1:
 * 输入: nums = [1, -1, 5, -2, 3], k = 3
 * 输出: 4 
 * 解释: 子数组 [1, -1, 5, -2] 和等于 3，且长度最长。
 *
 * 示例 2:
 * 输入: nums = [-2, -1, 2, 1], k = 1
 * 输出: 2 
 * 解释: 子数组 [-1, 2] 和等于 1，且长度最长。
 * 进阶:
 * 你能使时间复杂度在 O(n) 内完成此题吗?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-size-subarray-sum-equals-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-06-03 13:38
 **/
public class Q325_MaximumSizeSubarraySumEqualsK {
    public int maxSubArrayLen(int[] nums, int k) {
        int n = nums.length;
        int res = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for(int i = 0; i < n; i++) {
            sum += nums[i];
            if(map.containsKey(sum - k)) res = Math.max(res, i - map.get(sum - k));
            if(!map.containsKey(sum)) map.put(sum, i);
        }
        return res;
    }
}
