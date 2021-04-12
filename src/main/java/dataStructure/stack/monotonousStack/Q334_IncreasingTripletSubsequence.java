package dataStructure.stack.monotonousStack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @program: Leetcode
 * @description:
 * 给你一个整数数组nums ，判断这个数组中是否存在长度为 3 的递增子序列。
 * 如果存在这样的三元组下标 (i, j, k)且满足 i < j < k ，使得nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,4,5]
 * 输出：true
 * 解释：任何 i < j < k 的三元组都满足题意
 *
 * 示例 2：
 * 输入：nums = [5,4,3,2,1]
 * 输出：false
 * 解释：不存在满足题意的三元组
 *
 * 示例 3：
 * 输入：nums = [2,1,5,0,4,6]
 * 输出：true
 * 解释：三元组 (3, 4, 5) 满足题意，因为 nums[3] == 0 < nums[4] == 4 < nums[5] == 6
 * 
 *
 * 提示：
 * 1 <= nums.length <= 105
 * -231 <= nums[i] <= 231 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/increasing-triplet-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-04-01 15:44
 **/
public class Q334_IncreasingTripletSubsequence {
    // TODO: 最主要的就是维护两个信息，左边的最小值，右边的最大值
    public boolean increasingTriplet1(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++ ) {
            left[i] = Math.min(nums[i], i > 0 ? left[i - 1] : Integer.MAX_VALUE);
            right[n - 1 - i] = Math.max(nums[n - 1 - i], i > 0 ? right[n - i] : Integer.MIN_VALUE);
        }
        for (int i = 0; i < n; i++) {
            if(left[i] < nums[i] && nums[i] < right[i]) return true;
        }
        return false;
    }

    // TODO: 维护了两个哨兵，一个是最小值，一个是次小值
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        if(n < 3) return false;
        int small = Integer.MAX_VALUE, mid = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            if(nums[i] <= small) small = nums[i];
            else if(nums[i] <= mid) mid = nums[i];
            else return true;
        }
        return false;
    }

}
