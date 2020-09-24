package algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *
 * 示例：
 *
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ22_GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtrack(ans, n, n, new StringBuffer());
        return ans;
    }

    public void backtrack(List<String> ans, int left, int right, StringBuffer path) {
        if(left == 0 && right == 0) {
            ans.add(path.toString());
        }
        if(left > 0) {
            path.append("(");
            backtrack(ans, left-1, right, path);
            path.deleteCharAt(path.length() - 1);
        }
        if(right > left) {
            path.append(")");
            backtrack(ans, left, right-1, path);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
