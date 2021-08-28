package algorithm.diff_prefixSum;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: Leetcode
 * @description:
 * 给你一个整数数组 nums 和一个整数k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：
 * 子数组大小 至少为 2 ，且
 * 子数组元素总和为 k 的倍数。
 * 如果存在，返回 true ；否则，返回 false 。
 * 如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。
 *
 * 示例 1：
 * 输入：nums = [23,2,4,6,7], k = 6
 * 输出：true
 * 解释：[2,4] 是一个大小为 2 的子数组，并且和为 6 。
 *
 * 示例 2：
 * 输入：nums = [23,2,6,4,7], k = 6
 * 输出：true
 * 解释：[23, 2, 6, 4, 7] 是大小为 5 的子数组，并且和为 42 。 
 * 42 是 6 的倍数，因为 42 = 7 * 6 且 7 是一个整数。
 *
 * 示例 3：
 * 输入：nums = [23,2,6,4,7], k = 13
 * 输出：false
 * 
 * 提示：
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 109
 * 0 <= sum(nums[i]) <= 231 - 1
 * 1 <= k <= 231 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/continuous-subarray-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-06-02 10:24
 **/
public class Q523_ContinuousSubarraySum {
    // TODO: 前缀和的简化思路
    //  1 计算前缀和
    //  2 计算两点之间的区域和（差值），以及两点之间的距离
    //      若 diff mod k = 0，且距离大于2，则说明存在
    //  3 若不计算两点之间的差值，且两点的前缀和对 k 取模后的余数相同的话，则说明两点间的区间和是k的倍数
    //  4 若取余的结果存在相同的值，则存在
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        if(n < 2) return false;
        int remainder = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1);
        for(int i = 0; i < n; i++) {
            remainder = (remainder + nums[i]) % k; // 关键，前缀和的余数
            if(map.containsKey(remainder)) {
                int preIndex = map.get(remainder);
                if(i - preIndex >= 2) return true;
            } else {
                map.put(remainder, i);
            }
        }
        return false;
    }
}
