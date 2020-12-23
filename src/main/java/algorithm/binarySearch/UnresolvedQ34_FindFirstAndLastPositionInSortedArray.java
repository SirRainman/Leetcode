package algorithm.binarySearch;

import java.util.Scanner;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 你的算法时间复杂度必须是O(log n) 级别。
 * 如果数组中不存在目标值，返回[-1, -1]。
 * 
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 *
 * 示例2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 * 
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class UnresolvedQ34_FindFirstAndLastPositionInSortedArray {
    /**
     * 注意！！！
     * 
     * 1.mid 的取值，为何是 low + ((high - low) >> 1) 或者 low + (high - low) / 2 而不是 (high + low) / 2 ？
     * 因为 int 类型最大表示范围是 2147483647 ，那么对于两个都接近 2147483647 的数字而言，它们相加的结果将会溢出，变成负数。
     * 2.mid 的取值为什么有时是 low + high >> 1 有时是 low + high + 1 >> 1
     * 如果希望在左边找 low + high >> 1
     * 如果希望在右边找 low + high + 1 >> 1
     * 
     * 3.while 循环的条件到底是 <= 还是 < ？
     * high 初始值不同，两个可能出现在不同功能的二分查找中。主要区别:
     * 前者相当于两端都是闭区间 [low, high] ，high = numsSize - 1 。
     * 后者相当于左闭右开区间 [low, high) ，high = numsSize。
     * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/solution/da-jia-bu-yao-kan-labuladong-de-jie-fa-fei-chang-2/
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0 || nums[0] > target || nums[nums.length - 1] < target) {
            return new int[]{-1, -1};
        }
        int[] range = new int[]{-1, -1};
        range[0] = binarySearch(nums, target - 1);
        range[1] = binarySearch(nums, target) - 1;
        //System.out.println(range[0] + " " + range[1]);
        if (range[0] == nums.length || nums[range[0]] != target) return new int[]{-1, -1};
        return range;
    }

    public int binarySearch(int[] nums, int target) {
        // TODO: 为什么 right = nums.length???
        //  当right = nums.length - 1;时，为什么不对？？？
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + right >> 1;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    // TODO: 上面的那个做法有点邪乎，以下面这个为主！！！
    public static void binarySearch2() {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt(), times = in.nextInt();
        int[] nums = new int[len];
        for (int i = 0; i < len; i++) nums[i] = in.nextInt();

        while (times-- > 0) {
            int target = in.nextInt();
            if (target < nums[0] || target > nums[len - 1]) {
                System.out.println("-1 -1");
                continue;
            }

            int start = -1, end = -1;
            // TODO：注意right = len - 1 而不是 len
            int left = 0, right = len - 1;
            while (left < right) {
                // TODO:mid在中间靠左的位置，所以left = mid + 1不会越界
                int mid = left + (right - left) / 2;
                if (target <= nums[mid]) right = mid;
                else left = mid + 1;
            }
            if (nums[left] != target) {
                System.out.println("-1 -1");
                continue;
            }
            start = left;

            left = 0;
            right = len - 1;
            while (left < right) {
                // TODO：mid在中间靠右的位置，所以right = mid - 1不会越界
                int mid = left + (right - left + 1) / 2;
                if (target >= nums[mid]) left = mid;
                else right = mid - 1;
            }
            end = left;
            System.out.println(start + " " + end);
        }
    }
}
