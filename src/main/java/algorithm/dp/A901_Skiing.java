package algorithm.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定一个R行C列的矩阵，表示一个矩形网格滑雪场。
 *
 * 矩阵中第 i 行第 j 列的点表示滑雪场的第 i 行第 j 列区域的高度。
 *
 * 一个人从滑雪场中的某个区域内出发，每次可以向上下左右任意一个方向滑动一个单位距离。
 *
 * 当然，一个人能够滑动到某相邻区域的前提是该区域的高度低于自己目前所在区域的高度。
 *
 * 下面给出一个矩阵作为例子：
 *
 *  1  2  3  4 5
 *
 * 16 17 18 19 6
 *
 * 15 24 25 20 7
 *
 * 14 23 22 21 8
 *
 * 13 12 11 10 9
 * 在给定矩阵中，一条可行的滑行轨迹为24-17-2-1。
 *
 * 在给定矩阵中，最长的滑行轨迹为25-24-23-…-3-2-1，沿途共经过25个区域。
 *
 * 现在给定你一个二维矩阵表示滑雪场各区域的高度，请你找出在该滑雪场中能够完成的最长滑雪轨迹，并输出其长度(可经过最大区域数)。
 *
 * 输入格式
 * 第一行包含两个整数R和C。
 *
 * 接下来R行，每行包含C个整数，表示完整的二维矩阵。
 *
 * 输出格式
 * 输出一个整数，表示可完成的最长滑雪长度。
 *
 * 数据范围
 * 1≤R,C≤300,
 * 0≤矩阵中整数≤10000
 * 输入样例：
 * 5 5
 * 1 2 3 4 5
 * 16 17 18 19 6
 * 15 24 25 20 7
 * 14 23 22 21 8
 * 13 12 11 10 9
 * 输出样例：
 * 25
 *
 * https://www.acwing.com/problem/content/903/
 */
public class A901_Skiing {
    static int row, col;
    static int[][] g, dp;
    static int[] nx = new int[] {0, 0, 1, -1};
    static int[] ny = new int[] {1, -1, 0, 0};

    // TODO:
    //  1 集合划分：dp[i][j] 从[i, j]出发的可以滑的路径
    //  2 属性：max
    //  3 状态计算：该点的最大滑动长度 是 其他可以滑到的点的滑动长度+1
    //      dp[i][j] = max(dp[i][j-1], dp[i][j+1],dp[i-1][j],dp[i+1][j])
    public static int dfs(int u, int v) {
        if(dp[u][v] != -1) return dp[u][v]; // 如果该点的dp[][] 不为初始化值 那么就说明计算过 不必再次计算

        dp[u][v] = 1;
        for(int i = 0; i < 4; i++) {
            int x = u + nx[i], y = v + ny[i];
            if(x >= 0 && x < row && y >= 0 && y < col && g[u][v] > g[x][y]) {
                dp[u][v] = Math.max(dp[u][v], dfs(x, y) + 1);
            }
        }
        return dp[u][v];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        row = in.nextInt(); col = in.nextInt();
        g = new int[row][col];
        dp = new int[row][col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                g[i][j] = in.nextInt();
            }
        }

        for(int[] d : dp) Arrays.fill(d, -1);

        int res = 0;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                res = Math.max(res, dfs(i, j));
            }
        }
        System.out.print(res);
    }
}
