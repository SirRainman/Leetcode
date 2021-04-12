package algorithm.double_pointer.slidingWindow;

/**
 * 给定一个正整数数组nums。
 * 找出该数组内乘积小于k的连续的子数组的个数。
 *
 * 示例 1:
 * 输入: nums = [10,5,2,6], k = 100
 * 输出: 8
 * 解释: 8个乘积小于100的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
 * 需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
 *
 * 说明:
 * 0 < nums.length <= 50000
 * 0 < nums[i] < 1000
 * 0 <= k < 10^6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarray-product-less-than-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q713_SubarrayProductLessThanK {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(k == 0) return 0;
        int n = nums.length;
        int left = 0, right = 0, res = 0, multi = 1;
        while(right < n) {
            multi *= nums[right];
            while(left <= right && multi >= k) {
                multi /= nums[left];
                left++;
            }
            // TODO: 注意求出来一个窗口以后，有效的子数组是以最右端为终点的连续子数组
            res += right - left + 1;
            right++;
        }
        return res;
    }
}
