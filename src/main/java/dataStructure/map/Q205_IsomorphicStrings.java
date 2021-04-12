package dataStructure.map;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: Leetcode
 * @description:
 * 给定两个字符串s和t，判断它们是否是同构的。
 * 如果s中的字符可以按某种映射关系替换得到t，那么这两个字符串是同构的。
 * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。
 * 不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。
 *
 * 示例 1:
 * 输入：s = "egg", t = "add"
 * 输出：true
 *
 * 示例 2：
 * 输入：s = "foo", t = "bar"
 * 输出：false
 *
 * 示例 3：
 * 输入：s = "paper", t = "title"
 * 输出：true
 *
 * 提示：
 * 可以假设s和 t 长度相同。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/isomorphic-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-03-29 20:20
 **/
public class Q205_IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        char[] a = s.toCharArray();
        char[] b = t.toCharArray();
        Map<Character, Character> a2b = new HashMap<>();
        Map<Character, Character> b2a = new HashMap<>();
        for(int i = 0; i < a.length; i++) {
            if(a2b.containsKey(a[i]) && a2b.get(a[i]) != b[i]) return false;
            if(b2a.containsKey(b[i]) && b2a.get(b[i]) != a[i]) return false;
            a2b.put(a[i], b[i]);
            b2a.put(b[i], a[i]);
        }
        return true;
    }
}
