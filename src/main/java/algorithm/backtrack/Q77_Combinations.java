package algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 示例:
 *
 * 输入:n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combinations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q77_Combinations {
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        backtrack(n, k, 1);
        return ans;
    }

    public void backtrack(int n, int k, int cur) {
        if(k == 0) {
            ans.add(new ArrayList(path));
            return ;
        }
        for(int i = cur; i <= n; i++) {
            path.add(i);
            backtrack(n, k-1, i+1);
            path.remove(path.size() - 1);
        }
    }
}
