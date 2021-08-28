package dataStructure.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @program: Leetcode
 * @description:
 * 给出一个字符串s（仅含有小写英文字母和括号）。
 * 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
 * 注意，您的结果中 不应 包含任何括号。
 *
 * 示例 1：
 * 输入：s = "(abcd)"
 * 输出："dcba"
 *
 * 示例 2：
 * 输入：s = "(u(love)i)"
 * 输出："iloveu"
 *
 * 示例 3：
 * 输入：s = "(ed(et(oc))el)"
 * 输出："leetcode"
 *
 * 示例 4：
 * 输入：s = "a(bcdefghijkl(mno)p)q"
 * 输出："apmnolkjihgfedcbq"
 *
 * 提示：
 * 0 <= s.length <= 2000
 * s 中只有小写英文字母和括号
 * 我们确保所有括号都是成对出现的
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-substrings-between-each-pair-of-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-05-27 10:27
 **/
public class Q1190_ReverseSubstringsBetweenEachPairOfParentheses {
    public String reverseParentheses1(String s) {
        char[] str = s.toCharArray();
        Deque<Character> stack = new LinkedList<>();
        Deque<Character> helper = new LinkedList<>();

        for(int i = 0; i < str.length; i++) {
            if(str[i] != ')') stack.push(str[i]);
            else {
                while(!stack.isEmpty() && stack.peek() != '(') helper.offer(stack.pop());
                stack.pop();
                while(!helper.isEmpty()) stack.push(helper.poll());
            }
        }

        StringBuffer sb = new StringBuffer();
        while(!stack.isEmpty()) sb.insert(0, stack.pop());
        return sb.toString();
    }

    public String reverseParentheses2(String s) {
        char[] str = s.toCharArray();
        Deque<String> stack = new LinkedList<>();
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < str.length; i++) {
            if(str[i] == '(') {
                stack.push(sb.toString());
                sb.setLength(0);
            } else if(str[i] == ')') {
                sb.reverse();
                sb.insert(0, stack.pop());
            } else {
                sb.append(str[i]);
            }
        }
        return sb.toString();
    }

    public String reverseParentheses(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        int[] pairs = new int[n];
        Deque<Integer> stack = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            if(str[i] == '(') stack.push(i);
            else if(str[i] == ')') {
                int j = stack.pop();
                pairs[j] = i;
                pairs[i] = j;
            }
        }

        StringBuffer sb = new StringBuffer();
        int index = 0, step = 1;
        while(index < n) {
            if(str[index] == '(' || str[index] == ')') {
                index = pairs[index];
                step = -step;
            } else {
                sb.append(str[index]);
            }
            index += step;
        }
        return sb.toString();
    }
}
