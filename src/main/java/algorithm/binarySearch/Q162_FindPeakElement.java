package algorithm.binarySearch;

/**
 * @program: Leetcode
 * @description:
 * 峰值元素是指其值大于左右相邻值的元素。
 * 给你一个输入数组nums，找到峰值元素并返回其索引。
 * 数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * 你可以假设nums[-1] = nums[n] = -∞ 。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,1]
 * 输出：2
 * 解释：3 是峰值元素，你的函数应该返回其索引 2。
 *
 * 示例2：
 * 输入：nums = [1,2,1,3,5,6,4]
 * 输出：1 或 5 
 * 解释：你的函数可以返回索引 1，其峰值元素为 2；
 *     或者返回索引 5， 其峰值元素为 6。
 *
 * 提示：
 * 1 <= nums.length <= 1000
 * -231 <= nums[i] <= 231 - 1
 * 对于所有有效的 i 都有 nums[i] != nums[i + 1]
 * 进阶：你可以实现时间复杂度为 O(logN) 的解决方案吗？
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-peak-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-03-12 13:11
 **/
public class Q162_FindPeakElement {
    public int findPeakElement1(int[] nums) {
        int n = nums.length;
        for(int i = 0; i + 1 < n; i++) {
            if(nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return n - 1;
    }

    // TODO: 将数组看成一个交替上升或下降的数组，输出其中的一个山峰就行
    public int findPeakElement2(int[] nums) {
        int n = nums.length;
        int left = 0, right = n - 1;
        while(left < right) {
            int mid = left + right >> 1;
            if(nums[mid] > nums[mid + 1]) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    public int findPeakElement(int[] nums) {
        return binarySearch(nums, 0, nums.length - 1);
    }

    public int binarySearch(int[] nums, int left, int right) {
        if(left >= right) return left;
        int mid = left + right >> 1;
        if(nums[mid] > nums[mid + 1]) return binarySearch(nums, left, mid);
        else return binarySearch(nums, mid + 1, right);
    }
}
