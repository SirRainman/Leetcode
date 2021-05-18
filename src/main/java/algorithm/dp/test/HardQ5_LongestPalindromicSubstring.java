package algorithm.dp.test;

import java.util.Arrays;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设s 的最大长度为 1000。
 * 
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 *
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ5_LongestPalindromicSubstring {

    // TODO: 注意：在状态转移方程中，我们是从长度较短的字符串向长度较长的字符串进行转移的，因此一定要注意动态规划的循环顺序。
    public String longestPalindrome3(String s) {
        int longest = 0;
        char[] str = s.toCharArray();
        int n = str.length;
        boolean[][] st = new boolean[n][n];
        for(int i = 0; i < n; i++) Arrays.fill(st[i], true);

        int left = 0, right = 0;
        // TODO: 注意i j的遍历顺序
        for(int i = n - 1; i >= 0; i--) {
            for(int j = i + 1; j < n; j++) {
                if(str[i] == str[j] && st[i + 1][j - 1]) {
                    st[i][j] = true;
                    if(j - i + 1 > longest) {
                        longest = j - i + 1;
                        left = i;
                        right = j;
                    }
                } else {
                    st[i][j] = false;
                }
            }
        }
        return s.substring(left, right + 1);
    }


    // TODO: 区间dp
    //  状态转移方程
    //  dp[i][i]    = true
    //  dp[i][i+1]  = s[i] == s[i+1]
    //  dp[i][j]    = dp[i+1][j-1] && s[i] == s[j]
    public String longestPalindrome(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        boolean[][] dp = new boolean[n][n];
        String res = "";
        for(int len = 0; len < n; len++) {
            for(int i = 0; i + len < n; i++) {
                int j = i + len;
                if(len == 0) {
                    dp[i][j] = true;
                } else if(len == 1) {
                    dp[i][j] = str[i] == str[j];
                } else {
                    dp[i][j] = str[i] == str[j] && dp[i + 1][j - 1];
                }
                if(dp[i][j] && len >= res.length()) res = s.substring(i, j + 1);
            }
        }
        return res;
    }

    // TODO: 二分法
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
