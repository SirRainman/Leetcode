package algorithm.sort;

import java.util.Random;
import java.util.Scanner;
import java.util.Timer;

/**
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 *
 * 示例2:
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q215_KthLargestArray {
    public int findKthLargest(int[] nums, int k) {
        return quickSort(nums, 0, nums.length - 1, nums.length - k);
    }

    public int quickSort(int[] nums, int left, int right, int k) {
        int mid = randomPartition(nums, left, right);
        if (mid == k) {
            return nums[k];
        } else if (mid > k) {
            return quickSort(nums, left, mid - 1, k);
        } else {
            return quickSort(nums, mid + 1, right, k);
        }
    }

    // TODO: 加了这个效率倍增！！！！
    Random random = new Random();

    public int randomPartition(int[] nums, int left, int right) {
        int i = random.nextInt(right - left + 1) + left;
        int temp = nums[i];
        nums[i] = nums[right];
        nums[right] = temp;
        return partition(nums, left, right);
    }


    public static int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        while(left < right) {
            while(left < right && nums[right] >= pivot) right--;
            nums[left] = nums[right];
            while(left < right && nums[left] <= pivot ) left++;
            nums[right] = nums[left];
        }
        nums[left] = pivot;
        return left;
    }
}
