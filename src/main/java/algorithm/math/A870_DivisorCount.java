package algorithm.math;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 给定n个正整数ai，请你输出这些数的乘积的约数个数，答案对109+7取模。
 *
 * 输入格式
 * 第一行包含整数n。
 * 接下来n行，每行包含一个整数ai。
 *
 * 输出格式
 * 输出一个整数，表示所给正整数的乘积的约数个数，答案需对109+7取模。
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
 * 12
 *
 * https://www.acwing.com/problem/content/872/
 */
public class A870_DivisorCount {
    // TODO: 分解质因数，和他的幂
    public static void updatePrimesDivisor(int x, Map<Integer, Integer> primes) {
        for(int i = 2; i <= x / i; i++) {
            if(x % i == 0) {
                int index = 0;
                while(x % i == 0) {
                    index ++;
                    x /= i;
                }
                primes.put(i, primes.getOrDefault(i, 0) + index);
            }
        }
        if(x > 1) primes.put(x, primes.getOrDefault(x, 0) + 1);
    }

    // TODO: 约数和公式
    //  n = p1^a1 + p2^a2 + ... + pk^ak
    //  由约数定义可知 p1^a1 的约数有: p1^0, p1^1, p1^2 ... p1^a1 ，共（a1+1）个;
    //  同理p2^a2的约数有（a2+1）个
    //  ...
    //  pk^ak的约数有（ak+1）个。
    //  故根据乘法原理：n的约数的个数就是(a1+1)(a2+1)(a3+1)…(ak+1)。
    public static long getDivisorCount(Map<Integer, Integer> primes) {
        long count = 1;
        int M = (int ) (1e9 + 7);
        for(Map.Entry<Integer, Integer> entry : primes.entrySet()) {
            int prime = entry.getKey(), index = entry.getValue();
            // TODO:注意求约数的公式
            count = count * (index + 1) % M;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Map<Integer, Integer> primes = new HashMap<>();
        int n = in.nextInt();
        while(n-- > 0) {
            int x = in.nextInt();
            updatePrimesDivisor(x, primes);
        }

        long count = getDivisorCount(primes);
        System.out.print(count);
    }
}
