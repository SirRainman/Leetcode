package algorithm.double_pointer.slidingWindow;

/**
 * @program: Leetcode
 * @description:
 * 给你一个字符串 s，它只包含三种字符 a, b 和 c 。
 * 请你返回 a，b 和 c 都至少出现过一次的子字符串数目。
 *
 * 示例 1：
 * 输入：s = "abcabc"
 * 输出：10
 * 解释：包含 a，b 和 c 各至少一次的子字符串为 "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" 和 "abc" (相同字符串算多次)。
 *
 * 示例 2：
 * 输入：s = "aaacb"
 * 输出：3
 * 解释：包含 a，b 和 c 各至少一次的子字符串为 "aaacb", "aacb" 和 "acb" 。
 *
 * 示例 3：
 * 输入：s = "abc"
 * 输出：1
 *
 * 提示：
 * 3 <= s.length <= 5 x 10^4
 * s只包含字符 a，b 和 c 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-substrings-containing-all-three-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author: Rain
 * @create: 2021-02-10 12:56
 **/
public class Q1358_NumberOfSubstringsContainingAllThreeCharacters {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        char[] str = s.toCharArray();
        int left = 0, right = 0, res = 0;
        int[] countMap = new int[3];
        while(right < n) {
            countMap[str[right] - 'a']++;
            while(countMap[0] >= 1 && countMap[1] >= 1 && countMap[2] >= 1) {
                res += n - right;
                countMap[str[left] - 'a']--;
                left++;
            }
            right++;
        }
        return res;
    }
}
