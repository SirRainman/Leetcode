package algorithm.dp.test;

import java.util.Arrays;

/**
 * @program: Leetcode
 * @description:
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 示例:
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *     从下标为 0 跳到下标为 1 的位置，跳1步，然后跳3步到达数组的最后一个位置。
 * 说明:
 * 假设你总是可以到达数组的最后一个位置。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-03-11 14:32
 **/
public class Q45_JumpGame2 {
    // TODO: dp[i] 到达i的最小步数
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 0x3f3f3f3f);
        dp[0] = 0;
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j <= i + nums[i] && j < n; j++) {
                dp[j] = Math.min(dp[j], dp[i] + 1);
            }
        }
        return dp[n - 1];
    }

    // TODO: 如果我们「贪心」地进行正向查找，每次找到可到达的最远位置，就可以在线性时间内得到最少的跳跃次数。
    public int jump1(int[] nums) {
        int n = nums.length;
        int last = 0, farthest = 0, step = 0;
        for(int i = 0; i < n - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if(i == last) {
                step++;
                last = farthest;
            }
        }
        return step;
    }
}
