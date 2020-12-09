package algorithm.dp;

import java.util.Scanner;

/**
 * 有 N 种物品和一个容量是 V 的背包，每种物品都有无限件可用。
 * 第 i 种物品的体积是 vi，价值是 wi。
 * 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
 * 输出最大价值。
 *
 * 输入格式
 * 第一行两个整数，N，V，用空格隔开，分别表示物品种数和背包容积。
 * 接下来有 N 行，每行两个整数 vi,wi，用空格隔开，分别表示第 i 种物品的体积和价值。
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
 * 10
 *
 * https://www.acwing.com/problem/content/description/3/
 */
public class A3_CompletePackage {

    static int N, V;
    static int[] v, w;

    public static int getMaxWeight1() {
        int[][] f = new int[N + 1][V + 1]; // f[i][j]表示在考虑前i个物品后，背包容量为j条件下的最大价值

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= V; j++) {
                for(int k = 0; k * v[i] <= j; k++) { // 选k次
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - k * v[i]] + k * w[i]);
                    // TODO: 一维优化
                    //  想一想为什么这样也对？
                    // dp[j] = Math.max(dp[j], dp[j - k * v[i]] + k * w[i]);
                }
            }
        }

        return f[N][V];
    }

    // TODO: 如何去掉k重循环？？？
    //  1 因  f[i, j]   =   max(f[i-1][j], f[i-1][j-v] + w, f[i-1][j-2v] + 2w, f[i-1][j-3v] + 3w, ..., f[i-1][j-sv] + sw )
    //  2 且  f[i, j-v] =     max(         f[i-1][j-v],     f[i-1][j-2v] + w,  f[i-1][j-3v] + 2w, ..., f[i-1][j-sv] + (s-1)w )
    //  3 则  f[i, j-v] + w=  max(         f[i-1][j-v] + w, f[i-1][j-2v] + 2w, f[i-1][j-3v] + 3w, ..., f[i-1][j-sv] + sw )
    //  4 故  f[i, j] = max(f[i-1][j], f[i, j - v] + w)
    //    !!注 一定要注意这个公式是有区别于01背包问题的!!
    //      因 当前状态与 上一层的状态 和 本层的状态有关
    //      故 在遍历的时候需要注意状态的择取问题
    public static int getMaxWeight2() {
        int[][] f = new int[N + 1][V + 1];

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= V; j++) {
                if(v[i] > j) {
                    f[i][j] = f[i - 1][j];
                } else {
                    f[i][j] = Math.max(f[i - 1][j], f[i][j - v[i]] + w[i]);
                }
            }
        }

        return f[N][V];
    }

    // TODO: 如何从二维优化到一维
    //    f[i, j] = max(f[i-1][j], f[i, j - v])
    //    不断使用前面已经更新过的 f [i][ 前面体积 ]来更新 f [i][ 后面的体积 j ]
    //    !!!!!!!!!!!!!! 注 一定要注意这个公式是有区别于01背包问题的 !!!!!!!!!!!!
    //      因 当前状态与 上一层的状态 和 本层的状态有关
    //      故 在遍历的时候需要注意状态的择取问题
    public static int getMaxWeight() {
        int[] f = new int[V + 1];

        for(int i = 1; i <= N; i++) {
            for(int j = v[i]; j <= V; j++) {
                // TODO: 一定注意j的取值范围式从小大大枚举，要和01背包问题的一维优化区分开！！！
                //  f[i][j] = max(f[i][j], f[i - 1][j- v[i]] + w[i]);   01背包
                //  f[i][j] = max(f[i][j], f[i][j- v[i]] + w[i]);       完全背包问题
                f[j] = Math.max(f[j], f[j - v[i]] + w[i]);
            }
        }

        return f[V];
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
