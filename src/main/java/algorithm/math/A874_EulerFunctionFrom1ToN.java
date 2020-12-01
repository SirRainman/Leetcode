package algorithm.math;

import java.util.Scanner;

/**
 * 给定一个正整数n，求1~n中每个数的欧拉函数之和。
 * https://www.acwing.com/problem/content/876/
 */
public class A874_EulerFunctionFrom1ToN {
    public static long getSumEuler(int n ) {
        int[] euler = new int[n + 1];

        int count = 0;
        int[] primes = new int[n + 1];
        boolean[] st = new boolean[n + 1];

        euler[1] = 1;
        long res = euler[1];
        for(int i = 2; i <= n; i++) {
            if(!st[i]) {
                euler[i] = i - 1;
                primes[count++] = i;
            }

            for(int j = 0; primes[j] <= n / i; j++) {
                int t = primes[j] * i;
                st[t] = true;
                if(i % primes[j] == 0) { // TODO: 说明p是i的最小质因子，欧拉函数和质因子的指数无关，即e[p * i] = p * e[i]
                    euler[t] = primes[j] * euler[i];
                    break;
                }
                euler[t] = euler[i] * (primes[j] - 1); // TODO: 说明p 和 i 互质，即e[p * i] = e[i] * e[p - 1] = e[i] * (p - 1)
            }

            res += euler[i];
        }

        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long res = getSumEuler(n);
        System.out.print(res);
    }
}
