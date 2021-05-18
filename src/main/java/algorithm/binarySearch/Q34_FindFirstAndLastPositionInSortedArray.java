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
public class Q34_FindFirstAndLastPositionInSortedArray {
    // TODO: 找到数组中第一个大于target的元素下标
    private int binarySearch(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n;
        while(left < right) {
            int mid = left + right >> 1;
            if(nums[mid] <= target) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        if(n <= 0 || nums[0] > target || nums[n - 1] < target) return new int[]{-1, -1};
        int left = binarySearch(nums, target - 1);
        if(left == n || nums[left] != target) return new int[]{-1, -1};
        int right = binarySearch(nums, target);
        return new int[]{left, right - 1};
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
