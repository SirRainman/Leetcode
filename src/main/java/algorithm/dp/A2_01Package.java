package algorithm.dp;

import java.util.Scanner;

/**
 * 有 N 件物品和一个容量是 V 的背包。每件物品只能使用一次。
 * 第 i 件物品的体积是 vi，价值是 wi。
 * 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
 * 输出最大价值。
 *
 * 输入格式
 * 第一行两个整数，N，V，用空格隔开，分别表示物品数量和背包容积。
 * 接下来有 N 行，每行两个整数 vi,wi，用空格隔开，分别表示第 i 件物品的体积和价值。
 *
 * 输出格式
 * 输出一个整数，表示最大价值。
 *
 * 数据范围
 * 0<N,V≤1000
 * 0<vi,wi≤1000
 * 输入样例
 * 4 5
 * 1 2
 * 2 4
 * 3 4
 * 4 5
 * 输出样例：
 * 8
 *
 * https://www.acwing.com/problem/content/2/
 */
public class A2_01Package {
    static int N, V; // N:物品总数 V: 背包容量
    static int[] v, w; // v[i]: 物品体积 weight[i]：物品重量

    public static int getMaxWeight1() {
        int[][] f = new int[N + 1][V + 1]; // f[i][j]表示在考虑前i个物品后，背包容量为j条件下的最大价值

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= V; j++) {
                // TODO：可以不用if-else，为了方便理解就保留一下
                if(v[i] > j) { // 背包装不下这么大体积的物品
                    f[i][j] = f[i - 1][j]; // 价值等于前i-1个物品
                } else { // 背包可以装下该物品
                    // 要么选择该物品，要么不选
                    f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - v[i]] + w[i]);
                }
            }
        }
        return f[N][V];
    }

    public static int getMaxWeight() {
        int[] f = new int[V + 1]; // f[j]表示背包容量为j条件下的最大价值

        // TODO: 想一想二维的方法在哪里可以优化一下？
        //  因 二维的方法中，只用到了两层，一个是当前层，一个是上一层
        //  且 f[i, j] = max(f[i-1, j], f[i-1, j - v] + w)
        //  故 可以看出当前的状态只与上一层的状态是相关的，和本层状态无关
        //  即 倒着遍历j
        for(int i = 1; i <= N; i++) {
            for(int j = V; j >= v[i]; j--) { // TODO: 注意是倒序，否则出现写后读错误，注意与完全背包问题的一维优化区分开
                f[j] = Math.max(f[j], f[j - v[i]] + w[i]);
            }
        }
        return f[V]; // 注意是V不是N
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        V = in.nextInt();
        v = new int[N + 1];
        w = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            v[i] = in.nextInt();
            w[i] = in.nextInt();
        }

        int res = getMaxWeight();
        System.out.print(res);
    }
}
