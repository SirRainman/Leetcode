package algorithm.binarySearch;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组[0,0,1,2,2,5,6]可能变为[2,5,6,0,0,1,2])。
 * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回true，否则返回false。
 *
 * 示例1:
 * 输入: nums = [2,5,6,0,0,1,2], target = 0
 * 输出: true
 *
 * 示例2:
 * 输入: nums = [2,5,6,0,0,1,2], target = 3
 * 输出: false
 *
 * 进阶:
 * 这是 搜索旋转排序数组的延伸题目，本题中的nums 可能包含重复元素。
 * 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ33_81_SearchInRotatedSortedArray2 {
    // TODO: 和Q33相比，重复元素意味着什么？？
    public boolean search(int[] nums, int target) {
        int n = nums.length;
        if(n == 0) return false;
        if(n == 1) return nums[0] == target;

        int left = 0, right = n - 1;
        while(left <= right) {
            int mid = left + right >> 1;
            if(nums[mid] == target) return true;
            if(nums[left] == nums[mid]) {
                left++;
            } else if(nums[left] < nums[mid]) {
                if(nums[left] <= target && target < nums[mid]) right = mid ;
                else left = mid + 1;
            } else {
                if(nums[mid] < target && target <= nums[right]) left = mid + 1;
                else right = mid;
            }
        }

        return false;
    }
}
