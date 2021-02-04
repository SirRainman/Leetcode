package algorithm.double_pointer.slidingWindow;

/**
 * 给你一个包含 n 个整数的数组，请你找出 长度大于等于 k 且含最大平均值的连续子数组。并输出这个最大平均值。
 *
 * 示例：
 * 输入：[1,12,-5,-6,50,3], k = 4
 * 输出：12.75
 * 解释：
 * 当长度为 5 的时候，最大平均值是 10.8，
 * 当长度为 6 的时候，最大平均值是 9.16667。
 * 所以返回值是 12.75。
 * 
 *
 * 提示：
 * 1 <= k <= n <= 10,000。
 * 数组中的元素范围是 [-10,000, 10,000]。
 * 答案的计算误差小于 10-5。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-average-subarray-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ644_MaximumAverageSubarray2 {
    // TODO：
    //  1 min 表示数组 numsnums 的最小值，maxmax 表示数组 numsnums 的最大值。
    //      最大平均字段和一定在最小值与最大值之间，即区间 (min, max)(min,max) 之间
    //  2 本方法的思想是不断猜测最大平均值，再通过验证更新猜测值，使每次猜测都更加接近正确值。
    //      如果当前猜测值太大，下一次就猜一个更小的值；
    //      如果当前猜测值太小，下一次就猜一个更大的值。
    //  3 可以使用二分查找代替上面的随机猜测。
    //      以 maxmax 和 minmin 作为初始猜测边界，每次猜测值为 mid=(min+max)/2。
    //      接下来需要寻找是否存在长度大于等于 k 的子数组的平均值大于 mid
    //  4 数组中每个元素都减去 mid 后，
    //      如果存在长度大于等于 k 的子数组之和大于等于 0，就表明数组 numsnums 中存在子数组的平均和大于等于 mid，此时令猜测区间的下边界为 mid；
    //      否则令猜测区间的上边界为 mid，然后继续该过程。
    public double findMaxAverage(int[] nums, int k) {
        int maxNum = Integer.MIN_VALUE, minNum = Integer.MAX_VALUE;
        for(int x : nums) {
            maxNum = Math.max(maxNum, x);
            minNum = Math.min(minNum, x);
        }
        double left = minNum, right = maxNum;
        while(Math.abs(right - left) >= 1e-6) {
            double mid = (left + right) * 0.5;
            if(check(nums, k, mid)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public boolean check(int[] nums, int k, double mid) {
        double sum = 0, pre = 0, minSum = 0;
        for(int i = 0; i < k; i++) {
            sum += nums[i] - mid;
        }
        if(sum >= 0) return true;
        for(int i = k; i < nums.length; i++) {
            sum += nums[i] - mid;
            pre += nums[i - k] - mid;
            minSum = Math.min(minSum, pre);
            if(sum >= minSum) return true;
        }
        return false;
    }
}
