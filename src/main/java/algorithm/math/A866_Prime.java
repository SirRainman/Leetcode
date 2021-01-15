package algorithm.math;

import java.util.Scanner;

/**
 * 给定n个正整数ai，判定每个数是否是质数。
 *
 * https://www.acwing.com/problem/content/868/
 */
public class A866_Prime {
    public static boolean isPrime(int x) {
        if(x < 2) return false;
        for(int i = 2; i <= x / i; i++) {
            if(x % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        while(n-- > 0) {
            int x = in.nextInt();
            System.out.println(isPrime(x) ? "Yes" : "No");
        }
    }
}
