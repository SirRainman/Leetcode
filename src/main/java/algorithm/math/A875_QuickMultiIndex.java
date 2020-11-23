package algorithm.math;

import java.util.Scanner;

/**
 * 给定n组ai,bi,pi，对于每组数据，求出abii mod pi的值。
 *
 * 输入格式
 * 第一行包含整数n。
 *
 * 接下来n行，每行包含三个整数ai,bi,pi。
 *
 * 输出格式
 * 对于每组数据，输出一个结果，表示abii mod pi的值。
 *
 * 每个结果占一行。
 *
 * 数据范围
 * 1≤n≤100000,
 * 1≤ai,bi,pi≤2∗109
 *
 * 输入样例：
 * 2
 * 3 2 5
 * 4 3 9
 * 输出样例：
 * 4
 * 1
 * https://www.acwing.com/problem/content/877/
 */
public class A875_QuickMultiIndex {

    public static long qmi(long a, long b, long p) {
        if(b == 0) return 1;
        a = a % p;
        long res = qmi(a, b >> 1, p);
        if((b & 1) == 1) return (res * res % p) * a % p;
        else return res * res % p;
    }

    public static long qmi2(int a, int b, int p) {
        long res = 1;
        while(b > 0) {
            if((b & 1) == 1) res = res * a % p;
            a = (int)( a * (long) a % p );
            b = b >> 1;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        while(n-- > 0) {
            int a = in.nextInt(), b = in.nextInt(), p = in.nextInt();
            long res = qmi(a, b, p);
            System.out.println(res);
        }
    }
}
