package algorithm.dp;

import java.util.Scanner;

/**
 * 有 N 种物品和一个容量是 V 的背包。
 *
 * 第 i 种物品最多有 si 件，每件体积是 vi，价值是 wi。
 *
 * 求解将哪些物品装入背包，可使物品体积总和不超过背包容量，且价值总和最大。
 * 输出最大价值。
 *
 * 输入格式
 * 第一行两个整数，N，V，用空格隔开，分别表示物品种数和背包容积。
 *
 * 接下来有 N 行，每行三个整数 vi,wi,si，用空格隔开，分别表示第 i 种物品的体积、价值和数量。
 *
 * 输出格式
 * 输出一个整数，表示最大价值。
 *
 * 数据范围
 * 0<N≤1000
 * 0<V≤2000
 * 0<vi,wi,si≤2000
 * 提示：
 * 本题考查多重背包的二进制优化方法。
 *
 * 输入样例
 * 4 5
 * 1 2 3
 * 2 4 1
 * 3 4 3
 * 4 5 2
 * 输出样例：
 * 10
 *
 * https://www.acwing.com/problem/content/5/
 */
public class A5_MultiPackage2 {
    static int MAX_SIZE = 12000;
    static int N, V;
    static int[] v, w;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        V = in.nextInt();
        v = new int[MAX_SIZE]; w = new int[MAX_SIZE];
        int count = 0;
        for(int i = 1; i <= N; i++) {
            int vi = in.nextInt(), wi = in.nextInt(), si = in.nextInt();
            int k = 1;
            while(si >= k) {
                count++;
                v[count] = k * vi;
                w[count] = k * wi;
                si -= k;
                k = k << 1;
            }
            if(si > 0) {
                count++;
                v[count] = si * vi;
                w[count] = si * wi;
            }
        }

        // TODO: 想一想为什么就转化为了01背包问题？？？
        int[] f = new int[V + 1];
        for(int i = 1; i <= count; i++) {
            for(int j = V; j >= v[i]; j--) {
                f[j] = Math.max(f[j], f[j - v[i]] + w[i]);
            }
        }
        System.out.println(f[V]);
    }
}
