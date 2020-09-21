package algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q78_Subsets {
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> sub = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        // 方法1
        // dfs(nums, 0);

        // 方法二
        backtrack(nums, 0);
        return ans;
    }

    // TODO: 回溯法的精髓就是选择该数 或者不选该数
    public void dfs(int[] nums, int cur) {
        if(cur == nums.length) {
            ans.add(new ArrayList<>(sub));
            return;
        }
        // 选择当前元素
        sub.add(nums[cur]);
        dfs(nums, cur+1);

        // 不选择当前元素
        sub.remove(sub.size() - 1);
        dfs(nums, cur+1);
    }

    public void backtrack(int nums[], int cur) {
        ans.add(new ArrayList<>(sub));
        for(int i = cur; i < nums.length; i++) {
            sub.add(nums[i]);
            backtrack(nums, i+1);
            sub.remove(sub.size() - 1);
        }
    }
}
