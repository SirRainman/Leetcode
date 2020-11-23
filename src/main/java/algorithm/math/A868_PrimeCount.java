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

    // TODO：朴素筛法
    //  时间复杂度是O(n log n )
    //  1. 一个数可以标记该数组中的几个数为合数？n / x
    //  2. 调和级数：n * ( 1/2 + 1/3 + … + 1/n) = n * (ln n + c) < n * log n
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

    // TODO：埃式筛法：只用质数筛
    //  时间复杂度：O( n (log (log n) )
    //  怎么求时间复杂度？质数定理：1 - n中的n个数中，有 n / ln n 个质数
    //  核心思想：只用质数剔除合数
    //  缺点：
    //      1. 虽然用质数筛选比用合数筛选要好，可以减少每个数被筛选的次数，但是一个数被筛选的次数还是相对较多。
    //      2. 一个数有几个质因数，就会被筛选几次。
    //      3. 能不能减少一个数被筛选的次数呢？让每个合数只被他的最小质因数筛选掉
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


    // TODO: 线性筛法：只用最小质数筛
    //  时间复杂度：O(n)
    //  核心：保证每一个数都是被他的最小质因子筛选掉了
    // TODO：为什么是线性的？？？
    //  证明：
    //  1.对于任意一个合数 x <= n，他一定存在一个最小质因子。
    //  2.假设primes[j]是x的最小质因子，当i枚举到x / primes[j] 的时候，内循环中的 st [primes[j] * i ] = true 一定会把x枚举掉
    //  3.因为每个数只有一个最小质因子，所以每个数只会被筛掉一次，所以是线性的
    public static int getPrimeCount3(int n) {
        int count = 0;
        int[] primes = new int[n + 1];      // primes[]存储所有素数
        boolean[] st = new boolean[n + 1];  // st[x]存储x是否被筛掉

        for(int i = 2; i <= n; i++) {
            if(!st[i]) primes[count++] = i;
            for(int j = 0; primes[j] <= n / i; j++) {
                st[primes[j] * i] = true;
                if(i % primes[j] == 0) break;
                // TODO：用最小质因子筛选掉每一个合数
                //  1.当i % primes[j] != 0时，说明此时遍历到的primes[j]不是 i 的质因子，那么只可能是此时的primes[j] < i 的最小质因子，
                //      所以primes[j] *  i的最小质因子就是primes[j]
                //  2.当有i % primes[j] == 0时，说明i的最小质因子是primes[j]，因此primes[j] * i的最小质因子也就应该是prime[j]，
                //      之后接着用st[primes[j+1] * i] = true去筛合数时，就不是用最小质因子去更新了，因为i有最小质因子primes[j] < primes[j+1]，
                //      此时的primes[j+1]不是primes[j+1] * i的最小质因子，此时就应该退出循环，避免之后重复进行筛选
            }
        }
        return count;
    }w

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.print(getPrimeCount3(n));
    }
}
