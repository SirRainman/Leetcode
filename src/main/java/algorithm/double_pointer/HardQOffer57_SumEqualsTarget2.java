package algorithm.double_pointer;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 *
 * 示例 1：
 * 输入：target = 9
 * 输出：[[2,3,4],[4,5]]
 *
 * 示例 2：
 * 输入：target = 15
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 * 
 *
 * 限制：
 * 1 <= target <= 10^5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQOffer57_SumEqualsTarget2 {
    // TODO: 有点难
    //  维护一个区间，区间的和大于target则缩小区间，区间和小于target则扩大区间
    public int[][] findContinuousSequence(int target) {
        List<int[]> res = new ArrayList<>();
        int left = 1, right = 2;
        int sum = left + right;
        while(left <= target / 2) {
            // int sum = (right - left + 1) * (left + right) / 2;
            if(sum == target) {
                int[] nums = new int[right - left + 1];
                for(int i = left; i <= right; i++) nums[i - left] = i;
                res.add(nums);
                sum -= left;
                left++;
            }
            else if(sum < target) {
                right++;
                sum += right;
            }
            else {
                sum -= left;
                left++;
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
