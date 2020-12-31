package algorithm.dp.test;


/**
 * 假设你正在爬楼梯。需要 n阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 *
 * 示例 2：
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * */
public class Q70_ClimbingStairs {
    // TODO:
    //  f(x)=f(x−1)+f(x−2)
    //  时间复杂度太高了
    public int climbStairs(int n) {
        if (n <= 1) return n;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    // TODO:
    //  空间复杂度太高了
    public int climbStairs2(int n) {
        if (n == 1) return 1;
        int[] stairs = new int[n + 1];
        stairs[1] = 1;
        stairs[2] = 2;
        for (int i = 3; i <= n; i++) {
            stairs[i] = stairs[i - 1] + stairs[i - 2];
        }
        return stairs[n];
    }

    // TODO:
    //  空间优化
    public int climbStairs3(int n) {
        // dp[i] = dp[i - 2] + dp[i - 1]
        if(n == 0 || n == 1) return 1;
        int M = (int)1e9 + 7;
        int res = 0;
        int oneFwd = 1, twoFwd = 1;
        for(int i = 2; i <= n; i++) {
            res = (int)( (((long) oneFwd + twoFwd)) % M);
            twoFwd = oneFwd;
            oneFwd = res;
        }
        return res;
    }

    //TODO: 矩阵快速幂

    //TODO: 通项公式

}
