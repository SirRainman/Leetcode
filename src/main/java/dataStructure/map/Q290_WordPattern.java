package dataStructure.map;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: Leetcode
 * @description:
 * 给定一种规律 pattern和一个字符串str，判断 str 是否遵循相同的规律。
 * 这里的遵循指完全匹配，例如，pattern里的每个字母和字符串str中的每个非空单词之间存在着双向连接的对应规律。
 *
 * 示例1:
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 *
 * 示例 2:
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 *
 * 示例 3:
 * 输入: pattern = "aaaa", str = "dog cat cat dog"
 * 输出: false
 *
 * 示例4:
 * 输入: pattern = "abba", str = "dog dog dog dog"
 * 输出: false
 * 说明:
 * 你可以假设pattern只包含小写字母，str包含了由单个空格分隔的小写字母。 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-03-29 20:31
 **/
public class Q290_WordPattern {
    public boolean wordPattern(String pattern, String s) {
        char[] p = pattern.toCharArray();
        String[] word = s.split(" ");
        if(p.length != word.length) return false;
        Map<Character, String> p2word= new HashMap<>();
        Map<String, Character> word2p = new HashMap<>();
        for(int i = 0; i < p.length; i++) {
            if(p2word.containsKey(p[i]) && !p2word.get(p[i]).equals(word[i])) return false;
            if(word2p.containsKey(word[i]) && word2p.get(word[i]) != p[i]) return false;
            p2word.put(p[i], word[i]);
            word2p.put(word[i], p[i]);
        }
        return true;
    }
}
