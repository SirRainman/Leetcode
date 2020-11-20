package algorithm.math;

import java.util.Scanner;

/**
 * 给定一个正整数n，请你求出1~n中质数的个数。
 *
 * 输入格式
 * 共一行，包含整数n。
 *
 * 输出格式
 * 共一行，包含一个整数，表示1~n中质数的个数。
 *
 * 数据范围
 * 1≤n≤106
 * 输入样例：
 * 8
 * 输出样例：
 * 4
 *
 * https://www.acwing.com/problem/content/870/
 */
public class A868_PrimeCount {

    public static boolean isPrime(int x) {
        if(x < 2) return false;
        for(int i = 2; i <= x / i; i++) {
            if(x % i == 0) return false;
        }
        return true;
    }

    // 朴素筛法
    public static int getPrimeCount1(int n) {
        boolean[] st = new boolean[n + 1];
        int ans = 0;
        for(int i = 2; i <= n; i++) {
            if(st[i]) continue;
            if(isPrime(i)) ans++;
            for(int j = i + i; j <= n; j += i) st[j] = true;
        }
        return ans;
    }

    // 埃式筛法：只用质数筛
    public static int getPrimeCount2(int n ) {
        boolean[] st = new boolean[n + 1];
        int ans = 0;
        for(int i = 2; i <= n; i++) {
            if(!st[i]) {
                ans++;
                for(int j = i + i; j <= n; j += i) st[j] = true;
            }
        }
        return ans;
    }


    // 线性筛法：只用最小质数筛
    public static int getPrimeCount3(int n) {
        boolean[] st = new boolean[n + 1];
        int count = 0;
        int[] primes = new int[n + 1];

        for(int i = 2; i <= n; i++) {
            if(!st[i]) primes[count++] = i;
            for(int j = 0; primes[j] <= n / i; j++) {
                st[primes[j] * i] = true;
                if(i % primes[j] == 0) break;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.print(getPrimeCount3(n));
    }
}
