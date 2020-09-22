package algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 *
 * 说明：
 *
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 *
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 示例 2:
 *
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q216_CombinationSum3 {

    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        backtrack(k, n, 1);
        return ans;
    }

    public void backtrack(int k, int n, int cur) {
        if(k == 0 ) {
            if(n == 0) {
                ans.add(new ArrayList<>(path));
            }
            return;
        } else if(k > 0 && n < 0) {
            return;
        }

        for(int i = cur; i < 10; i++) {
            path.add(i);
            backtrack(k - 1, n - i, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
