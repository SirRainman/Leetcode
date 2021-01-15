package algorithm.math;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 给定n个正整数ai，对于每个整数ai,请你按照从小到大的顺序输出它的所有约数。
 *
 * 输入格式
 * 第一行包含整数n。
 * 接下来n行，每行包含一个整数ai。
 *
 * 输出格式
 * 输出共n行，其中第 i 行输出第 i 个整数ai的所有约数。
 *
 * 数据范围
 * 1≤n≤100,
 * 2≤ai≤2∗109
 * 输入样例：
 * 2
 * 6
 * 8
 * 输出样例：
 * 1 2 3 6
 * 1 2 4 8
 *
 * https://www.acwing.com/problem/content/description/871/
 */
public class A869_Divisor {
    public static List<Integer> getDivisors(int x) {
        List<Integer> divisors = new ArrayList<>();
        for(int i = 1; i <= x / i; i++) {
            if(x % i == 0) {
                divisors.add(i);
                if(x / i != i) divisors.add(x / i);
            }
        }
        // TODO: 注意List的排序方法
        divisors.sort((o1, o2) -> o1 - o2);
        return divisors;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        while(n-- > 0) {
            int x = in.nextInt();
            List<Integer> ans = getDivisors(x);
            for(int i : ans) System.out.print(i + " ");
            System.out.println();
        }
    }
}
