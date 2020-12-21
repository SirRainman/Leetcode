package algorithm.dp;

import java.util.Scanner;

/**
 * 给定两个长度分别为N和M的字符串A和B，求既是A的子序列又是B的子序列的字符串长度最长是多少。
 *
 * 输入格式
 * 第一行包含两个整数N和M。
 *
 * 第二行包含一个长度为N的字符串，表示字符串A。
 *
 * 第三行包含一个长度为M的字符串，表示字符串B。
 *
 * 字符串均由小写字母构成。
 *
 * 输出格式
 * 输出一个整数，表示最大长度。
 *
 * 数据范围
 * 1≤N,M≤1000
 * 输入样例：
 * 4 5
 * acbd
 * abedc
 * 输出样例：
 * 3
 *
 * https://www.acwing.com/problem/content/899/
 */
public class A897_LongestCommonSubsequence {

    static int la, lb;
    static char[] a, b;

    // TODO:
    //  1.集合划分：0 - Ai, 0 - Bj 中所有的公共子序列
    //  2.属性：max
    //  3.状态计算
    //      当a[i] != b[j]，dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
    //      当a[i] == b[j]，dp[i][j] = dp[i - 1][j - 1] + 1,
    public static int getLongestSubSet() {
        int[][] dp = new int[la + 1][lb + 1]; // dp[i][j] 表示text1[0~i-1] 和 text2[0~j-1] 的最长公共子序列长度
        for(int i = 1; i <= la; i++) {
            for(int j = 1; j <= lb; j++) {
                if(a[i - 1] == b[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // TODO: 难点：为什么不考虑ai bj都不在子序列中的情况dp[i-1][j-1]????
                    //  设 00: 表示 a[i] b[j] 都不选，01 10 11以此类推
                    //  问：为什么 f(i−1, j) 并不是01的等价形式？
                    //  f(i−1, j) 并不是01的等价形式，但01 ⊆ f(i − 1,j) ⊆ f(i, j)，
                    //      因此maxf(i−1, j) 包含了max(01)，且剩余的部分也是属于f(i, j)的，故可用f(i−1,j)代替01。
                    //      答：f (i−1, j) 表示的是 子序列在( i-1, j )之前出现了，但并不是表示子序列的最后一个字符在第j个位置，
                    //      即f(i-1, j)是包含了b[j]在第j个位置出现的。
                    //  同理f(i,j−1)可代替10
                    //  若C ⊆ A ∩ B，则max(A, B, C) = max(A, B)。
                    //  由于f(i−1, j−1) ⊆ f(i−1, j) ∪ f(i, j−1)，故无需考虑00的情形，而只需考虑01，10和11的情况
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[la][lb];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        la = in.nextInt();
        lb = in.nextInt();
        a = in.next().toCharArray();
        b = in.next().toCharArray();

        System.out.print(getLongestSubSet());
    }


}
