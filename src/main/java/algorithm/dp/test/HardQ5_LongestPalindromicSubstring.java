package algorithm.dp.test;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设s 的最大长度为 1000。
 * 
 * 示例 1：
 * 
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * 
 * 输入: "cbbd"
 * 输出: "bb"
 * 
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ5_LongestPalindromicSubstring {

    // TODO:状态转移方程
    //  dp[i][i]    = true
    //  dp[i][i+1]  = s[i] == s[i+1]
    //  dp[i][j]    = dp[i+1][j-1] && s[i] == s[j]
    public String longestPalindrome(String s) {
        int len = s.length();
        if (s == null || len <= 1) return s;
        String ans = "";
        boolean[][] dp = new boolean[len][len];
        for (int step = 0; step < len; step++) {
            for (int i = 0; i + step < len; i++) {
                int j = i + step;
                if (step == 0) {
                    dp[i][j] = true;
                } else if (step == 1) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else if (dp[i + 1][j - 1] == true) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                }
                if (dp[i][j] && ans.length() <= step) {
                    ans = s.substring(i, j + 1);
                }
            }
        }
        return ans;
    }

    public String longestPalindrome2(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        int start = 0, end = 0, maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expand(s, i, i);
            int len2 = expand(s, i, i + 1);
            int l = Math.max(len1, len2);
            //System.out.println(l);
            if (l > maxLen) {
                start = i - (l - 1) / 2;
                end = i + l / 2;
                maxLen = l;
            }
        }
        return s.substring(start, end + 1);
    }

    public int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

}
