package dataStructure.stack.monotonousStack;

import java.util.Scanner;

/**
 * 给定一个长度为N的整数数列，输出每个数左边第一个比它小的数，如果不存在则输出-1。
 *
 * 输入格式
 * 第一行包含整数N，表示数列长度。
 *
 * 第二行包含N个整数，表示整数数列。
 *
 * 输出格式
 * 共一行，包含N个整数，其中第i个数表示第i个数的左边第一个比它小的数，如果不存在则输出-1。
 *
 * 数据范围
 * 1≤N≤105
 * 1≤数列中元素≤109
 * 输入样例：
 * 5
 * 3 4 2 7 5
 * 输出样例：
 * -1 3 -1 2 2
 *
 * https://www.acwing.com/problem/content/description/832/
 */
public class A830_PreviousSmallerNum {

    // TODO:单调栈，栈中只存离他最近，且最小的值。
    //  每个元素只进栈一次，出栈一次，最多是O(2n)
    //  1.先想暴力应该怎么做
    //  2.再想是否有单调性在里面（把其中没有用的元素删掉）
    //  3.优化，求极值

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] stack = new int[100010];
        int top = -1;
        int len = in.nextInt();
        while(len-- > 0) {
            int x = in.nextInt();
            while(top > -1 && stack[top] >= x) top--;
            if(top == -1) {
                System.out.print("-1 ");
            } else {
                System.out.print(stack[top] + " ");
            }
            stack[++top] = x;
        }
    }
}
