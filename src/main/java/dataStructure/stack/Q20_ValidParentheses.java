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
 *
 * 输入: "()"
 * 输出: true
 * 示例2:
 *
 * 输入: "()[]{}"
 * 输出: true
 * 示例3:
 *
 * 输入: "(]"
 * 输出: false
 * 示例4:
 *
 * 输入: "([)]"
 * 输出: false
 * 示例5:
 *
 * 输入: "{[]}"
 * 输出: true
 *
 * */

public class Q20_ValidParentheses {
    public boolean isValid(String s) {
        if (s.length() % 2 == 1) return false;
//        Stack<Character> dataStructure.stack = new Stack<Character>();
        Deque<Character> stack = new LinkedList<Character>();
        Map map = new HashMap<Character, Character>();
        map.put('}', '{');
        map.put(']', '[');
        map.put(')', '(');
        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);
            if(map.containsKey(c)) {
                if (stack.isEmpty() || stack.peek() != map.get(c)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new Q20_ValidParentheses().isValid("){"));
    }

    public boolean backspaceCompare(String S, String T) {

        Deque<Character> s1 = new LinkedList<>();
        Deque<Character> s2 = new LinkedList<>();

        for(int i = 0; i < S.length(); i++) {
            if(S.charAt(i) != '#') {
                s1.push(S.charAt(i));
            } else {
                if(!s1.isEmpty()) {
                    s1.pop();
                }
            }
        }

        for(int i = 0; i < T.length(); i++) {
            if(T.charAt(i) != '#') {
                s2.push(T.charAt(i));
            } else {
                if(!s2.isEmpty()) {
                    s2.pop();
                }
            }
        }

        if(s1.size() != s2.size()) {
            return false;
        } else {
            while(!s1.isEmpty()) {
                if(s1.pop() != s2.pop()) {
                    return false;
                }
            }
            return true;
        }

    }
}
