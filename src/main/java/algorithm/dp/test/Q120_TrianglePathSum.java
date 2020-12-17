package algorithm.dp.test;

import java.util.Arrays;
import java.util.List;

/**
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 *
 * 例如，给定三角形：
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为11（即，2+3+5+1= 11）。
 *
 * 说明：
 * 如果你可以只使用 O(n)的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q120_TrianglePathSum {
    public int minimumTotal1(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;

        int n = triangle.size();
        if(n == 1) return triangle.get(0).get(0);

        int[][] dp = new int[n + 1][n + 1];
        for(int[] d : dp) Arrays.fill(d, Integer.MAX_VALUE);
        dp[1][1] = triangle.get(0).get(0);

        int res = Integer.MAX_VALUE;
        for(int i = 2; i <= n; i++) {
            for(int j = 1; j <= i; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i - 1).get(j - 1);
                if(i == n) res = Math.min(res, dp[i][j]);
            }
        }
        return res;
    }

    public int minimumTotal2(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;

        int n = triangle.size();
        if(n == 1) return triangle.get(0).get(0);

        int[][] dp = new int[n + 1][n + 1];

        for(int i = n - 1; i >= 0; i--) {
            for(int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;

        int n = triangle.size();
        if(n == 1) return triangle.get(0).get(0);

        int[] dp = new int[n + 1];

        for(int i = n - 1; i >= 0; i--) {
            for(int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}
