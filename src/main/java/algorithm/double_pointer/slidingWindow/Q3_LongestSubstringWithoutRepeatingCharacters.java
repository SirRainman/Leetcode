package algorithm.double_pointer.slidingWindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的最长子串的长度。
 *
 * 示例1:
 * 输入: "abcabcbb"
 * 输出: 3 
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q3_LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring1(String s) {
        char[] str = s.toCharArray();
        int[] map = new int[128];
        int res = 0, left = 0, right = 0;
        while(right < str.length) {
            map[str[right]]++;
            while(map[str[right]] > 1) {
                map[str[left]]--;
                left++;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }

    // TODO: 针对滑动窗口的优化
    //  map记录每个字母在最右边出现的位置
    public int lengthOfLongestSubstring(String s) {
        char[] str = s.toCharArray();
        int[] map = new int[128];
        Arrays.fill(map, -1);
        int res = 0, left = 0, right = 0;
        while(right < str.length) {
            if(map[str[right]] != -1) {
                left = Math.max(left, map[str[right]] + 1);
            }
            map[str[right]] = right;
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
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
}
