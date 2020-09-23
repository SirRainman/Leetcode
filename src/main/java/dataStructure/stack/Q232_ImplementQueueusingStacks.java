package dataStructure.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 使用栈实现队列的下列操作：
 *
 * push(x) -- 将一个元素放入队列的尾部。
 * pop() -- 从队列首部移除元素。
 * peek() -- 返回队列首部的元素。
 * empty() -- 返回队列是否为空。
 * 
 *
 * 示例:
 *
 * MyQueue dataStructure.queue = new MyQueue();
 *
 * dataStructure.queue.push(1);
 * dataStructure.queue.push(2);
 * dataStructure.queue.peek();  // 返回 1
 * dataStructure.queue.pop();   // 返回 1
 * dataStructure.queue.empty(); // 返回 false
 * 
 *
 * 说明:
 *
 * 你只能使用标准的栈操作 -- 也就是只有push to top,peek/pop from top,size, 和is empty操作是合法的。
 * 你所使用的语言也许不支持栈。你可以使用 dataStructure.list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 * 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-queue-using-stacks
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q232_ImplementQueueusingStacks {


    Deque<Integer> in;
    Deque<Integer> out;

    /** Initialize your data structure here. */
    public Q232_ImplementQueueusingStacks() {
        in = new LinkedList<>();
        out = new LinkedList<>();
    }

    /** Push element x to the back of dataStructure.queue. */
    public void push(int x) {
        in.push(x);
    }

    public void reverse() {
        if(out.isEmpty()) {
            while(!in.isEmpty()) {
                out.push(in.pop());
            }
        }
    }

    /** Removes the element from in front of dataStructure.queue and returns that element. */
    public int pop() {
        reverse();
        return out.pop();
    }

    /** Get the front element. */
    public int peek() {
        reverse();
        return out.peek();
    }

    /** Returns whether the dataStructure.queue is empty. */
    public boolean empty() {
        return out.isEmpty() && in.isEmpty();
    }
}
