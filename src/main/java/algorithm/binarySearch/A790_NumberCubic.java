package algorithm.binarySearch;

import java.util.Scanner;

/**
 * 给定一个浮点数n，求它的三次方根。
 *
 * 输入格式
 * 共一行，包含一个浮点数n。
 *
 * 输出格式
 * 共一行，包含一个浮点数，表示问题的解。
 *
 * 注意，结果保留6位小数。
 *
 * 数据范围
 * −10000≤n≤10000
 * 输入样例：
 * 1000.00
 * 输出样例：
 * 10.000000
 *
 * https://www.acwing.com/problem/content/description/792/
 */
public class A790_NumberCubic {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double x = in.nextDouble();
        double left = -10000, right = 10000;
        while(right - left >= 1e-7) {
            double mid = (left + right) / 2;
            if(mid * mid * mid <= x) left = mid;
            else right = mid;
        }
        System.out.printf("%.6f", left);
    }
}
