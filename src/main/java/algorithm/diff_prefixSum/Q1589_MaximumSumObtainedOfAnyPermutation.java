package algorithm.diff_prefixSum;

import java.util.Arrays;

/**
 * 有一个整数数组nums，和一个查询数组requests，其中requests[i] = [starti, endi]。
 * 第i个查询求nums[starti] + nums[starti + 1] + ... + nums[endi - 1] + nums[endi]的结果，
 * starti 和endi数组索引都是 从 0 开始 的。
 * 你可以任意排列 nums中的数字，请你返回所有查询结果之和的最大值。
 * 由于答案可能会很大，请你将它对109 + 7取余后返回。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,4,5], requests = [[1,3],[0,1]]
 * 输出：19
 * 解释：一个可行的 nums 排列为 [2,1,3,4,5]，并有如下结果：
 * requests[0] -> nums[1] + nums[2] + nums[3] = 1 + 3 + 4 = 8
 * requests[1] -> nums[0] + nums[1] = 2 + 1 = 3
 * 总和为：8 + 3 = 11。
 * 一个总和更大的排列为 [3,5,4,2,1]，并有如下结果：
 * requests[0] -> nums[1] + nums[2] + nums[3] = 5 + 4 + 2 = 11
 * requests[1] -> nums[0] + nums[1] = 3 + 5  = 8
 * 总和为： 11 + 8 = 19，这个方案是所有排列中查询之和最大的结果。
 *
 * 示例 2：
 * 输入：nums = [1,2,3,4,5,6], requests = [[0,1]]
 * 输出：11
 * 解释：一个总和最大的排列为 [6,5,4,3,2,1] ，查询和为 [11]。
 *
 * 示例 3：
 * 输入：nums = [1,2,3,4,5,10], requests = [[0,2],[1,3],[1,1]]
 * 输出：47
 * 解释：一个和最大的排列为 [4,10,5,3,2,1] ，查询结果分别为 [19,18,10]。
 *
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 105
 * 0 <= nums[i]<= 105
 * 1 <= requests.length <=105
 * requests[i].length == 2
 * 0 <= starti<= endi<n
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-sum-obtained-of-any-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q1589_MaximumSumObtainedOfAnyPermutation {
    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        // TODO：
        //  先用times[i]表示第i个位置出现的次数与第i-1个位置出现的次数的差值，
        //      即times[i] = times[i] - times[i - 1]。
        //  在计算每个位置上具体出现的次数
        int n = nums.length;
        int[] times = new int[n]; // 存储每个位置查询过的次数
        for(int[] req : requests) {
            // TODO：O(m*n)解法中关键一点，内层循环我们一直重复的在[i, j]之间加上k，怎么将这循环变成O(1)，成为问题的关键！
            times[req[0]]++;
            if(req[1] + 1 < n) times[req[1] + 1]--;
        }
        for(int i = 0; i < n; i++) {
            if(i > 0) times[i] += times[i - 1];
        }
        Arrays.sort(times);
        Arrays.sort(nums);

        long res = 0, mod = (int) 1e9 + 7;
        for(int i = 0; i < n; i++) {
            res += (long) times[i] * nums[i];
            res %= mod;
        }
        return (int)res;
    }
}
