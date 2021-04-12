package algorithm.dp.test;

import java.util.Arrays;

/**
 * @program: Leetcode
 * @description:
 * 给定正整数n，找到若干个完全平方数（比如1, 4, 9, 16, ...）使得它们的和等于 n。
 * 你需要让组成和的完全平方数的个数最少。
 * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
 * 完全平方数 是一个整数，其值等于另一个整数的平方；
 * 换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 *
 * 示例1：
 * 输入：n = 12
 * 输出：3 
 * 解释：12 = 4 + 4 + 4
 *
 * 示例 2：
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 * 
 * 提示：
 * 1 <= n <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/perfect-squares
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-04-01 20:59
 **/
public class HardQ279_PerfectSquares {
    // TODO: numSquares(n)=min(numSquares(n-k) + 1)∀k∈square numbers
    //  dp[i] 表示组成i的完全平方数的最小个数
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        int max_square_index = (int)Math.sqrt(n) + 1;
        // 计算完全平方数
        int[] squareNums = new int[max_square_index];
        for(int i = 0; i < max_square_index; i++) {
            squareNums[i] = i * i;
        }

        for(int i = 1; i <= n; i++) {
            for(int s = 1; s < max_square_index; s++) {
                if(i < squareNums[s]) break;
                dp[i] = Math.min(dp[i], dp[i - squareNums[s]] + 1);
            }
        }
        return dp[n];
    }
}
