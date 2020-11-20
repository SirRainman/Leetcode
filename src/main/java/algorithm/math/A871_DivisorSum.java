package algorithm.math;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 给定n个正整数ai，请你输出这些数的乘积的约数之和，答案对109+7取模。
 *
 * 输入格式
 * 第一行包含整数n。
 *
 * 接下来n行，每行包含一个整数ai。
 *
 * 输出格式
 * 输出一个整数，表示所给正整数的乘积的约数之和，答案需对109+7取模。
 *
 * 数据范围
 * 1≤n≤100,
 * 1≤ai≤2∗109
 * 输入样例：
 * 3
 * 2
 * 6
 * 8
 * 输出样例：
 * 252
 *
 * https://www.acwing.com/problem/content/873/
 */
public class A871_DivisorSum {
    public static void updatePrimesDivisor(int x, Map<Integer, Integer> primes) {
        for(int i = 2; i <= x / i; i++) {
            if(x % i == 0) {
                int count = 0;
                while(x % i == 0) {
                    x /= i;
                    count++;
                }
                primes.put(i, primes.getOrDefault(i, 0) + count);
            }
        }
        if(x > 1) primes.put(x, primes.getOrDefault(x, 0) + 1);
    }

    public static long getDivisorSum(Map<Integer, Integer> primes) {
        long sum = 1;
        int M = (int )(1e9 + 7);

        for(Map.Entry<Integer, Integer> entry : primes.entrySet()) {
            int prime = entry.getKey(), count = entry.getValue();
            long t = 1;
            for(int i = 0; i < count; i++) t = (t * prime + 1) % M;
            sum = (sum * t) % M;
        }

        return sum;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Map<Integer, Integer> primes = new HashMap<>();

        int n = in.nextInt();
        while(n-- > 0) {
            int x = in.nextInt();
            updatePrimesDivisor(x, primes);
        }

        long sum = getDivisorSum(primes);
        System.out.print(sum);
    }
}
