package algorithm.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: Leetcode
 * @description:
 * 
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * 返回 s 所有可能的分割方案。
 * 示例:
 * 输入:"aab"
 * 输出:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author: Rain
 * @create: 2021-03-07 15:03
 **/
public class Q131_PalindromePartitioning {
    boolean[][] st;
    List<String> path = new ArrayList<>();
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> partition(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        st = new boolean[n][n];

        for(int i = 0; i < n; i++) Arrays.fill(st[i], true);

        // 先判断两点之间是否是回文串
        for(int i = n - 1; i >= 0; i--) {
            for(int j = i + 1; j < n; j++) {
                st[i][j] = (str[i] == str[j]) && st[i + 1][j - 1];
            }
        }

        dfs(0, s);

        return res;
    }

    public void dfs(int start, String s) {
        if(start == s.length()) {
            res.add(new ArrayList(path));
            return ;
        }
        // 如果是回文串，则添加到路径中
        for(int i = start; i < s.length(); i++) {
            if(st[start][i]) {
                path.add(s.substring(start, i + 1));
                dfs(i + 1, s);
                path.remove(path.size() - 1);
            }
        }
    }
}
