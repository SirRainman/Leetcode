package algorithm.double_pointer;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的最长子串的长度。
 *
 * 示例1:
 *
 * 输入: "abcabcbb"
 * 输出: 3 
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。
 *     请注意，你的答案必须是 子串 的长度，"pwke"是一个子序列，不是子串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q3_LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> count = new HashMap<>();

        int ans = 0;
        for(int fast = 0, slow = 0; fast < s.length(); fast++) {
            count.put(s.charAt(fast), count.getOrDefault(s.charAt(fast), 0) + 1);
            while(count.get(s.charAt(fast)) > 1) {
                count.put(s.charAt(slow), count.get(s.charAt(slow)) - 1);
                slow++;
            }
            ans = Math.max(fast - slow + 1, ans);
        }
        return ans;
    }
}
