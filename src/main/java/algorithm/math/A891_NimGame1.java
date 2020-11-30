package algorithm.math;

import java.util.Scanner;

/**
 * 给定n堆石子，两位玩家轮流操作，每次操作可以从任意一堆石子中拿走任意数量的石子（可以拿完，但不能不拿），最后无法进行操作的人视为失败。
 * 问如果两人都采用最优策略，先手是否必胜。
 *
 * 输入格式
 * 第一行包含整数n。
 * 第二行包含n个数字，其中第 i 个数字表示第 i 堆石子的数量。
 *
 * 输出格式
 * 如果先手方必胜，则输出“Yes”。
 * 否则，输出“No”。
 *
 * 数据范围
 * 1≤n≤105,
 * 1≤每堆石子数≤109
 * 输入样例：
 * 2
 * 2 3
 * 输出样例：
 * Yes
 *
 * https://www.acwing.com/problem/content/893/
 */
public class A891_NimGame1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int res = 0;
        while(n-- > 0) {
            int a = in.nextInt();
            // TODO：想一想应该怎么证明？？？
            res = res ^ a;
        }
        System.out.print(res > 0 ? "Yes" : "No");
    }
}
