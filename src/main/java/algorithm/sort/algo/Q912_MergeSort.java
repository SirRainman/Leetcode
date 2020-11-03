package algorithm.sort.algo;

/**
 * 给你一个整数数组nums，请你将该数组升序排列。
 *
 * 示例 1：
 *
 * 输入：nums = [5,2,3,1]
 * 输出：[1,2,3,5]
 * 示例 2：
 *
 * 输入：nums = [5,1,1,2,0,0]
 * 输出：[0,0,1,1,2,5]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 50000
 * -50000 <= nums[i] <= 50000
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q912_MergeSort {
    public int[] sortArray(int[] nums) {
        if(nums == null || nums.length == 1) return nums;
        temp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    int[] temp;
    public void mergeSort(int[] nums, int left, int right) {
        if(left >= right) return;

        int mid = left + right >> 1;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);

        int k = 0, i = left, j = mid + 1;
        while(i <= mid && j <= right) {
            if(nums[i] <= nums[j]) temp[k++] = nums[i++];
            else temp[k++] = nums[j++];
        }
        while(i <= mid) temp[k++] = nums[i++];
        while(j <= right) temp[k++] = nums[j++];

        for(i = left, j = 0; i <= right; i++, j++) {
            nums[i] = temp[j];
        }
    }
}
