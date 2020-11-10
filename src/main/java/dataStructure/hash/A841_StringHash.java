package dataStructure.hash;

import java.util.Scanner;

/**
 *给定一个长度为n的字符串，再给定m个询问，每个询问包含四个整数l1,r1,l2,r2，请你判断[l1,r1]和[l2,r2]这两个区间所包含的字符串子串是否完全相同。
 *
 * 字符串中只包含大小写英文字母和数字。
 *
 * 输入格式
 * 第一行包含整数n和m，表示字符串长度和询问次数。
 *
 * 第二行包含一个长度为n的字符串，字符串中只包含大小写英文字母和数字。
 *
 * 接下来m行，每行包含四个整数l1,r1,l2,r2，表示一次询问所涉及的两个区间。
 *
 * 注意，字符串的位置从1开始编号。
 *
 * 输出格式
 * 对于每个询问输出一个结果，如果两个字符串子串完全相同则输出“Yes”，否则输出“No”。
 *
 * 每个结果占一行。
 *
 * 数据范围
 * 1≤n,m≤105
 * 输入样例：
 * 8 3
 * aabbaabb
 * 1 3 5 7
 * 1 3 6 8
 * 1 2 1 2
 * 输出样例：
 * Yes
 * No
 * Yes
 *
 * https://www.acwing.com/problem/content/843/
 */
public class A841_StringHash {
    static int P = 131; //String.hashCode()中是31 yxc推荐131，13331
    static int[] hash, p;// hash[k]存储字符串前k个字母的哈希值, p[k]存储 P^k mod 2^64

    static int getHash(int left , int right) {
        // TODO：为什么不是hash[right] * hash[left - 1]????
        return hash[right] - hash[left - 1] * p[right - left + 1];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt(), times = in.nextInt();
        char[] str = in.next().toCharArray();

        hash = new int[len + 1];
        p = new int[len + 1];
        p[0] = 1;
        for(int i = 1; i <= len; i++) {
            hash[i] = hash[i - 1] * P + str[i - 1]; // 从左至当前字符的字符串的hash值
            p[i] = p[i - 1] * P;
        }

        while(times-- > 0) {
            int l1 = in.nextInt(), r1= in.nextInt(), l2= in.nextInt(), r2= in.nextInt();
            System.out.println(getHash(l1, r1) == getHash(l2, r2) ? "Yes" : "No");
        }
    }
}
