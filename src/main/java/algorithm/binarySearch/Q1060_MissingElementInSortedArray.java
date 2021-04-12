package algorithm.binarySearch;

/**
 * @program: Leetcode
 * @description:
 * 现有一个按 升序 排列的整数数组 nums ，其中每个数字都 互不相同 。
 * 给你一个整数 k ，请你找出并返回从数组最左边开始的第 k 个缺失数字。
 *
 * 示例 1：
 * 输入：nums = [4,7,9,10], k = 1
 * 输出：5
 * 解释：第一个缺失数字为 5 。
 *
 * 示例 2：
 * 输入：nums = [4,7,9,10], k = 3
 * 输出：8
 * 解释：缺失数字有 [5,6,8,...]，因此第三个缺失数字为 8 。
 *
 * 示例 3：
 * 输入：nums = [1,2,4], k = 3
 * 输出：6
 * 解释：缺失数字有 [3,5,6,7,...]，因此第三个缺失数字为 6 。
 * 
 *
 * 提示：
 * 1 <= nums.length <= 5 * 104
 * 1 <= nums[i] <= 107
 * nums 按 升序 排列，其中所有元素 互不相同 。
 * 1 <= k <= 108
 *
 * 进阶：你可以设计一个对数时间复杂度（即，O(log(n))）的解决方案吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/missing-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-03-09 10:26
 **/
public class Q1060_MissingElementInSortedArray {

    // TODO：截止到idx，已经错过了多少到数字了
    private int missing(int idx, int[] nums) {
        return nums[idx] - nums[0] - idx;
    }

    public int missingElement1(int[] nums, int k) {
        int n = nums.length;
        if(missing(n - 1, nums) < k) return nums[n - 1] - missing(n - 1, nums) + k;

        int idx = 1;
        while(missing(idx, nums) < k) idx++;

        return nums[idx - 1] - missing(idx - 1, nums) + k;
    }

    public int missingElement(int[] nums, int k) {
        int n = nums.length;
        if(missing(n - 1, nums) < k) return nums[n - 1] - missing(n - 1, nums) + k;

        int left = 0, right = n - 1;
        while(left < right) {
            int mid = left + right >> 1;
            if(missing(mid, nums) < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left - 1] + k - missing(left - 1, nums);
    }
}
