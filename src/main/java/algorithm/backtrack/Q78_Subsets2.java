package algorithm.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: [1,2,2]
 * 输出:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q78_Subsets2 {
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        backtrack(nums, 0);
        return ans;
    }

    public void backtrack(int[] nums, int cur) {
        ans.add(new ArrayList<>(path));
        // Set<Integer> existNum = new HashSet<>();
        for(int i = cur; i < nums.length; i++) {
            // TODO: 为什么这么做不对？？？
            //  但是对nums进行排序后，再使用set就可以通过
            //  输入
            //  [2,1,2, 2, 2]
            //  输出
            //  [[],[2],[2,1],[2,1,2],[2,1,2,2],[2,1,2,2,2],[2,2],[2,2,2],[2,2,2,2],[1],[1,2],[1,2,2],[1,2,2,2]]
            //  预期结果
            //  [[],[1],[1,2],[1,2,2],[1,2,2,2],[1,2,2,2,2],[2],[2,2],[2,2,2],[2,2,2,2]]
            //  如果不进行排序的话，访问过2之后，再访问1是一次； 访问到了1之后，还是会在访问1一遍后面的2


            // if(existNum.contains(nums[i])) continue;
            // existNum.add(nums[i]);

            if(i > cur && nums[i] == nums[i - 1]) continue;

            path.add(nums[i]);
            backtrack(nums, i+1);
            path.remove(path.size()-1);
        }
    }
}
