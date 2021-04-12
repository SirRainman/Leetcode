package algorithm.dp.test;

/**
 * @program: Leetcode
 * @description:
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-03-11 10:50
 **/
public class Q63_UniquePaths2 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid[0][0] == 1) return 0;
        int row = obstacleGrid.length, col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = 1;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(obstacleGrid[i][j] == 1) continue;
                if(i > 0) dp[i][j] += dp[i - 1][j];
                if(j > 0) dp[i][j] += dp[i][j - 1];
            }
        }
        return dp[row - 1][col - 1];
    }
}
