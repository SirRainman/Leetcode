package algorithm.math;

import java.util.Scanner;

/**
 * 给定n个0和n个1，它们将按照某种顺序排成长度为2n的序列，
 * 求它们能排列成的所有序列中，能够满足任意前缀序列中0的个数都不少于1的个数的序列有多少个。
 * 输出的答案对109+7取模。
 *
 * 输入格式
 * 共一行，包含整数n。
 *
 * 输出格式
 * 共一行，包含一个整数，表示答案。
 *
 * 数据范围
 * 1≤n≤105
 * 输入样例：
 * 3
 * 输出样例：
 * 5
 *
 * https://www.acwing.com/problem/content/891/
 */
public class A889_Catalan {

    static int MOD = (int) 1e9 + 7;

    public static int qmi(int a, int b, int p) {
        int res = 1;
        while(b > 0) {
            if((b & 1) > 0) res = (int) ((long) res * a % p);
            a = (int) ((long) a * a % p);
            b = b >> 1;
        }
        return res;
    }

    // TODO: Catalan(n) = C(2n, n) - C(2n, n - 1) = C(2n, n) / (n + 1)
    //  1 卡特兰数的推导一定要记住
    //  2 再记住他的其他的应用场景
    public static int catalan(int n) {
        int a = 2 * n, b = n;
        int res = 1;
        for(int i = 1, j = a; i <= b; i++, j--) {
            res = (int) ((long) res * j % MOD);
            res = (int) ((long) res * qmi(i, MOD - 2, MOD) % MOD);
        }
        res = (int) ((long) res * qmi(n + 1, MOD - 2, MOD) % MOD);
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int ans = catalan(n);
        System.out.println(ans);
    }

}
