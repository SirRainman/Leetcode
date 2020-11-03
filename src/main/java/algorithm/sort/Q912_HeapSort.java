package algorithm.sort;

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
    public class Solution {

        public int[] sortArray(int[] nums) {
            heapSort(nums);
            return nums;
        }

        public void heapSort(int[] nums) {
            int len = nums.length;
            // 将数组整理成堆
            heapify(nums);

            // 循环不变量：区间 [0, i] 堆有序
            for (int i = len - 1; i >= 1; ) {
                // 把堆顶元素（当前最大）交换到数组末尾
                swap(nums, 0, i);
                // 逐步减少堆有序的部分
                i--;
                // 下标 0 位置下沉操作，使得区间 [0, i] 堆有序
                siftDown(nums, 0, i);
            }
        }

        /**
         * 将数组整理成堆（堆有序）
         *
         * @param nums
         */
        private void heapify(int[] nums) {
            int len = nums.length;
            // 只需要从 i = (len - 1) / 2 这个位置开始逐层下移
            for (int i = (len - 1) / 2; i >= 0; i--) {
                siftDown(nums, i, len - 1);
            }
        }

        /**
         * @param nums
         * @param k    当前下沉元素的下标
         * @param end  [0, end] 是 nums 的有效部分
         */
        private void siftDown(int[] nums, int k, int end) {
            while (2 * k + 1 <= end) {
                int j = 2 * k + 1;
                if (j + 1 <= end && nums[j + 1] > nums[j]) {
                    j++;
                }
                if (nums[j] > nums[k]) {
                    swap(nums, j, k);
                } else {
                    break;
                }
                k = j;
            }
        }

        private void swap(int[] nums, int index1, int index2) {
            int temp = nums[index1];
            nums[index1] = nums[index2];
            nums[index2] = temp;
        }
    }
}
