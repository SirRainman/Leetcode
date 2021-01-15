package algorithm.math;

import java.util.Scanner;

/**
 * 给定n个正整数ai，请你求出每个数的欧拉函数。
 * https://www.acwing.com/problem/content/875/
 */
public class A873_EulerFunction {
    // TODO: 注意看欧拉函数的证明
    public static int getEuler(int x) {
        int euler = x;
        for(int i = 2; i <= x / i; i ++) {
            if(x % i == 0) {
                // TODO:注意是先除后乘，避免溢出
                euler = euler / i * (i - 1);
                while(x % i == 0) x /= i;
            }
        }
        if(x > 1) euler = euler / x * (x - 1);
        return euler;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        while(n-- > 0) {
            int x = in.nextInt();
            System.out.println(getEuler(x));
        }
    }
}
