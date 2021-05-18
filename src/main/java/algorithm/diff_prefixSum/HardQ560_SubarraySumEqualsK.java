package algorithm.diff_prefixSum;

import java.util.HashMap;

/**
 * 给定一个整数数组和一个整数k，你需要找到该数组中和为k的连续的子数组的个数。
 *
 * 示例 1 :
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 *
 * 说明 :
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数y2ky2的范围是[-1e7, 1e7]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ560_SubarraySumEqualsK {

    public int subarraySum1(int[] nums, int k) {
        int res = 0;
        for(int i = 0; i < nums.length; i++) {
            for(int j = i, sum = 0; j >= 0; j--) { // 统计[j, i]之间的和是否为k
                sum += nums[j];
                if(sum == k) res++;
            }
        }
        return res;
    }

    public int subarraySum2(int[] nums, int k) {
        int n = nums.length;
        int[] pre = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            pre[i] = pre[i - 1] + nums[i - 1];
        }

        int res = 0;
        for(int i = 1; i <= n; i++) {
            for(int j = i; j >= 1; j--) {
                if(pre[i] - pre[j - 1] == k) {
                    res++;
                }
            }
        }
        return res;
    }

    public int subarraySum(int[] nums, int k) {
        // key: 前缀和
        // value: 前缀和出现的次数
        HashMap < Integer, Integer > prefixCount = new HashMap<>();
        int res = 0, pre = 0; // 设pre为[0, i]之间的前缀和
        prefixCount.put(pre, 1); // 前缀和为0的前缀出现了1次
        for(int i = 0; i < nums.length; i++) {
            pre += nums[i];
            // pre[i] - pre[j - 1] = k
            // pre[j - 1] = pre[i] - k
            if(prefixCount.containsKey(pre - k)) res += prefixCount.get(pre - k);
            prefixCount.put(pre, prefixCount.getOrDefault(pre, 0) + 1);
        }
        return res;
    }

    // TODO: 注意下面的这个做法是错误的
    //  为什么这题不可以用双指针/滑动窗口?
    //  因为nums[i]可以小于0，也就是说右指针i向后移1位不能保证区间会增大，左指针j向后移1位也不能保证区间和会减小。
    //  给定j，i的位置没有二段性，vice versa。
    public int subarraySum3(int[] nums, int k) {
        int n = nums.length;
        int res = 0, sum = 0, left = 0, right = 0;
        while(right < n) {
            sum += nums[right];
            while(left < right && sum > k) {
                sum -= nums[left];
                left++;
            }
            if(sum == k) res++;
            right++;
        }
        return res;
    }
}
