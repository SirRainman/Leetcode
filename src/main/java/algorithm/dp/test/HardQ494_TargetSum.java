package algorithm.dp.test;

/**
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。
 * 现在你有两个符号+和-。对于数组中的任意一个整数，你都可以从+或-中选择一个符号添加在前面。
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 * 
 * 示例：
 * 输入：nums: [1, 1, 1, 1, 1], S: 3
 * 输出：5
 * 解释：
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * 
 * 一共有5种方法让最终目标和为3。
 * 
 * 提示：
 * 数组非空，且长度不会超过 20 。
 * 初始的数组的和不会超过 1000 。
 * 保证返回的最终结果能被 32 位整数存下。
 * 
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/target-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ494_TargetSum {
    // TODO: 01背包
    //  从数字里选出neg 使得 (sum - neg) - neg = target
    //  则 neg = (sum - target) / 2
    public int findTargetSumWays3(int[] nums, int target) {
        int n = nums.length, sum = 0;
        for(int x : nums) sum += x;
        int diff = sum - target;
        if(diff < 0 || diff % 2 != 0) return 0;
        int neg = diff / 2;
        int[][] dp = new int[n + 1][neg + 1]; // dp[i][j] 表示用数组中的前 i 个元素，组成和为 j 的方案数。
        dp[0][0] = 1;
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= neg; j++) {
                if(j >= nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][neg];
    }


    // TODO：在【416.分割等和子集】这道题中，要求的输出结果就是boolean值，因此我们定义的dp数组只需要记录T/F即可，
    //  但是这道题要求返回结果是方法数，那么我们dp数组需要记录的数据就是具体的方法数。
    //  dp[i][j]定义为从数组nums中 0 - i 的元素进行加减可以得到 j 的方法数量。
    //  dp[i][j] = dp[i - 1][j - nums[i]] + dp[i - 1][j + nums[i]]
    public int findTargetSumWays1(int[] nums, int S) {
        int n = nums.length;
        int[][] dp = new int[n][2010]; // dp[i][j] 表示用数组中的前 i 个元素，组成和为 j 的方案数。
        dp[0][nums[0] + 1000] = 1; // 由于数组中所有数的和不超过 1000，那么 j 的最小值可以达到 -1000。
        dp[0][-nums[0] + 1000] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = -1000; j <= 1000; j++) {
                if (dp[i - 1][j + 1000] > 0) {
                    // dp[i][j] = dp[i - 1][j - nums[i]] + dp[i - 1][j + nums[i]];
                    // TODO: 状态转移
                    dp[i][j - nums[i] + 1000] += dp[i - 1][j + 1000]; // 减去当前元素，当前状态dp[i][j - nums[i]]可以由上一层的dp[i - 1][j]转移而来
                    dp[i][j + nums[i] + 1000] += dp[i - 1][j + 1000]; // 加上当前元素，当前状态dp[i][j + nums[i]]可以由上一层的dp[i - 1][j]转移而来
                }
            }
        }
        return S > 1000 ? 0 : dp[n - 1][S + 1000];
    }

    // TODO: dp优化为一维
    //  dp[i][...] 只和 dp[i - 1][...] 有关，因此我们可以优化动态规划的空间复杂度，只需要使用两个一维数组即可。
    public int findTargetSumWays(int[] nums, int S) {
        int n = nums.length;
        int[] dp = new int[2010]; // dp[i][j] 表示用数组中的前 i 个元素，组成和为 j 的方案数。
        dp[nums[0] + 1000] = 1; // 由于数组中所有数的和不超过 1000，那么 j 的最小值可以达到 -1000。
        dp[-nums[0] + 1000] = 1;
        for (int i = 1; i < n; i++) {
            int[] next = new int[2010];
            for (int j = -1000; j <= 1000; j++) {
                if (dp[j + 1000] > 0) {
                    // dp[i][j] = dp[i - 1][j - nums[i]] + dp[i - 1][j + nums[i]];
                    // TODO: 状态转移
                    next[j - nums[i] + 1000] += dp[j + 1000]; // 减去当前元素，当前状态dp[i][j - nums[i]]可以由上一层的dp[i - 1][j]转移而来
                    next[j + nums[i] + 1000] += dp[j + 1000]; // 加上当前元素，当前状态dp[i][j + nums[i]]可以由上一层的dp[i - 1][j]转移而来
                }
            }
            dp = next;
        }
        return S > 1000 ? 0 : dp[S + 1000];
    }
}
