package dataStructure.array;

import java.util.Scanner;

/**
 * 给定一个长度为n的数列，请你求出数列中每个数的二进制表示中1的个数。
 *
 * 输入格式
 * 第一行包含整数n。
 *
 * 第二行包含n个整数，表示整个数列。
 *
 * 输出格式
 * 共一行，包含n个整数，其中的第 i 个数表示数列中的第 i 个数的二进制表示中1的个数。
 *
 * 数据范围
 * 1≤n≤100000,
 * 0≤数列中元素的值≤109
 * 输入样例：
 * 5
 * 1 2 3 4 5
 * 输出样例：
 * 1 1 2 1 2
 *
 * https://www.acwing.com/problem/content/803/
 */
public class A801_Bit {
    public static void main(String[] args ) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        while(len-- > 0) {
            int x = in.nextInt(), ans = 0;
            while(x > 0) {
                // TODO: 判断x 第k位是否是1    (x >> k) & 1
                // if((x & 1) == 1) ans++;
                // x = x >> 1;

                // 每次减去x的最后一位1所对应的数值
                x -= lowbit(x);
                ans++;
            }
            System.out.print(ans + " ");
        }
    }

    public static int lowbit(int x) {
        // 原码  x       = 1010...1000
        // 反码 ~x       = 0101...0111
        // 补码 ~x + 1   = 0101...1000
        //      x&(~x+1) = 0000...1000
        // 在计算机语言 中-x 的值， 其实就是在x的值的基础上进行按位取反(~x)之后在增加1所得
        // TODO：
        //  1想一想什么是源码、反码、补码？
        //  2为什么计算机中要用-x表示~x+1
        return x & -x;
    }
}
