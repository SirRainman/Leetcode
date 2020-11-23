package algorithm.math;

import java.util.Scanner;

/**
 * 给定n对正整数ai,bi，对于每对数，求出一组xi,yi，使其满足ai∗xi+bi∗yi=gcd(ai,bi)。
 *
 * 输入格式
 * 第一行包含整数n。
 * 接下来n行，每行包含两个整数ai,bi。
 *
 * 输出格式
 * 输出共n行，对于每组ai,bi，求出一组满足条件的xi,yi，每组结果占一行。
 * 本题答案不唯一，输出任意满足条件的xi,yi均可。
 *
 * 数据范围
 * 1≤n≤105,
 * 1≤ai,bi≤2∗109
 * 输入样例：
 * 2
 * 4 6
 * 8 18
 * 输出样例：
 * -1 1
 * -2 1
 *
 * https://www.acwing.com/problem/content/879/
 */
public class A877_ExtendGCD {
    static class Solution {
        int x, y;
        Solution(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // TODO:
    //  1.裴蜀定理
    //  2.欧几里得定理
    //  3.裴蜀定理展开
    public static Solution extendGCD(int a, int b) {
        if(b == 0) return new Solution(1, 0);
        Solution res = extendGCD(b, a % b);
        int x = res.x, y = res.y;
        res.x = y;
        res.y = x - a / b * y;
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        while(n-- > 0) {
            int a = in.nextInt(), b = in.nextInt();
            Solution res = extendGCD(a, b);
            System.out.println(res.x + " " + res.y);
        }
    }
}
