package algorithm.dp.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @program: Leetcode
 * @description:
 * @author: Rain
 * @create: 2021-03-28 16:26
 **/
public class Q139_WordBreak {
    // TODO：前i个字母的字符串 是否 可以拆分成字典中的单词
    public boolean wordBreak(String str, List<String> wordDict) {
        int n = str.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for(int i = 0; i < n; i++) {
            if(!dp[i]) continue;
            for (String word : wordDict) {
                if(i + word.length() <= str.length() && word.equals(str.substring(i, i + word.length()))) {
                    dp[i + word.length()] = true;
                }
            }
        }
        return dp[n];
    }

    public boolean wordBreak1(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public boolean wordBreak2(String str, List<String> wordDict) {
        int n = str.length();
        Set<String> dict = new HashSet<>(wordDict);
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j <= n; j++) {
                if(dp[i] && dict.contains(str.substring(i, j))) {
                    dp[j] = true;
                }
            }
        }
        return dp[n];
    }
}
