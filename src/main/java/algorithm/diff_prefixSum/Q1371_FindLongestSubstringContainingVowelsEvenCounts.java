package algorithm.diff_prefixSum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: Leetcode
 * @description:
 * 给你一个字符串s，请你返回满足以下条件的最长子字符串的长度：每个元音字母，即'a'，'e'，'i'，'o'，'u' ，在子字符串中都恰好出现了偶数次。
 *
 * 示例 1：
 * 输入：s = "eleetminicoworoep"
 * 输出：13
 * 解释：最长子字符串是 "leetminicowor" ，它包含 e，i，o各 2 个，以及 0 个 a，u 。
 *
 * 示例 2：
 * 输入：s = "leetcodeisgreat"
 * 输出：5
 * 解释：最长子字符串是 "leetc" ，其中包含 2 个 e 。
 *
 * 示例 3：
 * 输入：s = "bcbcbc"
 * 输出：6
 * 解释：这个示例中，字符串 "bcbcbc" 本身就是最长的，因为所有的元音 a，e，i，o，u 都出现了 0 次。
 *
 * 提示：
 * 1 <= s.length <= 5 x 10^5
 * s只包含小写英文字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-longest-substring-containing-vowels-in-even-counts
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-06-04 14:59
 **/
public class Q1371_FindLongestSubstringContainingVowelsEvenCounts {
    // TODO: 前缀和 + 哈希表
    //  遇到奇偶个数校验，想到XOR
    //  遇到有限的参数（小于20个）表状态，想到状态压缩 （bitmask）
    //  遇到求最长的连续子串使得和为k想到，前缀和 加哈希表记录第一次出现某一状态的位置。
    public int findTheLongestSubstring1(String s) {
        int[] pos = new int[1 << 5];
        Arrays.fill(pos, -2);
        pos[0] = -1;
        int state = 0, res = 0;
        for(int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'a': state ^= 1 << 0; break;
                case 'e': state ^= 1 << 1; break;
                case 'i': state ^= 1 << 2; break;
                case 'o': state ^= 1 << 3; break;
                case 'u': state ^= 1 << 4; break;
            }
            if(pos[state] != -2) res = Math.max(res, i - pos[state]);
            else pos[state] = i;
        }
        return res;
    }

    private static final String VOWELS = "aeiou";

    public int findTheLongestSubstring(String s) {
        Map<Integer, Integer> map = new HashMap<>();
        int size = s.length();
        int state = 0; // (00000)
        int maxSize = 0;
        map.putIfAbsent(0, -1);
        for (int i = 0; i < size; i ++){
            for (int k = 0; k < VOWELS.length(); k++){
                if (s.charAt(i) == VOWELS.charAt(k)){
                    state ^= (1 << (VOWELS.length() - k - 1));
                    break;
                }
            }
            if (map.containsKey(state)){
                maxSize = Math.max(maxSize, i - map.get(state));
            }
            map.putIfAbsent(state, i); // 注意这里不是直接放进去，而是通过判断是否存在进行放置
        }

        return maxSize;
    }
}
