package algorithm.double_pointer.slidingWindow;

/**
 * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换k次。
 * 在执行上述操作后，找到包含重复字母的最长子串的长度。
 * 注意：字符串长度 和 k 不会超过10^4。
 *
 * 示例 1：
 * 输入：s = "ABAB", k = 2
 * 输出：4
 * 解释：用两个'A'替换为两个'B',反之亦然。
 *
 * 示例 2：
 * 输入：s = "AABABBA", k = 1
 * 输出：4
 * 解释：
 * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 * 子串 "BBBB" 有最长重复字母, 答案为 4。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-repeating-character-replacement
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ424_LongestRepeatingCharacterReplacement {
    // TODO：想一想为什么窗口的大小是一直在变大的？？
    //  https://leetcode-cn.com/problems/longest-repeating-character-replacement/solution/ti-huan-hou-de-zui-chang-zhong-fu-zi-fu-eaacp/
    //      1 如果长度为 L 的子串不符合题目的要求，那么左边界固定，长度更长的子串也不符合题目的要求。
    //      2 maxCount 在内层循环「左边界向右移动一个位置」的过程中，没有维护它的定义
    //  窗口的大小是一直在变大的
    public int characterReplacement(String s, int k) {
        char[] str = s.toCharArray();
        int n = s.length();
        int[] count = new int[26];
        int left = 0, right = 0;
        int maxCount = 0;
        while(right < n) {
            count[str[right] - 'A']++;
            maxCount = Math.max(maxCount, count[str[right] - 'A']); // 这就意味着窗口的大小是一直在变大的，而不是变小的
            if(right - left + 1 - maxCount > k) { // 除了出现次数最多的那一类字符之外，剩余的字符（即非最长重复字符）数量不超过 k 个。
                count[str[left] - 'A']--;
                left++;
            }
            right++;
        }
        return right - left;
    }

    // TODO: 重点看上面的做法
    public int characterReplacement1(String s, int k) {
        char[] str = s.toCharArray();
        int n = str.length;
        int maxLen = 0, changed = 0;
        int left = 0, right = 0;
        int[] count = new int[26];
        while (right < n) {
            count[str[right] - 'A']++;
            while (check(count, k)) {
                count[str[left] - 'A']--;
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        return maxLen;
    }

    public boolean check(int[] count, int k) {
        int sum = 0, maxCount = 0;
        for (int c : count) {
            sum += c;
            maxCount = Math.max(c, maxCount);
        }
        return sum - maxCount > k;
    }
}