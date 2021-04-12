package dataStructure.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @program: Leetcode
 * @description:
 * 根据 逆波兰表示法，求表达式的值。
 * 有效的算符包括+、-、*、/。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 *
 * 说明：
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 * 
 *
 * 示例1：
 * 输入：tokens = ["2","1","+","3","*"]
 * 输出：9
 * 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/evaluate-reverse-polish-notation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-04-01 16:13
 **/
public class Q150_EvaluateReversePolishNotation {
    // TODO: 注意出栈之后的操作顺序
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList<>();
        for(String token : tokens) {
            if(token.equals("+")) {
                int a = stack.pop(), b = stack.pop();
                stack.push(a + b);
            } else if(token.equals("-")) {
                int a = stack.pop(), b = stack.pop();
                stack.push(b - a);
            } else if(token.equals("*")) {
                int a = stack.pop(), b = stack.pop();
                stack.push(a * b);
            } else if (token.equals("/")) {
                int a = stack.pop(), b = stack.pop();
                stack.push(b / a);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.peek();
    }
}
