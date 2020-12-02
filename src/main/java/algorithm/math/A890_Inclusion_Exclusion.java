package algorithm.math;

import java.util.Scanner;

/**
 * 给定一个整数n和m个不同的质数p1,p2,…,pm。
 * 请你求出1~n中能被p1,p2,…,pm中的至少一个数整除的整数有多少个。
 *
 * 输入格式
 * 第一行包含整数n和m。
 * 第二行包含m个质数。
 *
 * 输出格式
 * 输出一个整数，表示满足条件的整数的个数。
 *
 * 数据范围
 * 1≤m≤16,
 * 1≤n,pi≤109
 * 输入样例：
 * 10 2
 * 2 3
 * 输出样例：
 * 7
 *
 * https://www.acwing.com/problem/content/892/
 */
public class A890_Inclusion_Exclusion {

    static int n, m;
    static int[] p;

    public static int get() {
        int res  = 0;

        // m 个质数可以组成的集合的个数是2^m - 1
        for(int i = 1; i < (1 << m); i++ ) {
            // t表示当前集合的所有质数的乘积
            // size表示当前集合的元素的个数，可以用来判断符号（正负交替）
            int t = 1, size = 0;
            for(int j = 0; j < m; j++) {
                // m个元素可以构造2^m−1种不同的非空集合，集合包含的元素可用m位二进制表示，例如5=101b，表示集合有第1个元素和第3个元素
                if((i >> j & 1) == 1) { // 当前集合包含了 第j个元素
                    if((long) t * p[j] > n) {  // 构造的质数乘积比n大，舍弃
                        t = -1;
                        break;
                    }
                    t = t * p[j];
                    size++;
                }
            }

            if(t == -1) continue;
            // 根据当前集合质数的个数的奇偶性实现正负交替
            // 1 ~ n中能被pp整除的数的个数为⌊n / p⌋
            if((size & 1) == 1) res += n / t;
            else res -= n / t;
        }

        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        p = new int[m];
        for(int i = 0; i < m; i++) {
            p[i] = in.nextInt();
        }

        int res = get();
        System.out.print(res);
    }
}
