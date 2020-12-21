package dataStructure.union;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
    class UnionFind {
        Map<Integer, Integer> parents;
        public UnionFind(int[] nums) {
            parents = new HashMap<>();
            for(int x : nums) {
                parents.put(x, x);
            }
        }

        public Integer find(int x) {
            if( !parents.containsKey(x)) return null;
            int p = parents.get(x);
            if(x != p) parents.put(x, find(p));
            return parents.get(x);
        }

        public boolean union(int x, int y) {
            Integer rootX = parents.get(x), rootY = parents.get(y);
            if(rootX == null || rootY == null) return false;
            if(rootX == rootY) return false;
            parents.put(rootX, rootY);
            return true;
        }
    }

    public int longestConsecutive(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        UnionFind uf = new UnionFind(nums);
        // TODO: 把所有的连续的数连在一起，放到一个集合中
        for(int x : nums) {
            uf.union(x, x + 1); // 注意union的参数顺序，较大的数做根
        }

        int maxLen = 1;
        for(int x : nums) {
            maxLen = Math.max(maxLen, uf.find(x) - x + 1);
        }
        return maxLen;
    }
}
