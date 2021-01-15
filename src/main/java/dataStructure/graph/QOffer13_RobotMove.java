package dataStructure.graph;

/**
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 *
 * 示例 1：
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 *
 * 示例 2：
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 * 提示：
 *
 * 1 <= n,m <= 100
 * 0 <= k<= 20
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class QOffer13_RobotMove {
    public int movingCount(int m, int n, int k) {
        return dfs(m, n, 0, 0, k, new boolean[m][n]);
    }

    // TODO: 想一想怎么把这个去掉？
    //  https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/solution/mian-shi-ti-13-ji-qi-ren-de-yun-dong-fan-wei-dfs-b/
    //  (x + 1) % 10 == 0, cal(x) = x - 8
    //  (x + 1) % 10 != 0, cal(x) = x + 1
    public int cal(int x) {
        int res = 0;
        while(x > 0) {
            res += x % 10;
            x /= 10;
        }
        return res;
    }

    public int dfs(int m, int n, int r, int c, int k, boolean[][] isVisited) {
        if(r >= m || c >= n || isVisited[r][c] || cal(r) + cal(c) > k) return 0;
        isVisited[r][c] = true;
        int right = dfs(m, n, r, c + 1, k, isVisited);
        int down = dfs(m, n, r + 1, c, k, isVisited);
        return right + down + 1;
    }
}
