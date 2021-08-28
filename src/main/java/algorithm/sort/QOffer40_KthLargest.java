package algorithm.sort;

import java.util.PriorityQueue;

/**
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 *
 * 示例 1：
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 *
 * 示例 2：
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 * 
 * 限制：
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i]<= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class QOffer40_KthLargest {
    public int[] getLeastNumbers1(int[] arr, int k) {
        int[] res = new int[k];
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for(int x : arr) {
            heap.offer(x);
            if(heap.size() > k) heap.poll();
        }
        for(int i = 0; !heap.isEmpty(); i++) res[i] = heap.poll();
        return res;
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        int[] res = new int[k];
        quickSort(arr, k, 0, arr.length - 1);
        for(int i = 0; i < k; i++) res[i] = arr[i];
        return res;
    }

    private void quickSort(int[] nums, int k, int left, int right) {
        if(left >= right) return;
        int mid = partition(nums, left, right);
        if(mid == k) return;
        else if(mid < k) quickSort(nums, k, mid + 1, right);
        else quickSort(nums, k, left, mid - 1);
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        while(left < right) {
            while(left < right && pivot <= nums[right]) right--;
            nums[left] = nums[right];
            while(left < right && pivot >= nums[left]) left++;
            nums[right] = nums[left];
        }
        nums[left] = pivot;
        return left;
    }
}
