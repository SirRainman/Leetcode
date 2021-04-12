package algorithm.double_pointer.slidingWindow;

/**
 * @program: Leetcode
 * @description:
 * 
 * 给定一个二进制数组，你可以最多将1 个 0 翻转为 1，找出其中最大连续 1 的个数。
 *
 * 示例 1：
 * 输入：[1,0,1,1,0]
 * 输出：4
 * 解释：翻转第一个 0 可以得到最长的连续 1。
 *     当翻转以后，最大连续 1 的个数为 4。
 * 
 * 注：
 * 输入数组只包含0 和1.
 * 输入数组的长度为正整数，且不超过 10,000
 *
 * 进阶：
 * 如果输入的数字是作为 无限流 逐个输入如何处理？换句话说，内存不能存储下所有从流中输入的数字。您可以有效地解决吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-consecutive-ones-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author: Rain
 * @create: 2021-02-15 11:44
 **/
public class Q487_MaxConsecutiveOnes2 {
    // TODO：看一看Q424题
    //  想一想为什么窗口的大小是一直在变大的？？
    //  https://leetcode-cn.com/problems/longest-repeating-character-replacement/solution/ti-huan-hou-de-zui-chang-zhong-fu-zi-fu-eaacp/
    //      1 如果长度为 L 的子串不符合题目的要求，那么左边界固定，长度更长的子串也不符合题目的要求。
    //      2 maxCount 在内层循环「左边界向右移动一个位置」的过程中，没有维护它的定义
    //  窗口的大小是一直在变大的
    public int findMaxConsecutiveOnes1(int[] nums) {
        int n = nums.length;
        int left = 0, right = 0, maxLen = 0, chance = 1;
        int[] count = new int[2]; // 记录0 1 出现的次数
        while(right < n) {
            count[nums[right]]++;
            if(count[0] > chance) {
                count[nums[left]]--;
                left++;
            }
            right++;
        }
        return right - left;
    }

    // 状态表示：dp[i][0] [0, i]区间中，不改变的最长连续1的区间；
    //          dp[i][1] [0, i]区间中，改变后的最长连续1的区间
    // 属性：max
    // 状态计算：nums[i] == 1
    //          nums[i] == 0
    public int findMaxConsecutiveOnes2(int[] nums) {
        int[][] dp = new int[nums.length + 1][2];
        int maxLen = 0;
        for(int i = 1; i <= nums.length; i++) {
            if(nums[i - 1] == 0) {
                dp[i][0] = 0;
                dp[i][1] = dp[i - 1][0] + 1;
            } else {
                dp[i][0] = dp[i - 1][0] + 1;
                dp[i][1] = dp[i - 1][1] + 1;
            }
            maxLen = Math.max(maxLen, Math.max(dp[i][0], dp[i][1]));
        }
        return maxLen;
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int dp0 = 0, dp1 = 0, maxLen = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 1) {
                dp0++;
                dp1++;
            } else {
                dp1 = dp0 + 1;
                dp0 = 0;
            }
            maxLen = Math.max(maxLen, Math.max(dp0, dp1));
        }
        return maxLen;
    }
}
