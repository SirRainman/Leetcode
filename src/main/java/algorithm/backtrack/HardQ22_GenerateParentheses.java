package algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 示例：
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
        List<String> res = new ArrayList<>();
        backtrack(res, n, n, new StringBuffer());
        return res;
    }

    // TODO: 从树的角度来进行剪枝
    //  回溯算法事实上就是在一个树形问题上做深度优先遍历，因此 首先需要把问题转换为树形问题。
    public void backtrack(List<String> res, int open, int close, StringBuffer path) {
        if(open == 0 && close == 0) res.add(path.toString());
        if(open > 0) {
            path.append("(");
            backtrack(res, open - 1, close, path);
            path.deleteCharAt(path.length() - 1);
        }
        if(close > open) {
            path.append(")");
            backtrack(res, open, close - 1, path);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
