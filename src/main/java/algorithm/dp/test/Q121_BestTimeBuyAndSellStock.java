package algorithm.dp.test;

/**
 * 给定一个数组，它的第i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * 注意：你不能在买入股票前卖出股票。
 *
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 *
 * 示例 2:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q121_BestTimeBuyAndSellStock {
    public int maxProfit1(int[] prices) {
        if(prices == null || prices.length == 0) return 0;
        int buyPrice = prices[0], profit = 0;
        for(int i = 1; i < prices.length; i++) {
            profit = Math.max(prices[i] - buyPrice, profit);
            buyPrice = Math.min(buyPrice, prices[i]);
        }
        return profit;
    }

    // TODO：区间和问题可以转化为求差问题，求差问题可以转化为求区间和问题
    //  https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/121-mai-mai-gu-piao-de-zui-jia-shi-ji-dp-7-xing-ji/
    //  前缀和 && 差分
    //  集合划分 dp[i] 表示以 i 为结尾的连续子差分数组的和
    //  属性：max
    //  状态计算：dp[i] = max(0, dp[i − 1] + diff[i])
    public int maxProfit2(int[] prices) {
        if(prices == null || prices.length == 0) return 0;
        int n = prices.length;
        int[] diff = new int[n];
        for (int i = 0; i < n - 1; i++) {
            diff[i] = prices[i + 1] - prices[i];
        }

        int[] dp = new int[n];
        dp[0] = Math.max(0, diff[0]);
        int profit = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(0, dp[i - 1] + diff[i]);
            profit = Math.max(profit, dp[i]);
        }
        return profit;
    }

    // TODO: 优化掉dp数组
    public int maxProfit3(int[] prices) {
        if(prices == null || prices.length == 0) return 0;
        int n = prices.length;
        int[] diff = new int[n];
        for (int i = 0; i < n - 1; i++) {
            diff[i] = prices[i + 1] - prices[i];
        }

        int last = Math.max(0, diff[0]);
        int profit = last;
        for (int i = 1; i < n; i++) {
            last = Math.max(0, last + diff[i]);
            profit = Math.max(profit, last);
        }
        return profit;
    }

    // TODO: 优化掉diff
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) return 0;
        int n = prices.length;
        int last = 0;
        int maxProfit = last;
        for(int i = 1; i < n; i++) {
            last = Math.max(last + prices[i] - prices[i - 1], 0);
            maxProfit = Math.max(maxProfit, last);
        }
        return maxProfit;
    }
}
