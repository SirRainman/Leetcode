package algorithm.backtrack;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 *
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ47_Permutations2 {

    List<Integer> path = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<>();

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void dfs(int[] nums, int start) {
        if(start == nums.length) ans.add(new ArrayList<>(path));

        Set<Integer> exist = new HashSet<>();
        for(int i = start; i < nums.length; i++) {
            if(exist.contains(nums[i])) continue;


            swap(nums, i, start);

            exist.add(nums[start]);
            path.add(nums[start]);

            dfs(nums, start + 1);

            path.remove(path.size() - 1);
            swap(nums, i, start);
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        dfs(nums, 0);
        return ans;
    }
}
