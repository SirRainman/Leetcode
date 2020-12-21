package algorithm.dp;

import java.util.List;
import java.util.Scanner;

/**
 * 给定n个长度不超过10的字符串以及m次询问，每次询问给出一个字符串和一个操作次数上限。
 * 对于每次询问，请你求出给定的n个字符串中有多少个字符串可以在上限操作次数内经过操作变成询问给出的字符串。
 * 每个对字符串进行的单个字符的插入、删除或替换算作一次操作。
 *
 * 输入格式
 * 第一行包含两个整数n和m。
 * 接下来n行，每行包含一个字符串，表示给定的字符串。
 * 再接下来m行，每行包含一个字符串和一个整数，表示一次询问。
 * 字符串中只包含小写字母，且长度均不超过10。
 *
 * 输出格式
 * 输出共m行，每行输出一个整数作为结果，表示一次询问中满足条件的字符串个数。
 *
 * 数据范围
 * 1≤n,m≤1000,
 *
 * 输入样例：
 * 3 2
 * abc
 * acd
 * bcd
 * ab 1
 * acbd 2
 * 输出样例：
 * 1
 * 3
 *
 * https://www.acwing.com/problem/content/901/
 */
public class A899_EditPath {
    static int n, m;
    static char[][] words;

    public static boolean ifCanBeEdited(char[] a, char[] b, int times) {
        int la = a.length, lb = b.length;
        int[][] dp = new int[la + 1][lb + 1];
        for(int i = 0; i <= la; i++) dp[i][0] = i;
        for(int j = 0; j <= lb; j++) dp[0][j] = j;

        for(int i = 1; i <= la; i++) {
            for(int j = 1; j <= lb; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                if(a[i - 1] == b[j - 1]) dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                else dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + 1);
            }
        }
        return dp[la][lb] <= times;
    }

    // TODO: 先看A902
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();

        words = new char[n][];
        for(int i = 0; i < n; i++) words[i] = in.next().toCharArray();

        while(m-- > 0) {
            char[] query = in.next().toCharArray();
            int times = in.nextInt();

            int count = 0;
            for(char[] word : words) {
                if(ifCanBeEdited(word, query, times)) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}
