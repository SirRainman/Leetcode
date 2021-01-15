package algorithm.math;

import java.util.Scanner;

/**
 * 给定n组ai,bi,pi，对于每组数据，求出ai^bi mod pi的值。
 *
 * 输入格式
 * 第一行包含整数n。
 * 接下来n行，每行包含三个整数ai,bi,pi。
 *
 * 输出格式
 * 对于每组数据，输出一个结果，表示abii mod pi的值。
 * 每个结果占一行。
 *
 * 数据范围
 * 1≤n≤100000,
 * 1≤ai,bi,pi≤2∗109
 *
 * 输入样例：
 * 2
 * 3 2 5
 * 4 3 9
 * 输出样例：
 * 4
 * 1
 * https://www.acwing.com/problem/content/877/
 */
public class A875_QuickMultiIndex {

    public static int qmi(int a, int b, int p) {
        if(b == 0) return 1;
        int res = qmi(a, b >> 1, p);
        if((b & 1) > 0)  return (int) ((long) res * res % p * a % p); // TODO: b 如果是奇数，多乘一个a
        else return (int) ((long) res * res % p); // TODO：b 如果是偶数
    }

    public static int qmi2(int a, int b, int p) {
        int res = 1;
        while(b > 0) {
            if((b & 1) > 0) res = (int)((long) res * a % p);
            a = (int) ((long) a * a % p);
            b = b >> 1;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        while(n-- > 0) {
            int a = in.nextInt(), b = in.nextInt(), p = in.nextInt();
            long res = qmi(a, b, p);
            System.out.println(res);
        }
    }
}
