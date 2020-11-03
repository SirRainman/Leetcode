package algorithm.sort;

/**
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 * 示例 1:
 *
 * 输入: [7,5,6,4]
 * 输出: 5
 * 
 *
 * 限制：
 * 0 <= 数组长度 <= 50000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQOffer51_LCOF {
    class Solution {
        public int reversePairs(int[] nums) {
            temp = new int[nums.length];
            mergeSort(nums, 0, nums.length - 1);

            return ans;
        }

        int[] temp;
        int ans = 0;
        public void mergeSort(int[] nums, int left, int right) {
            if(left >= right) return;

            int mid = left + right >> 1;
            mergeSort(nums, left, mid);
            mergeSort(nums, mid + 1, right);

            int k = 0, i = left, j = mid + 1;
            while( i <= mid && j <= right) {
                if(nums[i] <= nums[j]) temp[k++] = nums[i++];
                else {
                    temp[k++] = nums[j++];
                    ans += mid - i + 1;
                }
            }
            while(i <= mid) temp[k++] = nums[i++];
            while(j <= right) temp[k++] = nums[j++];

            for(i = left, j = 0; i <= right; i++, j++) {
                nums[i] = temp[j];
            }
        }
    }
}