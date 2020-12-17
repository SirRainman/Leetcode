package algorithm.dp.test;

/**
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 * 请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 *
 * 示例 1：
 * 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 * 输出：4
 * 解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
 * 其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
 *
 * 示例 2：
 * 输入：strs = ["10", "0", "1"], m = 1, n = 1
 * 输出：2
 * 解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
 * 
 *
 * 提示：
 * 1 <= strs.length <= 600
 * 1 <= strs[i].length <= 100
 * strs[i]仅由'0' 和'1' 组成
 * 1 <= m, n <= 100
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ones-and-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ474_OnesAndZeroes {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1]; // dp[i][j] 表示使用i个0，j个1，最多能表示strs字串的个数
        // dp[i][j] = max(dp[i][j], dp[i - zero][j - one] + 1)
        for(String str : strs) {
            int zero = 0, one = 0;
            for(char c : str.toCharArray()) {
                if (c == '0') zero++;
                else if(c == '1') one++;
            }
            for(int i = m; i >= zero; i--) {
                for(int j = n; j >= one; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zero][j - one] + 1);
                }
            }
        }
        return dp[m][n];
    }
}
