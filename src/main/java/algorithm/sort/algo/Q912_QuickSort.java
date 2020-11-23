package algorithm.sort.algo;

import java.util.Random;

/**
 * 给你一个整数数组nums，请你将该数组升序排列。
 *
 * 示例 1：
 * 输入：nums = [5,2,3,1]
 * 输出：[1,2,3,5]
 *
 * 示例 2：
 * 输入：nums = [5,1,1,2,0,0]
 * 输出：[0,0,1,1,2,5]
 *
 *
 * 提示：
 * 1 <= nums.length <= 50000
 * -50000 <= nums[i] <= 50000
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q912_QuickSort {
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length == 1) return nums;
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    // TODO:注意这种写法
    public void quickSort1(int[] nums, int left, int right) {
        if (left >= right) return;
        int pivot = nums[left], i = left - 1, j = right + 1;
        while (i < j) {
            do {
                i++;
            } while (nums[i] < pivot);
            do {
                j--;
            } while (nums[j] > pivot);
            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        // TODO：注意为什么是j 而不是i？？？为什么会有边界问题
        quickSort(nums, left, j);
        quickSort(nums, j + 1, right);
    }


    // TODO：我的写法
    public void quickSort(int[] nums, int left, int right) {
        //int mid = partition(nums, left, right);
        int mid = randomPartition(nums, left, right);
        quickSort(nums, left, mid - 1);
        quickSort(nums, mid + 1, right);
    }

    // TODO: 加了这个效率倍增！！！！
    //  想一想这样避免了什么情况出现？
    static Random random = new Random();
    public static int randomPartition(int[] nums, int left, int right) {
        int i = random.nextInt(right - left + 1) + left;
        int t = nums[i];
        nums[i] = nums[left];
        nums[left] = t;
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
