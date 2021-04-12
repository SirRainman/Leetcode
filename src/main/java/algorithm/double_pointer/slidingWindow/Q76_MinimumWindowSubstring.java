package algorithm.double_pointer.slidingWindow;

/**
 * @program: Leetcode
 * @description:
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 *
 * 示例 2：
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 
 * 提示：
 * 1 <= s.length, t.length <= 105
 * s 和 t 由英文字母组成
 * 
 * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-window-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-03-24 11:27
 **/
public class Q76_MinimumWindowSubstring {
    public String minWindow(String S, String T) {
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        int[] mapS = new int[128];
        int[] mapT = new int[128];
        for(char c : t) mapT[c]++;

        int minlen = Integer.MAX_VALUE;
        int start = -1, end = -1;
        int left = 0, right = 0;
        while(right < s.length) {
            mapS[s[right]]++;
            while(check(mapS, mapT)) {
                if(right - left + 1 < minlen) {
                    minlen = right - left + 1;
                    start = left;
                    end = right;
                }
                mapS[s[left]]--;
                left++;
            }
            right++;
        }
        return start != -1 ? S.substring(start, end + 1) : "";
    }

    private boolean check(int[] mapS, int[] mapT) {
        for(int i = 'A'; i <= 'z'; i++) {
            if(mapT[i] > 0 && mapS[i] < mapT[i]) return false;
        }
        return true;
    }

    // TODO: 注意这种解法，没有相应的检查函数进行检查
    public String minWindow2(String S, String T) {
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        int[] need = new int[128];
        boolean[] isExist = new boolean[128];
        for (int i = 0; i < t.length; i++) {
            need[t[i]]++;
            isExist[t[i]] = true;
        }

        int count = t.length, minlen = s.length + 1, start = -1;
        int left = 0, right = 0;
        while(right < s.length) {
            if(isExist[s[right]]) {
                need[s[right]]--;
                if(need[s[right]] >= 0) count--;
            }
            while(count == 0) {
                if(right - left + 1 < minlen) {
                    minlen = right - left + 1;
                    start = left;
                }
                if(isExist[s[left]]) {
                    need[s[left]]++;
                    if(need[s[left]] > 0)  count++;
                }
                left++;
            }
            right++;
        }
        return start == -1 ? "" : S.substring(start, start + minlen);
    }
}
