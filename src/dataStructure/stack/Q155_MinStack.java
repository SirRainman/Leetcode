package dataStructure.stack;

import java.util.Stack;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-stack
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * */

public class Q155_MinStack {

    Stack<Integer> stack = new Stack<Integer>();
    Stack<Integer> minStack = new Stack<Integer>();

    /** initialize your data structure here. */
    public Q155_MinStack() {

    }

    public void push(int x) {
        stack.push(x);
        if(minStack.isEmpty() || minStack.peek() >= x) {
            minStack.push(x);
        }
     }

    public void pop() {
        if (stack.isEmpty() == false) {
            int top = stack.pop();
            if (top == minStack.peek()) {
                minStack.pop();
            }
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
