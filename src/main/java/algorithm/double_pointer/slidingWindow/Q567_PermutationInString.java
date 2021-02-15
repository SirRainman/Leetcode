package algorithm.double_pointer.slidingWindow;

/**
 * 给定两个字符串s1和s2，写一个函数来判断 s2 是否包含 s1的排列。
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 *
 * 示例1:
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 *
 * 示例2:
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *
 * 注意：
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutation-in-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q567_PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length();
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] map1 = new int[26];
        int[] map2 = new int[26];
        for(char c : str1) map1[c - 'a']++;

        int left = 0, right = 0;
        while(right < n2) {
            map2[str2[right] - 'a']++;
            if(right - left + 1 > n1) { // 向右前进一步
                map2[str2[left] - 'a']--;
                left++;
            }
            if(check(map1, map2)) return true;
            right++;
        }
        return false;
    }

    public boolean check(int[] map1, int[] map2) {
        for(int i = 0; i < map1.length; i++) {
            if(map1[i] != map2[i]) return false;
        }
        return true;
    }
}
