package algorithm.double_pointer.slidingWindow;

/**
 * 当 A的子数组A[i], A[i+1], ..., A[j]满足下列条件时，我们称其为湍流子数组：
 * 若i <= k < j，当 k为奇数时，A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
 * 或 若i <= k < j，当 k 为偶数时，A[k] > A[k+1]，且当 k为奇数时，A[k] < A[k+1]。
 * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
 * 返回 A 的最大湍流子数组的长度。
 *
 * 示例 1：
 * 输入：[9,4,2,10,7,8,8,1,9]
 * 输出：5
 * 解释：(A[1] > A[2] < A[3] > A[4] < A[5])
 *
 * 示例 2：
 * 输入：[4,8,12,16]
 * 输出：2
 *
 * 示例 3：
 * 输入：[100]
 * 输出：1
 *
 * 提示：
 * 1 <= A.length <= 40000
 * 0 <= A[i] <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-turbulent-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ978_LongestTurbulentSubarray {
    // TODO：滑动窗口
    //
    public int maxTurbulenceSize(int[] arr) {
        if(arr.length < 2) return arr.length;
        int n = arr.length;
        int left = 0, right = 1, maxLen = 1;
        boolean pre = false;
        while(right < n) {
            boolean current = arr[right - 1] < arr[right];
            if(pre == current) {
                left = right - 1;
            }
            if(arr[right - 1] == arr[right]) {
                left = right;
            }
            maxLen = Math.max(maxLen, right - left + 1);
            pre = current;
            right++;
        }
        return maxLen;
    }

    // TODO:
    //  状态表示：
    //      dp[i][0] 以arr[i]为结尾，且arr[i - 1] > arr[i]的序列的长度
    //      dp[i][1] 以arr[i]为结尾，且arr[i - 1] < arr[i]的序列的长度
    //  属性：max(dp[i][1], dp[i][0])
    //  状态计算：
    //      arr[i - 1] > arr[i] 时，
    //      arr[i - 1] < arr[i] 时，
    //      arr[i - 1] = arr[i] 时，
    public int maxTurbulenceSize2(int[] arr) {
        if(arr == null || arr.length == 0) return 0;
        int n = arr.length;
        int[][] dp = new int[n][2]; // dp[i][1] 表示以arr[i]为起始点的奇数位最大的长度
        dp[0][0] = dp[0][1] = 1;
        int maxLen = 1;
        for(int i = 1; i < n; i++) {
            dp[i][0] = dp[i][1] = 1;
            if(arr[i - 1] < arr[i]) {
                dp[i][1] = dp[i - 1][0] + 1;
            } else if(arr[i - 1] > arr[i]){
                dp[i][0] = dp[i - 1][1] + 1;
            }
            maxLen = Math.max(maxLen, Math.max(dp[i][0], dp[i][1]));
        }
        return maxLen;
    }

    // TODO: 动态规划的优化
    public int maxTurbulenceSize3(int[] arr) {
        if(arr == null || arr.length == 0) return 0;
        int n = arr.length;
        int dp0 = 1, dp1 = 1;
        int maxLen = 1;
        for(int i = 1; i < n; i++) {
            if(arr[i - 1] < arr[i]) {
                dp1 = dp0 + 1;
                dp0 = 1;
            } else if(arr[i - 1] > arr[i]){
                dp0 = dp1 + 1;
                dp1 = 1;
            } else {
                dp0 = dp1 = 1;
            }
            maxLen = Math.max(maxLen, Math.max(dp0, dp1));
        }
        return maxLen;
    }
}
