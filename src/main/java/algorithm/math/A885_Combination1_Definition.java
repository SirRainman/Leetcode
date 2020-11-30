package algorithm.math;

import java.util.Scanner;

/**
 * 给定n组询问，每组询问给定两个整数a，b，请你输出Cba mod (109+7)的值。
 *
 * 输入格式
 * 第一行包含整数n。
 * 接下来n行，每行包含一组a和b。
 *
 * 输出格式
 * 共n行，每行输出一个询问的解。
 *
 * 数据范围
 * 1≤n≤10000,
 * 1≤b≤a≤2000
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
 * https://www.acwing.com/problem/content/887/
 */
public class A885_Combination1_Definition {
    static int MOD = (int)(1e9 + 7);
    static int N = 2010;
    static int[][] c;

    public static void combination() {

        for(int i = 0; i < N; i++) {
            for(int j = 0; j <= i; j++) {
                if(j == 0) c[i][j] = 1;
                // TODO:从组合的性质出发
                // TODO:注意a b的取值范围
                else c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % MOD;
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        c = new int[N][N];

        combination();

        while(n-- > 0) {
            int a = in.nextInt(), b = in.nextInt();
            System.out.println(c[a][b]);
        }
    }
}
