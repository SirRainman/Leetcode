package algorithm.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定一张 n 个点的带权无向图，点从 0~n-1 标号，求起点 0 到终点 n-1 的最短Hamilton路径。
 * Hamilton路径的定义是从 0 到 n-1 不重不漏地经过每个点恰好一次。
 *
 * 输入格式
 * 第一行输入整数n。
 * 接下来n行每行n个整数，其中第i行第j个整数表示点i到j的距离（记为a[i,j]）。
 * 对于任意的x,y,z，数据保证 a[x,x]=0，a[x,y]=a[y,x] 并且 a[x,y]+a[y,z]>=a[x,z]。
 *
 * 输出格式
 * 输出一个整数，表示最短Hamilton路径的长度。
 *
 * 数据范围
 * 1≤n≤20
 * 0≤a[i,j]≤107
 * 输入样例：
 * 5
 * 0 2 4 5 1
 * 2 0 6 5 3
 * 4 6 0 8 3
 * 5 5 8 0 5
 * 1 3 3 5 0
 * 输出样例：
 * 18
 *
 * https://www.acwing.com/problem/content/93/
 */
public class A91_ShortestHamiltonPath {
    static int INF = 0x3f3f3f3f;
    static int n;
    static int[][] g;

    // TODO:
    //  1 集合划分：dp[i][j]表示当前已经走过点的集合为i，移动到j的路径和。
    //  2 属性：
    //  3 状态计算：
    //      dp[i][j] = min{ dp[i][j], dp[i - (1 << j)][k] + map[k][j]}
    //      找一个倒数第二个点k，将已经走过点的集合i中去除掉j（表示j不在经过的点的集合中），然后再加上从k到j的权值
    public static int getHamiltonPath() {
        int[][] dp = new int[1 << n][n];
        for(int[] d : dp) Arrays.fill(d, INF);

        dp[1][0] = 0; // 第一个点走到自己的路径和为0
        for(int i = 1; i < 1 << n; i++) { // 遍历每一个走的方案
            for(int j = 0; j < n; j++) { // 进过i方案中的点，到达j点的Hamilton的路径和
                if((i >> j & 1) == 1) { // 假如可以走到j点
                    for(int k = 0; k < n; k++) {
                        if((i >> k & 1) == 1) { // 从[0, n - 1]中找到可以到达j的路径中，倒数第二个点k
                            dp[i][j] = Math.min(dp[i][j], dp[i - (1 << j)][k] + g[k][j]); // 从方案i中去掉终点j，即通过方案i中的其他点走到k后，再从k走到j
                        }
                    }
                }
            }
        }

        return dp[(1 << n) - 1][n - 1];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        g = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                g[i][j] = in.nextInt();
            }
        }
        System.out.print(getHamiltonPath());
    }
}
