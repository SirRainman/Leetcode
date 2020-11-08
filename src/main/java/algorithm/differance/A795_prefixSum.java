package algorithm.differance;

import java.util.Scanner;

/**
 * 输入一个长度为n的整数序列。
 *
 * 接下来再输入m个询问，每个询问输入一对l, r。
 *
 * 对于每个询问，输出原序列中从第l个数到第r个数的和。
 *
 * 输入格式
 * 第一行包含两个整数n和m。
 *
 * 第二行包含n个整数，表示整数数列。
 *
 * 接下来m行，每行包含两个整数l和r，表示一个询问的区间范围。
 *
 * 输出格式
 * 共m行，每行输出一个询问的结果。
 *
 * 数据范围
 * 1≤l≤r≤n,
 * 1≤n,m≤100000,
 * −1000≤数列中元素的值≤1000
 * 输入样例：
 * 5 3
 * 2 1 3 6 4
 * 1 2
 * 1 3
 * 2 4
 * 输出样例：
 * 3
 * 6
 * 10
 *
 * https://www.acwing.com/problem/content/797/
 */
public class A795_prefixSum {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        // TODO: 想一想为什么从 1开始存储
        //  一般这么进行处理的时候，就是考虑到了边界！！！
        //  因为s[i] 是 0 - i的所有的数的和 ans = sum[end] - sum[start - 1]，所以当start = 0的时候，要进行特判一下
        int[] nums = new int[len + 1];
        int[] s = new int[len + 1];
        int times = in.nextInt();
        for (int i = 1; i <= len; i++) {
            nums[i] = in.nextInt();
            s[i] = s[i - 1] + nums[i];
        }

        while (times-- > 0) {
            int start = in.nextInt(), end = in.nextInt();
            System.out.println(s[end] - s[start - 1]);
        }
    }

}
