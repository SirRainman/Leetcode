package algorithm.math;

import java.util.Scanner;

/**
 * 给定n对正整数ai,bi，请你求出每对数的最大公约数。
 *
 * 输入格式
 * 第一行包含整数n。
 *
 * 接下来n行，每行包含一个整数对ai,bi。
 *
 * 输出格式
 * 输出共n行，每行输出一个整数对的最大公约数。
 *
 * 数据范围
 * 1≤n≤105,
 * 1≤ai,bi≤2∗109
 * 输入样例：
 * 2
 * 3 6
 * 4 6
 * 输出样例：
 * 3
 * 2
 *
 * https://www.acwing.com/problem/content/874/
 */
public class A872_GreatestCommonDivisor {
    // TODO：欧几里得定理 gcd(a, b) = gcd(b, a % b)
    //  时间复杂度：log n
    //  a可以表示成a = kb + r（a，b，k，r皆为正整数，且r<b），则r = a mod b
    //  充分性：
    //      1. 假设d是a,b的一个公约数，记作d|a,d|b，即a和b都可以被d整除。
    //      2. 因为r = a - kb，两边同时除以d，r/d=a/d-kb/d=m，由等式右边可知m为整数，所以 d|r
    //      3. 因此d也是b,a mod b的公约数
    //  必要性：
    //      1. 假设d是b,a mod b的公约数, 则d|b,d|(a-k*b),k是一个整数。
    //      2. 进而d|a.
    //      3. 因此d也是a,b的公约数
    //  综上：
    //      1. 因此(a,b)和(b,a mod b)的公约数是一样的，其最大公约数也必然相等，得证。

    public static int gcd(int a, int b) {
        return b > 0 ? gcd(b, a % b) : a;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        while(n-- > 0) {
            int a = in.nextInt(), b = in.nextInt();
            int g = gcd(a, b);
            System.out.println(g);
        }
    }
}
