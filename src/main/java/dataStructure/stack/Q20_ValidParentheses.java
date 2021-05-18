package dataStructure.stack;

import java.util.*;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 * 输入: "()[]{}"
 * 输出: true
 *
 * 示例4:
 * 输入: "([)]"
 * 输出: false
 *
 * 提示：
 * 1 <= s.length <= 104
 * s 仅由括号 '()[]{}' 组成
 *
 * https://leetcode-cn.com/problems/valid-parentheses/
 * */
public class Q20_ValidParentheses {
    public boolean isValid(String s) {
        if (s.length() % 2 == 1) return false;
        char[] str = s.toCharArray();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        Deque<Character> stack = new LinkedList<>();
        for(char c : str) {
            if(map.containsKey(c)) {
                if(stack.isEmpty() || stack.pop() != map.get(c)) return false;
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
