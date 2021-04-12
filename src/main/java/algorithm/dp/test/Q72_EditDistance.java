package algorithm.dp.test;

/**
 * 给你两个单词word1 和word2，请你计算出将word1转换成word2 所使用的最少操作数。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 
 *
 * 示例1：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 *
 * 示例2：
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 * 
 *
 * 提示：
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/edit-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q72_EditDistance {
    // TODO:
    //  1.集合划分：dp[i][j] wa[1, i] 变成 wb[1, j] 的编辑方式的操作数
    //  2.属性：min
    //  3.状态计算：
    //      1 增加 dp[i][j] = dp[i][j - 1] + 1
    //      2 删除 dp[i][j] = dp[i - 1][j] + 1
    //      3 修改
    //          a[i] = b[j],  dp[i][j] = dp[i - 1][j - 1]
    //          a[i] != b[j], dp[i][j] = dp[i - 1][j - 1] + 1
    public int minDistance(String word1, String word2) {
        int la = word1.length(), lb = word2.length();
        char[] a = word1.toCharArray();
        char[] b = word2.toCharArray();

        int[][] dp = new int[la + 1][lb + 1];
        for(int i = 0; i <= la; i++) dp[i][0] = i; // 增加
        for(int j = 0; j <= lb; j++) dp[0][j] = j; // 删除

        for(int i = 1; i <= la; i++) {
            for(int j = 1; j <= lb; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1; // 如果增加或删除，要进行加一
                if(a[i - 1] == b[j - 1]) dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]); // 如果相同则不变
                else dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + 1); // 不同则进行修改
            }
        }
        return dp[la][lb];
    }
}
