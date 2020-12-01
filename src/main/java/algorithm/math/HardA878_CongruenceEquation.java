package algorithm.math;

import java.util.Scanner;

/**
 * 给定n组数据ai,bi,mi，对于每组数求出一个xi，使其满足ai∗xi≡bi(mod mi)，如果无解则输出impossible。
 *
 * 输入格式
 * 第一行包含整数n。
 * 接下来n行，每行包含一组数据ai,bi,mi。
 *
 * 输出格式
 * 输出共n行，每组数据输出一个整数表示一个满足条件的xi，如果无解则输出impossible。
 * 每组数据结果占一行，结果可能不唯一，输出任意一个满足条件的结果均可。
 * 输出答案必须在int范围之内。
 *
 * 数据范围
 * 1≤n≤105,
 * 1≤ai,bi,mi≤2∗109
 *
 * 输入样例：
 * 2
 * 2 3 6
 * 4 3 5
 *
 * 输出样例：
 * impossible
 * 7
 *
 * https://www.acwing.com/problem/content/880/
 */
public class HardA878_CongruenceEquation {
    static class Solution {
        int x, y, gcd;
        Solution (int x, int y, int gcd) {
            this.x = x;
            this.y = y;
            this.gcd = gcd;
        }
    }

    public static Solution extendGCD(int a, int b) {
        if(b == 0) return new Solution(1, 0, a);
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
            int a = in.nextInt(), b = in.nextInt(), m = in.nextInt();
            // TODO: a ∗ x ≡ b (mod m)
            //  设 a * x = m * k + b
            //  则 a * x + m * (-k) = b
            //  设 x1 = x , y1 = -k
            //  则 a * x1 + m * y1 = b
            //  求 x1, y1? 在这里只输出 x1就好了
            Solution res = extendGCD(a, m);
            // 有解的前提必须是 b = k * gcd
            // TODO:想一想为什么x是这个？？？
            //  因  a * x1 + m * y1 = gcd(a, m)
            //  设  b = k1 * gcd(a, m)
            //  即  k1 = b / gcd(a, m)
            //  则  k1 * (a * x1 + m * y1) = k1 * gcd(a, m) = b
            //  则  x = k1 * x1 = b / gcd(a, m) * b
            System.out.println(b % res.gcd == 0 ? (long) res.x * b / res.gcd % m : "impossible");
        }
    }
}
