package algorithm.dp.test;

/**
 * 给你一个整数数组 nums，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 * 示例 1:
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释:子数组 [2,3] 有最大乘积 6。
 *
 * 示例 2:
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释:结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ152_MaximumProductSubarray {
    // TODO: 看之前看一下Q53最大连续子序列的和
    //  集合划分：
    //      1 dpMax[i] 以nums[i] 为结尾的 子序列中，乘积最大的子序列
    //      2 dpMin[i] 以nums[i] 为结尾的 子序列中，乘积最小的子序列
    //  属性：min max
    //  状态计算：dpMax = max(
    public int maxProduct1(int[] nums) {
        int n = nums.length;
        if(nums == null || n == 0) return 0;

        int maxMulti = nums[0];
        int[] dpMax = new int[n];
        int[] dpMin = new int[n];

        dpMax[0] = dpMin[0] = nums[0];
        for(int i = 1; i < n; i++) {
            // 如果当前位置是一个正数的话，我们更希望以它前一个位置结尾的某个段的积也是个正数，并且希望它尽可能地大。
            dpMax[i] = Math.max(dpMax[i - 1] * nums[i], Math.max(nums[i], nums[i] * dpMin[i - 1]));
            // 当前位置如果是一个负数的话，那么我们希望以它前一个位置结尾的某个段的积也是个负数，这样就可以负负得正，并且我们希望这个积尽可能「负得更多」，即尽可能小。
            dpMin[i] = Math.min(dpMin[i - 1] * nums[i], Math.min(nums[i], nums[i] * dpMax[i - 1]));
            maxMulti = Math.max(maxMulti, dpMax[i]);
        }

        return maxMulti;
    }

    // TODO：空间优化
    public int maxProduct(int[] nums) {
        int n = nums.length;
        if(nums == null || n == 0) return 0;

        int maxMulti = nums[0];

        int dpMax = nums[0], dpMin = nums[0];
        for(int i = 1; i < n; i++) {
            // TODO：注意这个滚动数组
            int mx = dpMax, mn = dpMin;
            // 如果当前位置是一个正数的话，我们更希望以它前一个位置结尾的某个段的积也是个正数，并且希望它尽可能地大。
            dpMax = Math.max(mx * nums[i], Math.max(nums[i], nums[i] * mn));
            // 当前位置如果是一个负数的话，那么我们希望以它前一个位置结尾的某个段的积也是个负数，这样就可以负负得正，并且我们希望这个积尽可能「负得更多」，即尽可能小。
            dpMin = Math.min(mn * nums[i], Math.min(nums[i], nums[i] * mx));
            maxMulti = Math.max(maxMulti, dpMax);
        }

        return maxMulti;
    }
}
