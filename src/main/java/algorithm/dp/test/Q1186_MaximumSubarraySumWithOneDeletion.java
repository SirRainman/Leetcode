package algorithm.dp.test;

/**
 * @program: Leetcode
 * @description:
 * 给你一个整数数组，返回它的某个非空 子数组（连续元素）在执行一次可选的删除操作后，所能得到的最大元素总和。
 * 换句话说，你可以从原数组中选出一个子数组，并可以决定要不要从中删除一个元素（只能删一次哦），
 * （删除后）子数组中至少应当有一个元素，然后该子数组（剩下）的元素总和是所有子数组之中最大的。
 * 注意，删除一个元素后，子数组 不能为空。
 *
 * 示例 1：
 * 输入：arr = [1,-2,0,3]
 * 输出：4
 * 解释：我们可以选出 [1, -2, 0, 3]，然后删掉 -2，这样得到 [1, 0, 3]，和最大。
 *
 * 示例 2：
 * 输入：arr = [1,-2,-2,3]
 * 输出：3
 * 解释：我们直接选出 [3]，这就是最大和。
 *
 * 示例 3：
 * 输入：arr = [-1,-1,-1,-1]
 * 输出：-1
 * 解释：最后得到的子数组不能为空，所以我们不能选择 [-1] 并从中删去 -1 来得到 0。
 *      我们应该直接选择 [-1]，或者选择 [-1, -1] 再从中删去一个 -1。
 *
 * 提示：
 * 1 <= arr.length <= 10^5
 * -10^4 <= arr[i] <= 10^4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray-sum-with-one-deletion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-04-13 21:46
 **/
public class Q1186_MaximumSubarraySumWithOneDeletion {
    // TODO:
    //  集合划分
    //          dp[i][0] 截止到nums[i] 前面不删除元素的最大值
    //          dp[i][1] 截止到nums[i]，删除了一个元素之后最大的连续子序列的和
    //  属性 max
    //  状态转移
    //          dp[i][0] = Math.max(dp[i - 1][0] + nums[i], nums[i])
    //          dp[i][1] = Math.max(dp[i - 1][0] + nums[i], dp[i - 1][0])
    public int maximumSum(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 1][2];
        int res = Integer.MIN_VALUE;
        for(int i = 1; i <= n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0] + nums[i - 1], nums[i - 1]);
            dp[i][1] = Math.max(dp[i - 1][1] + nums[i - 1], dp[i - 1][0]);
            res = Math.max(dp[i][0], dp[i][1]);
        }
        return res;
    }
}
