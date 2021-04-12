package algorithm.AAAcontest.ms;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @program: Leetcode
 * @description:
 * A B可以互相抵消
 * C D可以互相抵消
 * 输入一个字符串，输出抵消后的字符串
 * @author: Rain
 * @create: 2021-03-26 22:08
 **/
public class T3 {
    public static void main(String[] args) {
        String S = "CABABD";
        System.out.println(solution(S));
    }

    public static String solution(String S) {
        Deque<Character> stack = new LinkedList<>();
        Map<Character, Character> map = new HashMap<>();
        map.put('A', 'B');
        map.put('B', 'A');
        map.put('C', 'D');
        map.put('D', 'C');
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (!stack.isEmpty() && map.get(stack.peekLast()) == c) stack.removeLast();
            else stack.addLast(c);
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) res.append(stack.removeFirst());
        return res.toString();
    }

    public String solution1(String S) {
        Deque<Character> s = new LinkedList<>();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (!s.isEmpty() && ((c ^ s.peekLast()) & 3) == 3) s.removeLast();
            else s.addLast(c);
        }
        StringBuilder res = new StringBuilder();
        while (!s.isEmpty()) res.append(s.removeFirst());
        return res.toString();
    }
}