package algorithm.diff_prefixSum;

/**
 * @program: Leetcode
 * @description:
 * 给定一个含有n个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组[numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 * 示例 1：
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组[4,3]是该条件下的长度最小的子数组。
 *
 * 示例 2：
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 *
 * 示例 3：
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 *
 * 提示：
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 *
 * 进阶：
 * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-06-03 14:06
 **/
public class Q209_MinimumSizeSubarraySum {
    public int minSubArrayLen1(int target, int[] nums) {
        int n = nums.length;
        int left = 0, right = 0, sum = 0, res = Integer.MAX_VALUE;
        while(right < n) {
            sum += nums[right];
            while(left <= right && sum >= target) {
                res = Math.min(res, right - left + 1);
                sum -= nums[left];
                left++;
            }
            right++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;

        int[] preSum = new int[n + 1];
        for(int i = 1; i <= n; i++) preSum[i] = preSum[i - 1] + nums[i - 1];

        int res = Integer.MAX_VALUE;
        for(int i = 0; i <= n; i++) {
            int right = binarySearch(preSum, i, preSum[i] + target);
            if(right < n + 1) res = Math.min(res, right - i);
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    private int binarySearch(int[] nums, int left, int target) {
        int right = nums.length;
        if(nums[right - 1] < target) return right;
        while(left < right) {
            int mid = left + right >> 1;
            if(nums[mid] >= target) right = mid;
            else left = mid + 1;
        }
        return left;
    }
}
