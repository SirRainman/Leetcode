package algorithm.double_pointer;

/**
 * @program: Leetcode
 * @description:
 * 给定一个增序排列数组 nums ，你需要在 原地 删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * 说明：
 * 为什么返回数值是整数，但输出的答案是数组呢？
 * 示例 1：
 * 输入：nums = [1,1,1,2,2,3]
 * 输出：5, nums = [1,1,2,2,3]
 * 解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。 你不需要考虑数组中超出新长度后面的元素。
 *
 * 提示：
 * 0 <= nums.length <= 3 * 104
 * -104 <= nums[i] <= 104
 * nums 按递增顺序排列
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-03-11 21:44
 **/
public class HardQ80_RemoveDuplicatesFromSortedArray2 {
    public int removeDuplicates1(int[] nums) {
        int slow = 0, fast = 1, count = 0;
        while(fast < nums.length) {
            if(nums[slow] == nums[fast]) count++;
            else count = 0;
            if(count < 2) nums[++slow] = nums[fast];
            fast++;
        }
        return slow + 1;
    }

    public int removeDuplicates(int[] nums) {
        int index = 0;
        for(int x : nums) {
            if(index < 2 || x > nums[index - 2]) nums[index++] = x;
        }
        return index;
    }
}
