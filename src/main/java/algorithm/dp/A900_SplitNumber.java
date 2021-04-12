package algorithm.dp;

import java.util.Scanner;

/**
 * 一个正整数n可以表示成若干个正整数之和，形如：n=n1+n2+…+nk，其中n1≥n2≥…≥nk,k≥1。
 * 我们将这样的一种表示称为正整数n的一种划分。
 * 现在给定一个正整数n，请你求出n共有多少种不同的划分方法。
 *
 * 输入格式
 * 共一行，包含一个整数n。
 *
 * 输出格式
 * 共一行，包含一个整数，表示总划分数量。
 * 由于答案可能很大，输出结果请对109+7取模。
 *
 * 数据范围
 * 1≤n≤1000
 * 输入样例:
 * 5
 * 输出样例：
 * 7
 *
 * https://www.acwing.com/problem/content/902/
 */
public class A900_SplitNumber {
    static int n ;
    static int M = (int) (1e9 + 7);

    // TODO：注意完全背包的化简公式
    public static int getMaxSplitMethods1() {
        int[][] dp = new int[n + 1][n + 1];
        dp[0][0] = 1;
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= n; j++) {
                for(int k = 0; k * i <= j; k++) {
                    dp[i][j] += dp[i - 1][j - k * i];
                    dp[i][j] %= M;
                }
            }
        }
        return dp[n][n];
    }

    // TODO: 完全背包都可以把 k层循环 给去掉
    //  dp[i][j] = dp[i - 1][j] + dp[i - 1][j - i] + dp[i - 1][j - 2i] ... + dp[i - 1][j - k * i]
    //  dp[i][j - i] =            dp[i - 1][j - i] + dp[i - 1][j - 2i] ... + dp[i - 1][j - k * i]
    //  dp[i][j] = dp[i - 1][j] + dp[i][j - i]
    public static int getMaxSplitMethods2() {
        int[][] dp = new int[n + 1][n + 1];
        dp[0][0] = 1;
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= n; j++) {
                dp[i][j] += dp[i - 1][j];
                if(j >= i) dp[i][j] += dp[i][j - i];
                dp[i][j] %= M;
            }
        }
        return dp[n][n];
    }

    // TODO：优化到一维
    public static int getMaxSplitMethods3() {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for(int i = 1; i <= n; i++) {
            for(int j = i; j <= n; j++) {
                dp[j] = (dp[j] + dp[j - i]) % M;
            }
        }
        return dp[n];
    }

    // TODO：另外一种解法
    //  集合划分: dp[i][j] 使用j个数，j个数的和为i的方案
    //  属性：个数
    //  状态计算：
    //      j个数中的最小值是1，dp[i][j] += dp[i - 1][j - 1]
    //          使用j - 1个数凑成i - 1，然后加上1
    //      j个数中的最小值都大于1，dp[i][j] += dp[i - j][j]
    //          因为每个数都严格大于1，则使用j个数凑成i - j，然后为每个数都加上1，即总和加上j就可以凑为i
    public static int getMaxSplitMethods() {
        int[][] dp = new int[n + 1][n + 1];

        dp[1][1] = 1;
        for(int i = 2; i <= n; i++) {
            for(int j = 1; j <= i; j++) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - j][j]) % M;
            }
        }

        int res = 0;
        for(int i = 1; i <= n; i++) res =(res + dp[n][i]) % M;
        return res;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        System.out.println(getMaxSplitMethods());
    }
}
