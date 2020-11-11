package algorithm.diff_prefixSum;

import java.util.HashMap;

/**
 * 给定一个整数数组和一个整数k，你需要找到该数组中和为k的连续的子数组的个数。
 *
 * 示例 1 :
 *
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * 说明 :
 *
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数y2ky2的范围是[-1e7, 1e7]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ560_SubarraySumEqualsK {
    public class Solution {
        public int subarraySum(int[] nums, int k) {
            // key: 前缀和 value: 前缀和出现的次数
            HashMap < Integer, Integer > mp = new HashMap<>();
            mp.put(0, 1);
            int count = 0, pre = 0;
            for (int i = 0; i < nums.length; i++) {
                pre += nums[i];
                // TODO:
                //  1 想一想怎么推导公式？
                //  2 怎么根据公式进行优化？？
                // sums[i] - sums[j - 1] = k
                // sums[j - 1] = sums[i] - k
                // 问题转化为判断是否出现过 前缀和 == sums[i] - k。
                // 判断是否出现过用map set
                if (mp.containsKey(pre - k)) {
                    count += mp.get(pre - k);
                }
                mp.put(pre, mp.getOrDefault(pre, 0) + 1);
            }
            return count;
        }
    }
}
