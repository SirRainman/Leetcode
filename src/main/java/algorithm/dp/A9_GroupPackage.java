package algorithm.dp;

import java.util.Scanner;

/**
 * 有 N 组物品和一个容量是 V 的背包。
 * 每组物品有若干个，同一组内的物品最多只能选一个。
 * 每件物品的体积是 vij，价值是 wij，其中 i 是组号，j 是组内编号。
 * 求解将哪些物品装入背包，可使物品总体积不超过背包容量，且总价值最大。
 *
 * 输出最大价值。
 *
 * 输入格式
 * 第一行有两个整数 N，V，用空格隔开，分别表示物品组数和背包容量。
 * 接下来有 N 组数据：
 * 每组数据第一行有一个整数 Si，表示第 i 个物品组的物品数量；
 * 每组数据接下来有 Si 行，每行有两个整数 vij,wij，用空格隔开，分别表示第 i 个物品组的第 j 个物品的体积和价值；
 * 输出格式
 * 输出一个整数，表示最大价值。
 *
 * 数据范围
 * 0<N,V≤100
 * 0<Si≤100
 * 0<vij,wij≤100
 * 输入样例
 * 3 5
 * 2
 * 1 2
 * 2 4
 * 1
 * 3 4
 * 1
 * 4 5
 * 输出样例：
 * 8
 *
 * https://www.acwing.com/problem/content/9/
 */
public class A9_GroupPackage {
    static int MAX_SIZE = 110;
    static int N, V;
    static int[][] w, v; //v为体积，w为重量
    static int[] s; // s代表第i组物品的个数

    public static int getMaxWeight1() {
        int[][] f = new int[N + 1][V + 1]; //只从前i组物品中选，当前体积小于等于j的最大值
        // TODO: f[i][j] = max(f[i-1][j], f[i-1][j - v[i][k]] + w[i][k])
        for(int i = 1; i <= N; i++) {
            for (int j = 1; j <= V; j++) {
                f[i][j] = f[i - 1][j]; //不选
                for (int k = 1; k <= s[i]; k++) {
                    if (v[i][k] <= j) {
                        f[i][j] = Math.max(f[i][j], f[i - 1][j - v[i][k]] + w[i][k]);
                    }
                }
            }
        }

        return f[N][V];
    }


    // TODO: 想一想是怎么从2维变成一维的？
    public static int getMaxWeight() {
        int[] f = new int[V + 1];
        for(int i = 1; i <= N; i++) {
            for(int j = V; j >= 1; j--) {
                for(int k = 1; k <= s[i]; k++) {
                    if(v[i][k] <= j) {
                        f[j] = Math.max(f[j], f[j - v[i][k]] + w[i][k]);
                    }
                }
            }
        }

        return f[V];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        V = in.nextInt();
        v = new int[N + 1][MAX_SIZE];
        w = new int[N + 1][MAX_SIZE];
        s = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            s[i] = in.nextInt();
            for(int j = 1; j <= s[i]; j++) {
                v[i][j] = in.nextInt();
                w[i][j] = in.nextInt();
            }
        }

        System.out.print(getMaxWeight());
    }
}
