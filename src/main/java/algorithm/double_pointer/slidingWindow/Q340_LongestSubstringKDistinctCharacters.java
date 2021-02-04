package algorithm.double_pointer.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串 s ，找出至多包含 k 个不同字符的最长子串 T。
 *
 * 示例 1:
 * 输入: s = "eceba", k = 2
 * 输出: 3
 * 解释: 则 T 为 "ece"，所以长度为 3。
 *
 * 示例 2:
 * 输入: s = "aa", k = 1
 * 输出: 2
 * 解释: 则 T 为 "aa"，所以长度为 2。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-with-at-most-k-distinct-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q340_LongestSubstringKDistinctCharacters {
    // TODO：[left, right] 区间是一个合法区间
    //  1 滑动窗口的本质是找到一个符合条件的窗口后，更新窗口的大小
    //  2 如果窗口固定，就判断一下这个窗口是否是合法的
    public int lengthOfLongestSubstringKDistinct1(String str, int k) {
        char[] s = str.toCharArray();
        int n = s.length;
        int left = 0, right = 0;
        Map<Character, Integer> map = new HashMap<>();
        int maxLen = 0;

        while(right < n) {
            map.put(s[right], map.getOrDefault(s[right], 0) + 1);
            while(map.size() > k) {
                map.put(s[left], map.get(s[left]) - 1);
                if(map.get(s[left]) == 0) {
                    map.remove(s[left]);
                }
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        return maxLen;
    }

    public int lengthOfLongestSubstringKDistinct(String str, int k) {
        char[] s = str.toCharArray();
        int n = s.length;
        int left = 0, right = 0;
        int[] count = new int[128];
        int maxLen = 0, types = 0;

        while(right < n) {
            if(count[s[right]]++ == 0) types++;
            while(types > k) {
                if(--count[s[left]] == 0) {
                    types--;
                }
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        return maxLen;
    }
}
