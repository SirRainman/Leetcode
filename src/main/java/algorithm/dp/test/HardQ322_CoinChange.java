package algorithm.dp.test;

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回-1。
 *
 * 
 *
 * 示例1:
 *
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3 
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 *
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * 
 *
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ322_CoinChange {
    // TODO:没有记忆话搜索，所以会超时
    public int coinChange1(int[] coins, int amount) {
        if(amount == 0) return 0;
        if(amount < 0) return -1;

        int means = Integer.MAX_VALUE;
        for(int i = 0; i < coins.length; i++) {
            int supProblem = coinChange(coins, amount - coins[i]);
            if(supProblem < 0) continue;
            means = Math.min(means, supProblem + 1);
        }
        return means == Integer.MAX_VALUE ? -1 : means;
    }

    // TODO: 递归
    public int coinChange2(int[] coins, int amount) {
        if(coins == null || coins.length == 0) return -1;
        int[] searched = new int[amount+1];
        return subChange(coins, amount, searched);
    }

    public int subChange(int[] coins, int amount, int[] searched) {
        if(amount == 0) return 0;
        if(amount < 0) return -1;
        if(searched[amount] != 0) return searched[amount];
        int means = Integer.MAX_VALUE;
        for(int i = 0; i < coins.length; i++) {
            int sub = subChange(coins, amount - coins[i], searched);
            if(sub < 0) continue;
            means = Math.min(sub + 1, means);
        }
        searched[amount] = means == Integer.MAX_VALUE ? -1 : means;
        return searched[amount];
    }

    // TODO：迭代
    public int coinChange(int[] coins, int amount) {
        if(coins == null || coins.length == 0) return -1;
        int[] searched = new int[amount+1];
        for(int i = 0; i <= amount; i++) searched[i] = amount+1;
        searched[0] = 0;
        for(int money = 1; money <= amount; money++) {
            for(int i = 0; i < coins.length; i++) {
                if(money < coins[i]) continue;
                searched[money] = Math.min(searched[money-coins[i]] + 1, searched[money]);
            }
        }
        if(searched[amount] > amount) searched[amount] = -1;
        return searched[amount];
    }
}
