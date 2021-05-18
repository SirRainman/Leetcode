package algorithm.dp;

import java.util.Scanner;

/**
 * @program: Leetcode
 * @description:
 * 设有 N×N 的方格图，我们在其中的某些方格中填入正整数，而其它的方格中则放入数字0。如下图所示：
 * 某人从图中的左上角 A 出发，可以向下行走，也可以向右行走，直到到达右下角的 B 点。
 * 在走过的路上，他可以取走方格中的数（取走后的方格中将变为数字0）。
 * 此人从 A 点到 B 点共走了两次，试找出两条这样的路径，使得取得的数字和为最大。
 *
 * https://www.acwing.com/problem/content/1029/
 * @author: Rain
 * @create: 2021-04-12 15:54
 **/
public class A1027_SelectNumberInMatrix {
    static int n;
    static int[][] grid;

    // TODO: 分两次走，并不能保证最后得到的数是最大的，必须两个一起走
    private static int cal() {
        int[][][][] dp = new int[n + 1][n + 1][n + 1][n + 1];
        for (int i1 = 1; i1 <= n; i1++) {
            for(int j1 = 1; j1 <= n; j1++) {
                for(int i2 = 1; i2 <= n; i2++) {
                    for(int j2 = 1; j2 <= n; j2++) {
                        int x = dp[i1][j1][i2][j2];
                        if(i1 == i2 && j1 == j2) {
                            x = Math.max(x, dp[i1 - 1][j1][i2 - 1][j2] + grid[i1][j1]);
                            x = Math.max(x, dp[i1 - 1][j1][i2][j2 - 1] + grid[i1][j1]);
                            x = Math.max(x, dp[i1][j1 - 1][i2 - 1][j2] + grid[i1][j1]);
                            x = Math.max(x, dp[i1][j1 - 1][i2][j2 - 1] + grid[i1][j1]);
                        } else {
                            x = Math.max(x, dp[i1 - 1][j1][i2 - 1][j2] + grid[i1][j1] + grid[i2][j2]);
                            x = Math.max(x, dp[i1 - 1][j1][i2][j2 - 1] + grid[i1][j1] + grid[i2][j2]);
                            x = Math.max(x, dp[i1][j1 - 1][i2 - 1][j2] + grid[i1][j1] + grid[i2][j2]);
                            x = Math.max(x, dp[i1][j1 - 1][i2][j2 - 1] + grid[i1][j1] + grid[i2][j2]);
                        }
                        dp[i1][j1][i2][j2] = x;
                    }
                }
            }
        }

        return dp[n][n][n][n];
    }

    private static int cal2() {
        int[][][] dp = new int[n + n + 1][n + 1][n + 1];
        for(int k = 2; k <= n + n; ++k) {
            for(int i1 = 1; i1 <= n; ++i1) {
                for(int i2 = 1; i2 <= n; ++i2){
                    int j1 = k - i1, j2 = k - i2;
                    if(j1 >= 1 && j1 <= n && j2 >= 1 && j2 <= n){
                        int t = grid[i1][j1];
                        if(i1 != i2) t += grid[i2][j2];
                        int x = dp[k][i1][i2];
                        x = Math.max(x, dp[k - 1][i1 - 1][i2 - 1] + t);
                        x = Math.max(x, dp[k - 1][i1][i2 - 1] + t);
                        x = Math.max(x, dp[k - 1][i1 - 1][i2] + t);
                        x = Math.max(x, dp[k - 1][i1][i2] + t);
                        dp[k][i1][i2] = x;
                    }
                }
            }
        }
        return dp[n + n][n][n];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        grid = new int[n + 1][n + 1];
        while(true) {
            int i = in.nextInt(), j = in.nextInt(), w = in.nextInt();
            if(i == 0 && j == 0 && w == 0) break;
            grid[i][j] = w;
        }
        System.out.println(cal());
    }
}
