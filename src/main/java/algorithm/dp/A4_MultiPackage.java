package algorithm.dp;

import java.util.Scanner;

/**
 * 有 N 种物品和一个容量是 V 的背包。
 * 第 i 种物品最多有 si 件，每件体积是 vi，价值是 wi。
 * 求解将哪些物品装入背包，可使物品体积总和不超过背包容量，且价值总和最大。
 * 输出最大价值。
 *
 * 输入格式
 * 第一行两个整数，N，V，用空格隔开，分别表示物品种数和背包容积。
 * 接下来有 N 行，每行三个整数 vi,wi,si，用空格隔开，分别表示第 i 种物品的体积、价值和数量。
 *
 * 输出格式
 * 输出一个整数，表示最大价值。
 *
 * 数据范围
 * 0<N,V≤100
 * 0<vi,wi,si≤100
 * 输入样例
 * 4 5
 * 1 2 3
 * 2 4 1
 * 3 4 3
 * 4 5 2
 * 输出样例：
 * 10
 *
 * https://www.acwing.com/problem/content/4/
 */
public class A4_MultiPackage {
    static int N, V;
    static int[] v, w, s;

    public static int getMaxWeight1() {
        int[][] f = new int[N + 1][V + 1];

        // TODO: f[i, j] = max( f[i-1, j], f[i-1, j-v] + w, f[i-1, j-2v] + 2w, ..., f[i-1, j - sv] + sw)
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= V; j++) {
                for(int k = 0; k <= s[i] && k * v[i] <= j; k++) {
                    f[i][j] = Math.max(f[i][j], f[i-1][j - k * v[i]] + k * w[i]);
                }
            }
        }
        return f[N][V];
    }

    // TODO: 尝试性的进行二维转一维的操作
    //  1 因 f[i, j]     = max( f[i-1, j], f[i-1, j-v] + w, f[i-1, j-2v] + 2w, ..., f[i-1, j - sv] + sw)
    //  2 则 f[i, j-v]   = max(            f[i-1, j-v],     f[i-1, j-2v] + w,  ..., f[i-1, j - (s+1)v] + sw)
    //      一定要注意 公式最后这里 和完全背包不一样，完全背包是可以一直往里放，而多重背包是有限个
    //      即完全背包的s是可以最大往其中放s个，多重背包是只能有s[i]个，可以放多余s个
    //  3 故 f[i, j-v]+w = max(            f[i-1, j-v] + w, f[i-1, j-2v] + 2w, ..., f[i-1, j - sv] + sw, f[i-1, j - (s+1)v] + (s+1)w)
    //  4 即 f[i, j]    != max( f[i-1, j], f[i, j-v]+w)
    //  5 因 max操作不能做减法，所以多了的一项f[i-1, j - (s+1)v] + (s+1)w 去不掉
    public static int getMaxWeight() {
        return -1;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        V = in.nextInt();
        v = new int[N + 1]; s = new int[N + 1]; w = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            v[i] = in.nextInt();
            w[i] = in.nextInt();
            s[i] = in.nextInt();
        }

        int res = getMaxWeight();
        System.out.println(res);
    }
}
