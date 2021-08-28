package algorithm.diff_prefixSum;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: Leetcode
 * @description:
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 *
 * 示例 1:
 * 输入: nums = [0,1]
 * 输出: 2
 * 说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
 *
 * 示例 2:
 * 输入: nums = [0,1,0]
 * 输出: 2
 * 说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
 *
 * 提示：
 * 1 <= nums.length <= 105
 * nums[i] 不是 0 就是 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contiguous-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-06-03 12:10
 **/
public class Q525_ContiguousArray {
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        // 记录0 1 出现的次数
        int cntOne = 0, cntZero = 0, res = 0;
        // 哈希表存每个位置上的0 1出现次数的差值
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for(int i = 0; i < n; i++) {
            if(nums[i] == 0) cntZero++;
            else cntOne++;
            // 如果前面出现过相同的差值，则说明子数组中的0 1 的出现次数相同
            if(map.containsKey(cntOne - cntZero)) res = Math.max(res, i - map.get(cntOne - cntZero));
            else map.put(cntOne - cntZero, i);
        }
        return res;
    }
}
