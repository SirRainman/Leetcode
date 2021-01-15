package algorithm.math.test;

/**
 * 实现pow(x, n)，即计算 x 的 n 次幂函数。
 *
 * 示例 1:
 *
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例2:
 *
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例3:
 *
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 * 说明:
 *
 * -100.0 <x< 100.0
 * n是 32 位有符号整数，其数值范围是[−231,231− 1] 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/powx-n
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q50_QuickPow {
    public double myPow1(double x, int n) {
        double res = 1;
        // TODO:int的取值范围为（-2147483648~2147483647）
        //  Math.abs(-2147483648) = 0
        long b = Math.abs((long)n);
        while(b > 0) {
            if((b & 1) > 0) res = res * x;
            x *= x;
            b = b >> 1;
        }
        return n > 0 ? res : 1 / res;
    }

    public double myPow(double x, int n) {
        if(n == 0) return 1;
        if(n == -1) return 1 / x;
        double res = myPow(x, n >> 1);
        if((n & 1) == 1) return res * res * x;
        else return res * res;
    }
}
