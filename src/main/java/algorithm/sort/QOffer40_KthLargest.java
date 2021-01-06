package algorithm.sort;

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
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0 || arr.length == 0) return new int[0];

        // quickSort1(arr, 0, arr.length - 1, k - 1);
        quickSort(arr, 0, arr.length - 1, k);
        int[] res = new int[k];
        for(int i = 0; i < k; i++) res[i] = arr[i];
        return res;
    }

    public void quickSort1(int[] nums, int left, int right, int K) {
        if(left >= right) return;

        int p = partition(nums, left, right);
        if(p == K) {
            return ;
        } else if(p < K) {
            quickSort(nums, p + 1, right, K);
        } else {
            quickSort(nums, left, p - 1, K);
        }
    }

    public void quickSort(int[] nums, int left, int right, int K) {
        if(left >= right) return;
        int p = partition(nums, left, right);
        int pos = p - left + 1; // [left, right] 中,nums[p] 排在第pos位
        if(pos == K) {
            return ;
        } else if(pos < K) {
            quickSort(nums, p + 1, right, K - pos);
        } else {
            quickSort(nums, left, p - 1, K);
        }
    }

    public int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        while(left < right) {
            while(left < right && pivot <= nums[right] ) right--;
            nums[left] = nums[right];
            while(left < right && pivot >= nums[left]) left++;
            nums[right] = nums[left];
        }
        nums[left] = pivot;
        return left;
    }
}
