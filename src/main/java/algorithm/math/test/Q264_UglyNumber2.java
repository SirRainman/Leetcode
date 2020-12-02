package algorithm.math.test;

/**
 * 编写一个程序，找出第 n 个丑数。
 * 丑数就是质因数只包含2, 3, 5 的正整数。
 *
 * 示例:
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 说明:
 *
 * 1是丑数。
 * n不超过1690。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ugly-number-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q264_UglyNumber2 {
    class Solution {
        // TODO: 丑数的递推性质： 丑数只包含因子 2, 3, 5 ，因此有 “丑数 == 某较小丑数 * 某因子” （例如：10 = 5 * 2）。
        //  https://leetcode-cn.com/problems/chou-shu-lcof/solution/mian-shi-ti-49-chou-shu-dong-tai-gui-hua-qing-xi-t/
        //  时间复杂度 O(N) ： 其中 N = n，动态规划需遍历计算 dp 列表。
        //  空间复杂度 O(N) ： 长度为 N 的 dp 列表使用 O(N) 的额外空间。
        public int nthUglyNumber(int n) {
            int[] dp = new int[n];
            dp[0] = 1;
            // TODO: a, b, c是三个较小整数的指针
            int a = 0, b = 0, c = 0;
            for(int i = 1; i < n; i++) {
                int n2 = dp[a] * 2, n3 = dp[b] * 3, n5 = dp[c] * 5;
                // TODO：xn+1=min(xa×2,xb×3,xc×5)
                dp[i] = Math.min(Math.min(n2, n3), n5);
                if(dp[i] == n2) a++;
                if(dp[i] == n3) b++;
                if(dp[i] == n5) c++;
            }
            return dp[n - 1];
        }
    }
}
