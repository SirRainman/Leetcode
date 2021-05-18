package algorithm.binarySearch;

/**
 * 统计一个数字在排序数组中出现的次数。
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: 2
 *
 * 示例2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: 0
 * 
 * 限制：
 * 0 <= 数组长度 <= 50000
 *
 * 注意：本题与主站 34 题相同（仅返回值不同）：
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class QOffer53_FindNum {

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0 || nums[0] > target || nums[nums.length - 1] < target) {
            return 0;
        }
        int right = binarySearch(nums, target);
        int left = binarySearch(nums, target-1);
        return right - left;
    }

    // TODO: 在数组中找到第一个大于target的数的下标
    private int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length; // 注意right的取值
        while(left < right) {
            int mid = left + right >> 1;
            if(nums[mid] <= target) left = mid + 1; // 注意left的变化
            else right = mid;
        }
        return left;
    }
}
