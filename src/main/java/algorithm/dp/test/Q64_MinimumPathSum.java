package algorithm.dp.test;

import java.util.Arrays;

/**
 * 给定一个包含非负整数的 mxn网格grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 *
 * 示例 1：
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 100
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q64_MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        int[][] dp = new int[row][col]; // 从起点走到[i, j]的路径总和最小的路径

        dp[0][0] = grid[0][0];
        for(int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for(int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        for(int i = 1; i < row; i++) {
            for(int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }

    public int minPathSum1(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        int[][] dp = new int[row + 1][col + 1]; // 从起点走到[i, j]的路径总和最小的路径

        for(int i = 1; i <= row; i++) {
            for(int j = 1; j <= col; j++) {
                if(i == 1 && j == 1) dp[i][j] = grid[i - 1][j - 1];
                else {
                    if(i == 1) dp[i][j] = dp[i][j - 1] + grid[i - 1][j - 1];
                    else if(j == 1) dp[i][j] = dp[i - 1][j] + grid[i - 1][j - 1];
                    else dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
                }
            }
        }
        return dp[row][col];
    }

    public int minPathSum2(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        int[][] dp = new int[row + 1][col + 1]; // 从起点走到[i, j]的路径总和最小的路径
        for(int[] d : dp) Arrays.fill(d, 0x3f3f3f3f);
        // TODO: f[1][1] = min(f[0][1], f[0][1]) + a[1][1] = 0 + a[1][1] = a[1][1]
        //  这样有个好处，循环内部不用特判，代码简洁。
        dp[0][1] = 0;
        for(int i = 1; i <= row; i++) {
            for(int j = 1; j <= col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
            }
        }
        return dp[row][col];
    }
}
