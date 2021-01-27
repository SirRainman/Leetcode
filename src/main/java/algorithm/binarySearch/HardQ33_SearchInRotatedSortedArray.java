package algorithm.binarySearch;

/**
 * 升序排列的整数数组 nums 在预先未知的某个点上进行了旋转（例如， [0,1,2,4,5,6,7] 经旋转后可能变为[4,5,6,7,0,1,2] ）。
 * 请你在数组中搜索target ，如果数组中存在这个目标值，则返回它的索引，否则返回-1。
 *
 * 示例 1：
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 *
 * 示例2：
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 *
 * 示例 3：
 * 输入：nums = [1], target = 0
 * 输出：-1
 * 
 * 提示：
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * nums 中的每个值都 独一无二
 * nums 肯定会在某个点上旋转
 * -10^4 <= target <= 10^4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ33_SearchInRotatedSortedArray {
    public int findRotatedIndex(int[] nums) {
        int left = 0, right = nums.length - 1;
        while(left < right) {
            int mid = left + right >> 1;
            if(nums[mid] < nums[right]) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    public int findTarget(int[] nums, int left, int right, int target) {
        while(left < right) {
            int mid = left + right >> 1;
            if(nums[mid] == target) return mid;
            else if(nums[mid] < target) left = mid + 1;
            else right = mid;
        }
        return nums[left] == target ? left : -1;
    }

    public int search1(int[] nums, int target) {
        int rotated = findRotatedIndex(nums);
        int left = findTarget(nums, 0, rotated - 1, target);
        int right = findTarget(nums, rotated, nums.length - 1, target);
        return left == -1 ? right : left;
    }

    // TODO: 注意每次mid出来之后，都可以确定一下mid左右两个区间是否是有序的！
    public int search(int[] nums, int target) {
        int n = nums.length;
        if(n == 0) return -1;
        if(n == 1) return nums[0] == target ? 0 : -1;

        int left = 0, right = n - 1;
        while(left < right) {
            int mid = left + right >> 1;
            if(nums[mid] == target) return mid;
            if(nums[left] <= nums[mid]) { // [left, mid] 区间内是有序数组
                if(nums[left] <= target && target < nums[mid]) right = mid;
                else left = mid + 1;
            } else { // [mid, right] 区间内是有序数组
                if(nums[mid] < target && target <= nums[right]) left = mid + 1;
                else right = mid;
            }
        }
        return nums[left] == target ? left : -1;
    }
}
