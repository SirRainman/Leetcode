package algorithm.double_pointer;

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 *
 * 示例：
 * 输入：nums =[1,2,3,4]
 * 输出：[1,3,2,4] 
 * 注：[3,1,2,4] 也是正确的答案之一。
 * 
 *
 * 提示：
 * 1 <= nums.length <= 50000
 * 1 <= nums[i] <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class QOffer21_PutOddBeforeEven {
    public int[] exchange1(int[] nums) {
        int left = 0, right = nums.length - 1;
        while(left < right) {
            while(left < right && nums[left] % 2 == 1) left++;
            while(left < right && nums[right] % 2 == 0) right--;
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }
        return nums;
    }

    // TODO: 注意这种写法，fast走过的地方将数组划分为了两部分，
    //  [0,slow-1]为奇数部分
    //  [slow, fast]为偶数部分
    public int[] exchange(int[] nums) {
        int fast = 0, slow = 0;
        while(fast < nums.length) {
            if((nums[fast] & 1) == 1) {
                int temp = nums[fast];
                nums[fast] = nums[slow];
                nums[slow] = temp;
                slow++;
            }
            fast++;
        }
        return nums;
    }
}
