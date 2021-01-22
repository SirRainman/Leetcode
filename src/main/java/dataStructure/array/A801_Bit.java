package dataStructure.array;

import com.sun.xml.internal.bind.v2.TODO;

import java.util.Scanner;

/**
 * 给定一个长度为n的数列，请你求出数列中每个数的二进制表示中1的个数。
 *
 * 输入格式
 * 第一行包含整数n。
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
    // TODO: java 中 >> 和 >>> 的含义是不一样的
    //  >> 是有符号的右移
    //  >>> 是无符号的右移
    public static void main(String[] args ) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        while(len-- > 0) {
            int x = in.nextInt(), ans = 0;
            while(x > 0) {
                // TODO: 判断x 第k位是否是1    (x >> k) & 1
                // if((x & 1) == 1) ans++;
                // x = x >>> 1;

                // TODO: 每次减去x的最后一位1所对应的数值
                x -= lowbit(x);
                ans++;
            }
            System.out.print(ans + " ");
        }
    }

    // TODO：解法一
    //  n           = 1010100
    //  n - 1       = 1010011
    //  n & (n - 1) = 1010000
    public int hammingWeight(int n) {
        int res = 0;
        while(n != 0) {
            res++;
            n &= n - 1;
        }
        return res;
    }

    // TODO：解法二
    //  判断最右边的一位1在什么位置
    //  原码  x        = 1010...1000
    //  反码 ~x        = 0101...0111
    //  补码 ~x + 1    = 0101...1000
    //  x & ( ~x + 1) = 0000...1000
    //  java中 -x = (~x + 1)
    public static int lowbit(int x) {
        // TODO：
        //  1想一想什么是源码、反码、补码？
        //  2为什么计算机中要用-x表示~x+1
        return x & -x;
    }

    // TODO: 减掉最后一位1后的大小
    // 原码  x       = 1010...1000
    //      x - 1   = 1010...0111
    // x & (x - 1)  = 1010...0000
}
