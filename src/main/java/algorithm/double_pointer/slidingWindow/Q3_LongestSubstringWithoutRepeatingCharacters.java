package algorithm.double_pointer.slidingWindow;

import java.util.Arrays;
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

    public int lengthOfLongestSubstring1(String str) {
        char[] s = str.toCharArray();
        int maxLen = 0;
        int left = 0, right = 0;
        int[] count = new int[128];
        while(right < s.length) {
            count[s[right]]++;
            while(count[s[right]] > 1) {
                count[s[left]]--;
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        return maxLen;
    }


    public int lengthOfLongestSubstring2(String s) {
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

    // TODO: 针对滑动窗口的优化，map记录每个字母出现的位置
    public int lengthOfLongestSubstring(String str) {
        char[] s = str.toCharArray();
        int left = 0, right = 0, maxLen = 0;
        int[] map = new int[128];
        Arrays.fill(map, -1);
        while(right < s.length) {
            if(map[s[right]] != -1) { // 检查left左端点是否更新
                left = Math.max(left, map[s[right]] + 1);
            }
            map[s[right]] = right;
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        return maxLen;
    }
}
