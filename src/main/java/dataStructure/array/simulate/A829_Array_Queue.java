package dataStructure.array.simulate;

import java.util.Scanner;

/**
 * 实现一个队列，队列初始为空，支持四种操作：
 * (1) “push x” – 向队尾插入一个数x；
 * (2) “pop” – 从队头弹出一个数；
 * (3) “empty” – 判断队列是否为空；
 * (4) “query” – 查询队头元素。
 *
 * 现在要对队列进行M个操作，其中的每个操作3和操作4都要输出相应的结果。
 *
 * 输入格式
 * 第一行包含整数M，表示操作次数。
 * 接下来M行，每行包含一个操作命令，操作命令为”push x”，”pop”，”empty”，”query”中的一种。
 *
 * 输出格式
 * 对于每个”empty”和”query”操作都要输出一个查询结果，每个结果占一行。
 * 其中，”empty”操作的查询结果为“YES”或“NO”，”query”操作的查询结果为一个整数，表示队头元素的值。
 *
 * 数据范围
 * 1≤M≤100000,
 * 1≤x≤109,
 * 所有操作保证合法。
 *
 * 输入样例：
 * 10
 * push 6
 * empty
 * query
 * pop
 * empty
 * push 3
 * push 4
 * pop
 * query
 * push 6
 * 输出样例：
 * NO
 * 6
 * YES
 * 4
 */

class ArrayQueue1 {
    int[] e;
    int front, rear;

    // TODO：
    //  1 注意front rear的初始值
    //  2 注意判空的方法，想一想循环队列应该怎么判断是否是空/满
    public ArrayQueue1(int size) {
        e = new int[size];
        front = 0;
        rear = -1;
    }

    public void push(int x) {
        e[++rear] = x;
    }

    public int pop() {
        return e[front++];
    }

    public boolean isEmpty() {
        return front > rear;
    }

    public int query() {
        return e[front];
    }
}

// TODO: 更推荐这种做法，因为这种做法和循环队列的判断方法正好可以对上

// TODO：循环队列的队空队判断和一般队队列不一样
//  循环队列中，队空判断 front == rear，队满判断 (rear+1) % n == front
//  由于入队时尾指针向前追赶头指针；出队时头指针向前追赶尾指针，造成队空和队满时头尾指针均相等。
//  因此，无法通过条件front==rear来判别队列是"空"还是"满"。
//  解决这个问题的方法至少有两种：
//  1 另设一布尔变量以区别队列的空和满；
//  2 另一种方式就是数据结构常用的，牺牲掉一个单位的空间：
//      队满时：(rear+1)%n==front，n为队列长度（所用数组大小），
//      由于rear，front均为所用空间的指针，循环只是逻辑上的循环，所以需要求余运算。

class ArrayQueue{
    int[] e;
    int front, rear;

    public ArrayQueue(int size) {
        e = new int[size];
        front = rear = 0;
    }

    public void push(int x) {
        e[rear++] = x;
    }

    public int pop() {
        return e[front++];
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public int query() {
        return e[front];
    }
}

public class A829_Array_Queue {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayQueue queue = new ArrayQueue(100010);
        int len = in.nextInt();
        while(len-- > 0) {
            String op = in.next();
            if(op.compareTo("push") == 0) {
                queue.push(in.nextInt());
            } else if (op.compareTo("pop") == 0) {
                queue.pop();
            } else if (op.compareTo("empty") == 0) {
                System.out.println(queue.isEmpty()?"YES":"NO");
            } else if (op.compareTo("query") == 0) {
                System.out.println(queue.query());
            }
        }
    }
}
