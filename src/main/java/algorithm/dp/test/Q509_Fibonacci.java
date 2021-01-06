package algorithm.dp.test;

/**
 * 斐波那契数，通常用F(n) 表示，形成的序列称为 斐波那契数列 。该数列由0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 * F(0) = 0，F(1)= 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给你 n ，请计算 F(n) 。
 *
 * 示例 1：
 * 输入：2
 * 输出：1
 * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
 *
 * 示例 2：
 * 输入：3
 * 输出：2
 * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2
 *
 * 示例 3：
 * 输入：4
 * 输出：3
 * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3
 * 
 *
 * 提示：
 * 0 <= n <= 30
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fibonacci-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q509_Fibonacci {
    // TODO: 动态规划
    public int fib(int N) {
        if(N == 1 || N == 0) return N;
        int res = 0;
        int oneFwd = 1, twoFwd = 0;
        for(int i = 2; i <= N; i++) {
            res = oneFwd + twoFwd;
            twoFwd = oneFwd;
            oneFwd = res;
        }
        return res;
    }

    // TODO：你还记得高中是怎么推通项公式的吗？？？
}
