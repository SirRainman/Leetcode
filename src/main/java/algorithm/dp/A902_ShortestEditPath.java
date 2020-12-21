package algorithm.dp;

import java.util.Scanner;

/**
 * 给定两个字符串A和B，现在要将A经过若干操作变为B，可进行的操作有：
 * 删除–将字符串A中的某个字符删除。
 * 插入–在字符串A的某个位置插入某个字符。
 * 替换–将字符串A中的某个字符替换为另一个字符。
 * 现在请你求出，将A变为B至少需要进行多少次操作。
 *
 * 输入格式
 * 第一行包含整数n，表示字符串A的长度。
 * 第二行包含一个长度为n的字符串A。
 * 第三行包含整数m，表示字符串B的长度。
 * 第四行包含一个长度为m的字符串B。
 * 字符串中均只包含大写字母。
 *
 * 输出格式
 * 输出一个整数，表示最少操作次数。
 *
 * 数据范围
 * 1≤n,m≤1000
 * 输入样例：
 * 10
 * AGTCTGACGC
 * 11
 * AGTAAGTAGGC
 * 输出样例：
 * 4
 *
 * https://www.acwing.com/problem/content/904/
 */
public class A902_ShortestEditPath {
    static int la, lb;
    static char[] a, b;

    // TODO:
    //  1.集合划分：dp[i][j] wa[1, i] 变成 wb[1, j] 的编辑方式的操作数
    //  2.属性：min
    //  3.状态计算：
    //      1 增加 dp[i][j] = dp[i][j - 1] + 1
    //      2 删除 dp[i][j] = dp[i - 1][j] + 1
    //      3 修改
    //          a[i] = b[j],  dp[i][j] = dp[i - 1][j - 1]
    //          a[i] != b[j], dp[i][j] = dp[i - 1][j - 1] + 1
    public static int getShortestEditPath() {
        int[][] dp = new int[la + 1][lb + 1];
        for(int i = 0; i <= la; i++) dp[i][0] = i; // a[] 删除为空串 需要操作的次数
        for(int j = 0; j <= lb; j++) dp[0][j] = j; // 空串 扩充为b[] 需要操作的次数

        for(int i = 1; i <= la; i++) {
            for(int j = 1; j <= lb; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1; // 删除or增加 第i位的最少操作数
                if(a[i - 1] == b[j - 1]) dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]); // 不修改
                else dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + 1);   // 修改
            }
        }
        return dp[la][lb];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        la = in.nextInt();
        a = in.next().toCharArray();
        lb = in.nextInt();
        b = in.next().toCharArray();
        System.out.print(getShortestEditPath());
    }
}
