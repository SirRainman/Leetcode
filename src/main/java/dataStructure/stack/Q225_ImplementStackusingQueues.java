package dataStructure.stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用队列实现栈的下列操作：
 *
 * push(x) -- 元素 x 入栈
 * pop() -- 移除栈顶元素
 * top() -- 获取栈顶元素
 * empty() -- 返回栈是否为空
 * 注意:
 *
 * 你只能使用队列的基本操作-- 也就是push to back, peek/pop from front, size, 和is empty这些操作是合法的。
 * 你所使用的语言也许不支持队列。你可以使用 dataStructure.list 或者 deque（双端队列）来模拟一个队列, 只要是标准的队列操作即可。
 * 你可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 pop 或者 top 操作）。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-stack-using-queues
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q225_ImplementStackusingQueues {

    Deque<Integer> queue;
    /** Initialize your data structure here. */
    public Q225_ImplementStackusingQueues() {
        queue = new LinkedList<>();
    }

    /** Push element x onto dataStructure.stack. */
    public void push(int x) {
        queue.offer(x);
        int size = queue.size();
        while(size-- > 1) {
            queue.offer(queue.poll());
        }
    }

    /** Removes the element on top of the dataStructure.stack and returns that element. */
    public int pop() {
        return queue.poll();
    }

    /** Get the top element. */
    public int top() {
        return queue.peekFirst();
    }

    /** Returns whether the dataStructure.stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}

class MyStack {
    private Queue<Integer> in;//输入队列
    private Queue<Integer> out;//输出队列

    public MyStack() {
        in = new LinkedList<>();
        out = new LinkedList<>();
    }

    public void push(int x) {
        in.offer(x);
        // 将out队列中元素全部转给in队列
        while(!out.isEmpty())
            in.offer(out.poll());
        // 交换in和out,使得in队列没有在push()的时候始终为空队列
        Queue temp = in;
        in = out;
        out = temp;
    }

    public int pop() {
        return out.poll();
    }

    public int top() {
        return out.peek();
    }

    public boolean empty() {
        return out.isEmpty();
    }
}