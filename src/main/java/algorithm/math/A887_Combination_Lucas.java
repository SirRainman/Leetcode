package algorithm.math;

import java.util.Scanner;

/**
 * 给定n组询问，每组询问给定三个整数a,b,p，其中p是质数，请你输出Cba mod p的值。
 *
 * 输入格式
 * 第一行包含整数n。
 * 接下来n行，每行包含一组a,b,p。
 *
 * 输出格式
 * 共n行，每行输出一个询问的解。
 *
 * 数据范围
 * 1≤n≤20,
 * 1≤b≤a≤1018,
 * 1≤p≤105,
 *
 * 输入样例：
 * 3
 * 5 3 7
 * 3 1 5
 * 6 4 13
 * 输出样例：
 * 3
 * 3
 * 2
 *
 * https://www.acwing.com/problem/content/889/
 */
public class A887_Combination_Lucas {
    public static long qmi(long a, long b, long p) {
        long res = 1;
        while(b > 0) {
            if((b & 1) > 0) res = res * a % p;
            a = a * a % p;
            b = b >> 1;
        }
        return res;
    }

    public static long combination(long a, long b, long p) {
        if(b > a) return 0;
        long res = 1;
        // TODO：注意i和j的取值范围，以及组合的公式
        for(long i = 1, j = a; i <= b; i++, j--) {
            res = res * j % p;
            res = res * qmi(i, p - 2, p) % p;
        }
        return res;
    }

    public static long lucas(long a, long b, long p) {
        if(a < p && b < p) return combination(a, b, p);
        // TODO:注意卢卡斯定理的推理
        return combination(a % p, b % p, p) * lucas(a / p, b / p, p) % p;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        while(n-- > 0) {
            // TODO：注意ab的取值范围
            long a = in.nextLong(), b = in.nextLong(), p = in.nextLong();
            long c = lucas(a, b, p);
            System.out.println(c);
        }
    }
}
