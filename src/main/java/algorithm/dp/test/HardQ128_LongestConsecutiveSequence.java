package algorithm.dp.test;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 进阶：你可以设计并实现时间复杂度为O(n) 的解决方案吗？
 *
 * 示例 1：
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 * 示例 2：
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 * 
 * 提示：
 * 0 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-consecutive-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ128_LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(); // <key, value> key所在的最长连续子区间的长度
        int maxLen = 0;
        for(int x : nums) {
            if(map.containsKey(x)) continue;

            int leftLen = map.getOrDefault(x - 1, 0); // 左边的数所在的最长连续子区间的长度
            int rightLen = map.getOrDefault(x + 1, 0);
            int len = leftLen + rightLen + 1; // 将左右两个区间连接到了一起

            // TODO: 没怎么看懂
            //  核心思想：就是拿当前数字去找与其左右相连的数字集合看看能否组成一个更大的集合
            //  为什么不更新数组中间的值，因为中间的值对最大长度是没有影响的，所以只需要更新头和尾的值。
            map.put(x, len); // 更新 x 所在的连续子区间的长度
            map.put(x - leftLen, len);
            map.put(x + rightLen, len);

            maxLen = Math.max(maxLen, len);
        }
        return maxLen;
    }
}
