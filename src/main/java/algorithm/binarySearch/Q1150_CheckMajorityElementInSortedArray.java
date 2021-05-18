package algorithm.binarySearch;

/**
 * @program: Leetcode
 * @description:
 * 给出一个按 非递减顺序排列的数组nums，和一个目标数值target。
 * 假如数组nums 中绝大多数元素的数值都等于target，则返回True，否则请返回False。
 * 所谓占绝大多数，是指在长度为 N的数组中出现必须超过N/2次。
 *
 * 示例 1：
 * 输入：nums = [2,4,5,5,5,5,5,6,6], target = 5
 * 输出：true
 * 解释：
 * 数字 5 出现了 5 次，而数组的长度为 9。
 * 所以，5 在数组中占绝大多数，因为 5 次 > 9/2。
 *
 * 示例 2：
 * 输入：nums = [10,100,101,101], target = 101
 * 输出：false
 * 解释：
 * 数字 101 出现了 2 次，而数组的长度是 4。
 * 所以，101 不是 数组占绝大多数的元素，因为 2 次 = 4/2。
 * 
 *
 * 提示：
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 10^9
 * 1 <= target <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/check-if-a-number-is-majority-element-in-a-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-03-31 12:18
 **/
public class Q1150_CheckMajorityElementInSortedArray {
    public boolean isMajorityElement(int[] nums, int target) {
        int n = nums.length;
        int begin = findLastPos(nums, target - 1);
        if(begin == n || nums[begin] != target) return false;
        int end = findLastPos(nums, target);
        return (end - begin) > n / 2;
    }

    // TODO: 寻找第一个大于target的数的下标
    private int findLastPos(int[] nums, int target) {
        int n = nums.length;
        // TODO: 注意right是从n开始设置的
        //  [left, right] 是寻找元素可以存在的下标范围
        int left = 0, right = n;
        while(left < right) {
            int mid = left + right >> 1;
            if(nums[mid] <= target) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    // TODO：找到target出现在数组中的最左边的位置，并判断他的区间的右边是否是target
    public boolean isMajorityElement1(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1;
        while(left < right) {
            int mid = left + right >> 1;
            if(nums[mid] >= target) right = mid;
            else left = mid + 1;
        }
        return nums[left] == target && left + n / 2 < n && nums[left + n / 2] == target;
    }
}
