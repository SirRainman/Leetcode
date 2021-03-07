package algorithm.dp;

/**
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。
 * 请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 * 示例 1：
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 *
 * 示例2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 ×3 ×4 = 36
 *
 * 提示：
 * 2 <= n <= 58
 * 注意：本题与主站 343 题相同：https://leetcode-cn.com/problems/integer-break/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jian-sheng-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class QOffer14_CutRope1 {
    // TODO:
    //  1 状态集合划分：dp[i] 长度为i的绳子，剪成若干段的小绳子后的乘积
    //  2 属性：max
    //  3 状态计算
    //      不剪 dp[i]
    //      剪两段 j * (i - j)
    //      剪多段 j * dp[i - j]
    //      dp[i] = max(dp[i], max(j * (i - j), j * dp[i - j]))
    public int cuttingRope1(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for(int i = 3; i <= n; i++) {
            // TODO: 想一想为什么是 j <= i - j?
            //  因为划分两部分后 (1, i - 1) 与 (i - 1, 1)是等价的
            for(int j = 1; j <= i - j; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }

    public int cuttingRope(int n) {
        if(n <= 3) return n - 1;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for(int i = 4; i <= n; i++) {
            int maxValue = 0;
            for(int j = 1; j <= i - j; j++) {
                maxValue = Math.max(maxValue, dp[j] * dp[i - j]);
            }
            dp[i] = maxValue;
        }
        return dp[n];
    }

    // TODO：数学方法

    // TODO：贪心
}
