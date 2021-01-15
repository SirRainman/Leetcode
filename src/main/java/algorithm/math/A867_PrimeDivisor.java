package algorithm.math;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 给定n个正整数ai，将每个数分解质因数，并按照质因数从小到大的顺序输出每个质因数的底数和指数。
 *
 *  输入格式
 * 第一行包含整数n。
 * 接下来n行，每行包含一个正整数ai。
 *
 * 输出格式
 * 对于每个正整数ai,按照从小到大的顺序输出其分解质因数后，每个质因数的底数和指数，每个底数和指数占一行。
 * 每个正整数的质因数全部输出完毕后，输出一个空行。
 *
 * 数据范围
 * 1≤n≤100,
 * 1≤ai≤2∗109
 *
 *  输入样例：
 * 2
 * 6
 * 8
 * 输出样例：
 * 2 1
 * 3 1
 *
 * 2 3
 *
 * https://www.acwing.com/problem/content/869/
 */
public class A867_PrimeDivisor {
    // TODO:如果可以整除说明当前是最小质因子
    //  为什么 x % i == 0 中的i一定是x的最小质因数？？
    //      当枚举到i时，就表明x不含有 2...i - 1的质因子了，
    //      即x把 2...i-1之间的质因数除干净了，
    //      因此i也不包含2…i-1之间的质因子了，i也是质数；
    //      反证法：如果包含质因子，说明前面没有除干净
    public static List<int[]> getPrimeDivisor(int x) {
        List<int[]> divisors = new ArrayList<>();
        for(int i = 2; i <= x / i; i++) {
            if(x % i == 0) {
                int exp = 0;
                while(x % i == 0) {
                    exp++;
                    x /= i;
                }
                divisors.add(new int[]{i, exp});
            }
        }
        // TODO：n中最多只包含一个大于sqrt(n)的质因子
        if(x > 1) divisors.add(new int[]{x, 1}); return divisors;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        while(n-- > 0) {
            int x = in.nextInt();
            List<int[]> ans = getPrimeDivisor(x);
            for(int[] p : ans) {
                System.out.println(p[0] + " " +p[1]);
            }
            System.out.println();
        }
    }
}
