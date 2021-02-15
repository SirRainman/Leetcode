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
public class Q912_HeapSort {
    public int findKthLargest(int[] nums, int k) {
        heapSort(nums);
        return nums[nums.length - k];
    }

    private void heapSort(int[] nums) {
        int n = nums.length;
        build(nums);
        for(int i = n - 1; i > 0; i--) {
            swap(nums, 0, i);
            heapify(nums, 0, i - 1);
        }
    }

    /**
     * 建立一个大根堆
     * @param nums
     */
    private void build(int[] nums) {
        int n = nums.length;
        for(int i = (n >> 1) - 1; i >= 0; i--) {
            heapify(nums, i, n - 1);
        }
    }

    /**
     * 更新堆
     * @param nums
     * @param i
     * @param end 更新堆数据范围
     */
    private void heapify(int[] nums, int i, int end) {
        int parent = i, child = 2 * parent + 1;
        while(child <= end) {
            if(child + 1 <= end && nums[child + 1] > nums[child]) child++;
            if(nums[child] > nums[parent]) swap(nums, child, parent);
            parent = child;
            child = 2 * child + 1;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
