package dataStructure.graph.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q46_Permutations {
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void backtrack(int[] nums, int start) {
        if(start == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for(int i = start; i < nums.length; i++) {
            swap(nums, start, i);
            path.add(nums[start]);
            backtrack(nums, start + 1);
            path.remove(path.size() - 1);
            swap(nums, start, i);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        backtrack(nums, 0);
        return ans;
    }
}
