package algorithm.math;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 输入a,b，求C(a, b)的值。
 *
 * 注意结果可能很大，需要使用高精度计算。
 *
 * 输入格式
 * 共一行，包含两个整数a和b。
 *
 * 输出格式
 * 共一行，输出Cba的值。
 *
 * 数据范围
 * 1≤b≤a≤5000
 * 输入样例：
 * 5 3
 * 输出样例：
 * 10
 *
 * https://www.acwing.com/problem/content/description/890/
 */
public class A888_Combination_PrimeDivisor_BigNumMulti {
    static int n;

    static int count = 0;
    static int[] primes, sumIndex;
    static boolean[] st;

    // TODO:筛选质数
    public static void getPrimes(int n) {
        primes = new int[n + 1];
        st = new boolean[n + 1];
        for(int i = 2; i <= n; i++) {
            if(!st[i]) primes[count++] = i;
            for(int j = 0; primes[j] <= n / i; j++) {
                st[ primes[j] * i ] = true;
                if(i % primes[j] == 0) break;
            }
        }
    }

    // TODO：计算n中可以整除该数p的数的个数
    public static int getCount(int n, int p) {
        int count = 0;
        while(n > 0) {
            count += n / p;
            n /= p;
        }
        return count;
    }

    // TODO：大数乘法
    public static List<Integer> multi(List<Integer> A, int b) {
        List<Integer> ans = new ArrayList<>();
        int carry = 0;
        for(int i = 0; i < A.size() || carry > 0; i++) {
            if(i < A.size()) carry += A.get(i) * b;
            ans.add(carry % 10);
            carry /= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int a = in.nextInt(), b = in.nextInt();
        n = Math.max(a, b);
        getPrimes(n);

        sumIndex = new int[n + 1];
        for(int i = 0; i < count; i++) {
            // TODO：牢记组合公式
            sumIndex[i] = getCount(a, primes[i]) - getCount(b, primes[i]) - getCount(a - b, primes[i]);
        }

        List<Integer> res = new ArrayList<>();
        res.add(1);
        for(int i = 0; i < count; i++) {
            for(int j = 0; j < sumIndex[i]; j++) {
                res = multi(res, primes[i]);
            }
        }

        for(int i = res.size() - 1; i >= 0; i--) System.out.print(res.get(i));
    }
}
