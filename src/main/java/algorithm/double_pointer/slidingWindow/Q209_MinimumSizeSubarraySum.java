package algorithm.double_pointer.slidingWindow;

/**
 * 给定一个含有n个正整数的数组和一个正整数s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。
 * 如果不存在符合条件的子数组，返回 0。
 *
 * 示例：
 * 输入：s = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组[4,3]是该条件下的长度最小的子数组。
 *
 * 进阶：
 * 如果你已经完成了 O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q209_MinimumSizeSubarraySum {

    // TODO：滑动窗口
    //  滑动窗口的结束的判断是right，而不是left
    public int minSubArrayLen1(int s, int[] nums) {
        int sum = 0, minLen = nums.length + 1;
        int left = 0, right = 0;
        while(right < nums.length) {
            sum += nums[right];
            while(sum >= s) {
                // TODO：注意这里判断是否合法转移到了这里，而不是在循环体的外面
                minLen = Math.min(minLen, right - left + 1);
                sum -= nums[left];
                left++;
            }
            right++;
        }
        return minLen == nums.length + 1 ? 0 : minLen;
    }

    // TODO: 因为他是每个数都是正数，所以前缀和一定是递增的，所以判断是否满足可以将窗口的值不断的缩小进行判断a
    public int minSubArrayLen(int s, int[] nums) {
        int left = 0, right = nums.length;
        while(left < right) {
            int mid = left + right >> 1;
            if(check(nums, s, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
            // System.out.println(left + " " + right);
        }
        return left < nums.length || check(nums, s, nums.length) ? left : 0;
    }

    public boolean check(int[] nums, int s, int k) {
        int sum = 0, pre = 0;
        for(int i = 0; i < nums.length; i++) {
            if(sum - pre >= s) break;
            sum += nums[i];
            if(i >= k) pre += nums[i - k];
        }
        return sum - pre >= s;
    }
}
