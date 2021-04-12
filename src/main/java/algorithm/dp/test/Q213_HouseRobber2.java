package algorithm.dp.test;

import java.util.Arrays;

/**
 * @program: Leetcode
 * @description:
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
 * 这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
 * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。
 *
 * 示例1：
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 *
 * 示例 2：
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *     偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 示例 3：
 * 输入：nums = [0]
 * 输出：0
 *
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-03-29 17:15
 **/
public class Q213_HouseRobber2 {
    // TODO: 环状排列意味着第一个房子和最后一个房子中只能选择一个偷窃，
    //  因此可以把此环状排列房间问题约化为两个单排排列房间子问题：
    //      1.在不偷窃第一个房子的情况下（即 nums[1:]），最大金额是 p1
    //      2.在不偷窃最后一个房子的情况下（即 nums[:n-1]），最大金额是 p2
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;
        if(n == 1) return nums[0];
        return Math.max(robFrom(Arrays.copyOfRange(nums, 0, n - 1)),
                robFrom(Arrays.copyOfRange(nums, 1, n)));
    }

    public int robFrom1(int[] nums) {
        int[] dp = new int[nums.length + 1];
        dp[1] = nums[0];
        for(int i = 2; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
        }
        return dp[nums.length];
    }

    public int robFrom(int[] nums) {
        int pre1 = nums[0], pre2 = 0, res = 0;
        for(int i = 2; i < nums.length; i++) {
            int cur = Math.max(pre1, pre2 + nums[i]);
            res = Math.max(res, cur);
            pre2 = pre1;
            pre1 = cur;
        }
        return res;
    }
}
