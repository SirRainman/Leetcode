package algorithm.dp.test;

/**
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成两笔交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 
 *
 * 示例1:
 * 输入：prices = [3,3,5,0,0,3,1,4]
 * 输出：6
 * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 *
 * 示例 2：
 * 输入：prices = [1,2,3,4,5]
 * 输出：4
 * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。  
 *     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。  
 *     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 *
 * 示例 3：
 * 输入：prices = [7,6,4,3,1]
 * 输出：0 
 * 解释：在这个情况下, 没有交易完成, 所以最大利润为 0。
 *
 * 示例 4：
 * 输入：prices = [1]
 * 输出：0
 * 
 * 提示：
 * 1 <=prices.length <= 105
 * 0 <=prices[i] <=105
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ123_BestTimeToBuyAndSellStock3 {
    // TODO:
    //  1.状态定义-集合划分：dp[i][j] 第i天结束后，经过1至2笔交易后所剩的最大现金
    //  2.状态属性：max
    //  3.状态计算：第i天时，当前的操作状态可以划分为
    //      0 没有交易操作 = dp[i][0] = 0
    //      1 第一次买 = dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - price[i])
    //          买：第i天买入股票，dp[i][1] = dp[i - 1][0] - prices[i]
    //          不买：第i天没有操作，而是沿用前一天买入的状态，即：dp[i][1] = dp[i - 1][1]
    //      2 第一次卖 = dp[i][2] = Math.max(dp[i - 1][2] + price[i], dp[i - 1][2]
    //          卖：第i天卖出股票 dp[i][2] = dp[i - 1][1] + price[i]
    //          不卖：第i天不卖 dp[i][2] = dp[i - 1][2]
    //      3 第二次买 = dp[i][3] = Math.max(dp[i - 1][2] - price[i], dp[i - 1][3])
    //          买：第i天第二次买 dp[i][3] = dp[i - 1][2] - price[i]
    //          不买：第i天不买 dp[i][3] = dp[i - 1][2]
    //      4 第二次卖 = dp[i][4] = Math.max(dp[i - 1][3] + price[i], dp[i - 1][4])
    public int maxProfit1(int[] prices) {
        if(prices == null || prices.length == 0 ) return 0;
        int n = prices.length;
        int[][] dp = new int[n][5];
        dp[0][1] = -1 * prices[0];
        dp[0][3] = -1 * prices[0];
        for(int i = 1; i < n; i++) {
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
            dp[i][2] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][2]);
            dp[i][3] = Math.max(dp[i - 1][2] - prices[i], dp[i - 1][3]);
            dp[i][4] = Math.max(dp[i - 1][3] + prices[i], dp[i - 1][4]);
        }
        return dp[n - 1][4];
    }

    // TODO: 空间优化
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0 ) return 0;
        int n = prices.length;
        int firstBuy = -1 * prices[0], firstSell = 0, secondBuy = -1 * prices[0], secondSell = 0;
        for(int i = 1; i < n; i++) {
            firstBuy = Math.max( -1 * prices[i], firstBuy);
            firstSell = Math.max(firstBuy + prices[i], firstSell);
            secondBuy = Math.max(firstSell - prices[i], secondBuy);
            secondSell = Math.max(secondBuy + prices[i], secondSell);
        }
        return secondSell;
    }
}
