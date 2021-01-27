package algorithm.diff_prefixSum;

/**
 * 给你两个字符串 a 和 b ，二者均由小写字母组成。一步操作中，你可以将 a 或 b 中的 任一字符 改变为 任一小写字母 。
 * 操作的最终目标是满足下列三个条件 之一 ：
 *      a 中的 每个字母 在字母表中 严格小于 b 中的 每个字母 。
 *      b 中的 每个字母 在字母表中 严格小于 a 中的 每个字母 。
 *      a 和 b 都 由 同一个 字母组成。
 * 返回达成目标所需的 最少 操作数。
 *
 * 示例 1：
 * 输入：a = "aba", b = "caa"
 * 输出：2
 * 解释：满足每个条件的最佳方案分别是：
 * 1) 将 b 变为 "ccc"，2 次操作，满足 a 中的每个字母都小于 b 中的每个字母；
 * 2) 将 a 变为 "bbb" 并将 b 变为 "aaa"，3 次操作，满足 b 中的每个字母都小于 a 中的每个字母；
 * 3) 将 a 变为 "aaa" 并将 b 变为 "aaa"，2 次操作，满足 a 和 b 由同一个字母组成。
 * 最佳的方案只需要 2 次操作（满足条件 1 或者条件 3）。
 *
 * 示例 2：
 * 输入：a = "dabadd", b = "cda"
 * 输出：3
 * 解释：满足条件 1 的最佳方案是将 b 变为 "eee" 。
 * 
 * 提示：
 * 1 <= a.length, b.length <= 105
 * a 和 b 只由小写字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/change-minimum-characters-to-satisfy-one-of-three-conditions
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q5662_MinimumChangeToSatisfyThreeConditions {
    public int minCharacters(String a, String b) {
        char[] A = a.toCharArray(), B = b.toCharArray();
        int la = A.length, lb = B.length;
        int[] countA = new int[26]; // 统计每个字母出现的次数
        int[] countB = new int[26];
        for(char c : A) countA[c - 'a']++;
        for(char c : B) countB[c - 'a']++;

        int operation = Integer.MAX_VALUE, preA = 0, preB = 0; // preA A中前i个字母总共出现的次数
        for(int i = 0; i < 25; i++) {
            preA += countA[i];
            preB += countB[i];
            // 修改成相同的字母的操作数
            operation = Math.min(operation, la - countA[i] + lb - countB[i]);
            // 将A修改成全部小于B 或者 将B修改成全部小于A
            operation = Math.min(operation, Math.min(la - preA + preB, lb - preB + preA));
        }
        operation = Math.min(operation, la - countA[25] + lb - countB[25]);
        return operation;
    }
}
