package algorithm.math;

import java.util.Scanner;

/**
 * 给定n组询问，每组询问给定两个整数a，b，请你输出Cba mod (109+7)的值。
 *
 * 输入格式
 * 第一行包含整数n。
 *
 * 接下来n行，每行包含一组a和b。
 *
 * 输出格式
 * 共n行，每行输出一个询问的解。
 *
 * 数据范围
 * 1≤n≤10000,
 * 1≤b≤a≤105
 *
 *
 * 输入样例：
 * 3
 * 3 1
 * 5 3
 * 2 2
 * 输出样例：
 * 3
 * 10
 * 1
 *
 * https://www.acwing.com/problem/content/888/
 */
public class A886_Combination2_InverseModular {
    static int MOD = (int ) (1e9 + 7);
    static int N = 100010;
    static int[] factorial, inFact;


    public static int qmi(int a, int b) {
        int res = 1;
        while(b > 0) {
            if((b & 1) > 0) res = (int)( (long) res * a % MOD);
            a = (int)( (long) a *  a % MOD) ;
            b = b >> 1;
        }
        return res;
    }

    public static void combination() {
        factorial = new int[N];
        inFact = new int[N];
        factorial[0] = inFact[0] = 1;
        for(int i = 1; i < N; i++) {
            // TODO:注意a b的取值范围
            // TODO:(a / b) % MOD   =  a * b^-1 % MOD
            //  注意推理过程
            factorial[i] = (int)( (long) factorial[i - 1] * i % MOD);
            inFact[i] = (int) ( (long) qmi(i, MOD - 2) * inFact[i - 1] % MOD);
        }
    }

    public static int combination(int a, int b) {
        int res = 1;
        // TODO:注意看组合公式，这个地方有分子分母约分
        for(int i = 1, j = a; i <= b; i++, j--) {
            res = (int) ((long) res * j % MOD);
            res = (int) ((long) res * qmi(i, MOD - 2) % MOD);
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        combination();

        int n = in.nextInt();
        while(n-- > 0) {
            int a = in.nextInt(), b = in.nextInt();
            int c = (int)( (long) factorial[a] * inFact[a - b] % MOD * inFact[b] % MOD);
            System.out.println(c);
        }
    }
}
