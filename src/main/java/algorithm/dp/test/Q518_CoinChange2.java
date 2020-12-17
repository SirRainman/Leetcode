package algorithm.dp.test;

/**
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
 *
 * 示例 1:
 * 输入: amount = 5, coins = [1, 2, 5]
 * 输出: 4
 * 解释: 有四种方式可以凑成总金额:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 *
 * 示例 2:
 * 输入: amount = 3, coins = [2]
 * 输出: 0
 * 解释: 只用面额2的硬币不能凑成总金额3。
 *
 * 示例 3:
 * 输入: amount = 10, coins = [10]
 * 输出: 1
 * 
 *
 * 注意:
 * 你可以假设：
 * 0 <= amount (总金额) <= 5000
 * 1 <= coin (硬币面额)<= 5000
 * 硬币种类不超过 500 种
 * 结果符合 32 位符号整数
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change-2
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q518_CoinChange2 {
    public int change1(int amount, int[] coins) {
        int n = coins.length;
        if(n == 0) return amount == 0 ? 1 : 0;
        int[][] dp = new int[n + 1][amount + 1]; // dp[i][j] 前i个硬币，最多可以凑成面额j的硬币组合数
        dp[0][0] = 1;
        for(int i = coins[0]; i <= amount; i += coins[0]) dp[0][i] = 1;
        for(int i = 1; i < n; i++) {
            for(int j = 0; j <= amount; j++) {
                // TODO：dp[i][j]     = dp[i - 1][j] + dp[i - 1][j - c] + ... + dp[i - 1][j - k * c]
                for(int k = 0; k * coins[i] <= j; k++) {
                    dp[i][j] += dp[i - 1][j - k * coins[i]];
                }
            }
        }

        return dp[n - 1][amount];
    }

    // TODO: 如何去掉k重循环
    //  dp[i][j]     = dp[i - 1][j] + dp[i - 1][j - c] + ... + dp[i - 1][j - k * c]
    //  dp[i][j - c] =                dp[i - 1][j - c] + ... + dp[i - 1][j - k * c]
    //  dp[i][j]     = dp[i - 1][j] + dp[i][j - c]
    public int change2(int amount, int[] coins) {
        int n = coins.length;
        if(n == 0) return amount == 0 ? 1 : 0;
        int[][] dp = new int[n][amount + 1];

        dp[0][0] = 1;
        for(int i = coins[0]; i <= amount; i += coins[0]) dp[0][i] = 1;

        for(int i = 1; i < n; i++) {
            for(int j = 0; j <= amount; j++) {
                if(j < coins[i]) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]];
            }
        }

        return dp[n - 1][amount];
    }

    // TODO: 优化到一维
    public int change3(int amount, int[] coins) {
        int n = coins.length;
        if(n == 0) return amount == 0 ? 1 : 0;
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for(int i = coins[0]; i <= amount; i += coins[0]) dp[i] = 1;
        for(int i = 1; i < n; i++) {
            for(int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }

    // TODO: 优化到一维
    // TODO: 为什么上面到那个方法比较快？
    public int change(int amount, int[] coins) {
        int n = coins.length;
        if(n == 0) return amount == 0 ? 1 : 0;
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for(int i = 0; i < n; i++) {
            for(int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }
}
