package algorithm.AAAcontest.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @program: Leetcode
 * @description:
 * 给你一个字符串 s ，它包含一些括号对，每个括号中包含一个 非空 的键。
 * 比方说，字符串 "(name)is(age)yearsold" 中，有 两个 括号对，分别包含键 "name" 和 "age" 。
 *
 * 你知道许多键对应的值，这些关系由二维字符串数组 knowledge 表示，其中 knowledge[i] = [keyi, valuei] ，表示键 keyi 对应的值为 valuei 。
 * 你需要替换 所有 的括号对。当你替换一个括号对，且它包含的键为 keyi 时，你需要：
 * 将 keyi 和括号用对应的值 valuei 替换。
 *
 * 如果从 knowledge 中无法得知某个键对应的值，你需要将 keyi 和括号用问号 "?" 替换（不需要引号）。
 * knowledge 中每个键最多只会出现一次。s 中不会有嵌套的括号。
 * 请你返回替换 所有 括号对后的结果字符串。
 *
 * @author: Rain
 * @create: 2021-03-28 11:19
 **/
public class Q5714_ReplaceWordInString {
    public static String evaluate(String s, List<List<String>> knowledge) {
        HashMap<String, String> map = new HashMap<>();
        for(List<String> k : knowledge) map.put(k.get(0), k.get(1));
        StringBuilder res = new StringBuilder();
        StringBuilder content = new StringBuilder();
        char[] str = s.toCharArray();
        boolean isOpen = false;
        for(int i = 0; i < str.length; i++) {
            if(str[i] == '(') {
                isOpen = true;
            } else if(str[i] == ')') {
                if(map.containsKey(content.toString())) res.append(map.get(content.toString()));
                else res.append('?');
                content.delete(0, content.length());
                isOpen = false;
            } else if(isOpen) {
                content.append(str[i]);
            } else {
                res.append(str[i]);
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String s = "(name)is(age)yearsold";
        List<List<String>> k = new ArrayList<>();
        List<String> test = new ArrayList<>();
        test.add("name");
        test.add("bob");
        k.add(new ArrayList<>(test));
        test.clear();
        test.add("age");
        test.add("two");
        k.add(new ArrayList<>(test));
        System.out.println(evaluate(s, k));
    }
}
