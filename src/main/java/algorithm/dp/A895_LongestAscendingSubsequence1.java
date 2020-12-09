package algorithm.dp;

import java.util.Scanner;

/**
 * 给定一个长度为N的数列，求数值严格单调递增的子序列的长度最长是多少。
 *
 * 输入格式
 * 第一行包含整数N。
 *
 * 第二行包含N个整数，表示完整序列。
 *
 * 输出格式
 * 输出一个整数，表示最大长度。
 *
 * 数据范围
 * 1≤N≤1000，
 * −109≤数列中的数≤109
 * 输入样例：
 * 7
 * 3 1 2 1 8 5 6
 * 输出样例：
 * 4
 *
 * https://www.acwing.com/problem/content/897/
 */
public class A895_LongestAscendingSubsequence1 {
    static int n;
    static int[] a;
    static int[] history; // TODO:想一想怎么记住状态转移？？

    // 1 朴素法
    public static int getLongestSubSet() {
        int res = 0;
        int index = 1;
        int[] dp = new int[n + 1]; // dp[n]表示的是以a[n]为终点的上升序列的长度的最大值
        // 求以a[i]为终点的上升序列中，序列元素个数的最大值，i∈[1,n]
        for(int i = 1; i <= n; i++) {
            dp[i] = 1; // 初始化dp[i]为1，因为{a[i]}也是一条合法的上升序列

            history[i] = 0; // TODO:想一想怎么记住状态转移？？

            for(int j = 1; j < i; j++) { // 状态计算（考虑前i-1个元素）
                if(a[i] > a[j]) { // 转移条件（升序条件）
                    // TODO: 状态转移方程
                    //  dp[i]=max(dp[j])+1,其中0≤j<i且num[j]<num[i]
                    dp[i] = Math.max(dp[i], dp[j] + 1); // 状态转移

                    if(dp[i] < dp[j] + 1) { // TODO:想一想怎么记住状态转移？？
                        dp[i] = dp[j] + 1;
                        history[i] = j;
                    }

                }
            }
            // 题目求的是分别以a[1],a[2],...,a[n]为终点的上升序列的最大值
            res = Math.max(res, dp[i]);

            if(res < dp[i]) { // TODO:想一想怎么记住状态转移？？
                index = i;
            }
        }

        // TODO:想一想怎么记住状态转移？？
        for(int i = 0, len = dp[index]; i < len; i++) {
            System.out.printf("%d ", history[index]);
            index = history[index];
        }

        return res;
    }




    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        a = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
        }

        System.out.print(getLongestSubSet());
    }
}
