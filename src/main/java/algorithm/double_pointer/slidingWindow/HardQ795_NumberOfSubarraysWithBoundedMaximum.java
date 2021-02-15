package algorithm.double_pointer.slidingWindow;

/**
 * @program: Leetcode
 * @description:
 * 给定一个元素都是正整数的数组A，正整数 L以及R(L <= R)。
 * 求连续、非空且其中最大元素满足大于等于L小于等于R的子数组个数。
 *
 * 例如 :
 * 输入: 
 * A = [2, 1, 4, 3]
 * L = 2
 * R = 3
 * 输出: 3
 * 解释: 满足条件的子数组: [2], [2, 1], [3].
 * 
 * 注意:
 * L, R 和A[i] 都是整数，范围在[0, 10^9]。
 * 数组A的长度范围在[1, 50000]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-subarrays-with-bounded-maximum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author: Rain
 * @create: 2021-02-10 12:09
 **/
public class HardQ795_NumberOfSubarraysWithBoundedMaximum {
    // TODO: 想一想为什额？
    public static int numSubarrayBoundedMax(int[] arr, int L, int R) {
        int left = 0, right = 0, res = 0, count = 0;
        while(right < arr.length) {
            if(arr[right] > R) { // left 是子区间最大值小于R的左端点
                left = right + 1;
            }
            if(arr[right] >= L) { // 如果在范围内，更新count为区间长度；如果不再区间范围内，则该数可以拼接到上有个有效子数组的后面
                count = right - left + 1;
            }
            res += count;
            right++;
        }
        return res;
    }

    // TODO: 最大元素满足大于等于L小于等于R的子数组个数 = 最大元素小于等于R的子数组个数 - 最大元素小于L的子数组个数
    public static int numSubarrayBoundedMax1(int[] arr, int L, int R) {
        if (arr == null || arr.length == 0) return 0;
        // arr的所有子数组中，根据子数组最大值情况，可分为三类：(..., L-1], [L, R], [R+1, ...)
        // 范围在[L, R]的个数 == count(..., R] - count(..., L-1]
        return countOfFloor(arr, R) - countOfFloor(arr, L-1);
    }

    // 返回数组arr中，最大值小于等于k的子数组的个数
    // 最初始的DP解：真实申请dp数组，不做空间优化
    public static int countOfFloor1(int[] arr, int k) {
        int n = arr.length;
        if (n == 0) return 0;
        // dp[i]：必须以i位置结尾的子数组arr[... i]，满足最大值 <= k的子数组个数
        int[] dp = new int[n];
        dp[0] = arr[0] <= k ? 1 : 0;
        int count = dp[0];
        for (int i = 1; i < n; i++) {
            // 求必须以i结尾的子数组满足条件的个数dp[i]
            // 1. 如果当前i位置元素值小于等于k：
            // 1）则至少可以形成1个符合条件的子数组（arr[i]）
            // 2）然后再看前面以i-1位置结尾有多少个符合条件的子数组，对于前面的每个符合条件的子数组，arr[i]都可以追加到其后面，形成的子数组同样符合条件，dp[i-1]个
            // 总共获得dp[i-1]+1个
            // 2. 如果当前i位置元素值大于k，则必须以i结尾没有符合条件的子数组
            dp[i] = arr[i] <= k ? dp[i-1]+1 : 0;
            count += dp[i];
        }
        return count;
    }

    // 返回数组arr中，最大值小于等于k的子数组的个数
    // 空间优化后的DP解
    public static int countOfFloor(int[] arr, int k) {
        int n = arr.length;
        if (n == 0) return 0;
        // dp[i]：必须以i位置结尾的子数组arr[... i]，满足最大值 <= k的子数组个数
        int dp = arr[0] <= k ? 1 : 0;
        int count = dp;
        for (int i = 1; i < n; i++) {
            dp = arr[i] <= k ? dp+1 : 0;
            count += dp;
        }
        return count;
    }
    
}
