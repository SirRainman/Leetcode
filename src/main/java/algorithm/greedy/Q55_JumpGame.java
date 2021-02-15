package algorithm.greedy;

/**
 * 给定一个非负整数数组nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 *
 * 示例1：
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 *
 * 示例2：
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 * 
 * 提示：
 * 1 <= nums.length <= 3 * 104
 * 0 <= nums[i] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q55_JumpGame {
    // TODO: 贪心：每次走都走到最远可以去的的点
    public boolean canJump1(int[] nums) {
        return dfs(nums, 0, nums[0]);
    }

    public boolean dfs(int[] nums, int i, int step) {
        if(i + step >= nums.length - 1) return true;
        if(step == 0) return false;
        int start = i; // 选择最远可以到的点作为出发点
        for(int j = i + 1; j < nums.length && j <= i + step; j++) {
            if(j + nums[j] >= start + nums[start]) {
                start = j;
            }
        }
        return dfs(nums, start, nums[start]);
    }

    public boolean canJump(int[] nums) {
        int farthest = nums[0];
        for(int i = 0; i < nums.length && i <= farthest; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if(farthest >= nums.length - 1) return true;
        }
        return false;
    }
}
