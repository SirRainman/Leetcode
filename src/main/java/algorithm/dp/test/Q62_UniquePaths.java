package algorithm.dp.test;

/**
 * 一个机器人位于一个 m x n网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 * 
 * 提示：
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 109
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q62_UniquePaths {

    // TODO: 动态规划
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n]; // 从[0, 0] 走到 [i, j] 有多少种不同的路径
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) { // 只能走直线
                    dp[i][j] = 1;
                    continue;
                }
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1]; // 要么从上面走过来，要么从左边走过来
            }
        }
        return dp[m - 1][n - 1];
    }

    // TODO: 组合数学
    //  从左上角到右下角的过程中，我们需要移动 m+n-2 次，其中有 m-1 次向下移动，n-1 次向右移动。
    //  因此路径的总数，就等于从 m+n-2 次移动中选择 m-1 次向下移动的方案数，即组合数
    public int uniquePaths1(int m, int n) {
        int a = m + n - 2;
        int b = Math.min(m, n) - 1;
        int[][] c = new int[a + 1][b + 1];
        for (int i = 0; i <= a; i++) {
            for (int j = 0; j <= b && j <= i; j++) {
                if (j == 0) c[i][j] = 1;
                else c[i][j] = c[i - 1][j] + c[i - 1][j - 1];
            }
        }
        return c[a][b];
    }

    // TODO: 化简
    public int uniquePaths2(int m, int n) {
        int a = m + n - 2;
        int b = Math.min(m, n) - 1;
        long c = 1;
        for (int i = 1, j = a; i <= b; i++, j--) {
            c = c * j / i;
        }
        return (int) c;
    }
}
