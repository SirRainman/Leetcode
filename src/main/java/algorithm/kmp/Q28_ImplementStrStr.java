package algorithm.kmp;

/**
 * 实现strStr()函数。
 * 给定一个haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回 -1。
 *
 * 示例 1:
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 *
 * 示例 2:
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 *
 * 说明:
 * 当needle是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 对于本题而言，当needle是空字符串时我们应当返回 0 。这与C语言的strstr()以及 Java的indexOf()定义相符。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-strstr
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q28_ImplementStrStr {
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() < 1) return 0;
        return kmp(haystack, needle);
    }

    private static int kmp(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        int[] next = getNext(p, pLen);

        int res = -1;
        int i = 0, j = 0;
        while (i < sLen && j < pLen) {
            if (j == -1 || s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
            if (j == pLen) return i - pLen;
        }
        return -1;
    }

    private static int[] getNext(String p, int len) {
        int[] next = new int[len];
        next[0] = -1;

        int i = 0, k = -1;
        while (i < len - 1) {
            if (k == -1 || p.charAt(i) == p.charAt(k)) {
                i++;
                k++;
                next[i] = k;
            } else {
                k = next[k];
            }
        }

        return next;
    }

    private static int kmp2(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        int[] next = getNext(p, pLen);

        int res = -1;
        for (int i = 0, j = 0; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != p.charAt(j)) j = next[j - 1];
            if (s.charAt(i) == p.charAt(j)) j++;
            if (j == p.length()) return i - j + 1;
        }
        return -1;
    }

    private static int[] getNext2(String p, int len) {
        int[] next = new int[len];
        for (int i = 1, j = 0; i < p.length(); i++) {
            while (j > 0 && p.charAt(i) != p.charAt(j)) j = next[j - 1];
            if (p.charAt(j) == p.charAt(i)) j++;
            next[i] = j;
        }
        return next;
    }
}
