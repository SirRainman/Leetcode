package dataStructure.array.simulate;

import java.util.Scanner;

/**
 * 实现一个栈，栈初始为空，支持四种操作：
 * (1) “push x” – 向栈顶插入一个数x；
 * (2) “pop” – 从栈顶弹出一个数；
 * (3) “empty” – 判断栈是否为空；
 * (4) “query” – 查询栈顶元素。
 * 现在要对栈进行M个操作，其中的每个操作3和操作4都要输出相应的结果。
 *
 * 输入格式
 * 第一行包含整数M，表示操作次数。
 * 接下来M行，每行包含一个操作命令，操作命令为”push x”，”pop”，”empty”，”query”中的一种。
 *
 * 输出格式
 * 对于每个”empty”和”query”操作都要输出一个查询结果，每个结果占一行。
 * 其中，”empty”操作的查询结果为“YES”或“NO”，”query”操作的查询结果为一个整数，表示栈顶元素的值。
 *
 * 数据范围
 * 1≤M≤100000,
 * 1≤x≤109
 * 所有操作保证合法。
 *
 * 输入样例：
 * 10
 * push 5
 * query
 * push 6
 * pop
 * query
 * pop
 * empty
 * push 4
 * query
 * empty
 * 输出样例：
 * 5
 * 5
 * YES
 * 4
 * NO
 *
 *
 * https://www.acwing.com/problem/content/830/
 */

class ArrayStack {
    int[] e;
    int top;

    public ArrayStack(int size) {
        e = new int[size + 1];
        // TODO:
        //  1.注意top的初始值
        //  2.注意判断是否是空的方法
        //  3 注意入栈和出栈的方法
        top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int x) {
        e[++top] = x;
    }

    public int pop() {
        return e[top--];
    }

    public int top() {
        return e[top];
    }
}

public class A828_Array_Stack {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int times = in.nextInt();
        ArrayStack stack = new ArrayStack(100010);
        while(times-- > 0) {
            String op = in.next();
            if(op.compareTo("push") == 0) {
                int x = in.nextInt();
                stack.push(x);
            }else if (op.compareTo("pop") == 0) {
                stack.pop();
            }else if (op.compareTo("empty") == 0) {
                System.out.println(stack.isEmpty() ? "Yes" : "NO");
            }else if (op.compareTo("query") == 0) {
                System.out.println(stack.top());
            }
        }
    }
}
