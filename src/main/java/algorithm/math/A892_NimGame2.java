package algorithm.math;

import java.util.Scanner;

/**
 * 现在，有一个n级台阶的楼梯，每级台阶上都有若干个石子，其中第i级台阶上有ai个石子(i≥1)。
 * 两位玩家轮流操作，每次操作可以从任意一级台阶上拿若干个石子放到下一级台阶中（不能不拿）。
 * 已经拿到地面上的石子不能再拿，最后无法进行操作的人视为失败。
 * 问如果两人都采用最优策略，先手是否必胜。
 *
 * 输入格式
 * 第一行包含整数n。
 * 第二行包含n个整数，其中第i个整数表示第i级台阶上的石子数ai。
 *
 * 输出格式
 * 如果先手方必胜，则输出“Yes”。
 * 否则，输出“No”。
 *
 * 数据范围
 * 1≤n≤105,
 * 1≤ai≤109
 * 输入样例：
 * 3
 * 2 1 3
 * 输出样例：
 * Yes
 *
 * https://www.acwing.com/problem/content/894/
 */
public class A892_NimGame2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int res = 0;
        int n = in.nextInt();
        for(int i = 1; i <= n; i++) {
            // TODO:想一想为什么先手赢不赢是和奇数台阶有关的？？？
            //  res = a1 ^ a3 ^ ... ^ an = x != 0
            //      先手从奇数台阶上拿到偶数台阶上，使得res = 0
            //      即无论如何，如果先手的话，始终可以让后手面对res = 0的情况
            //  res = a1 ^ a3 ^ ... ^ an = x = 0
            //      从奇数台阶上拿完之后，res一定不为0，后手可以再从奇数上拿，使得res = 0
            //      从偶数台阶上拿到奇数台阶上之后，后手面对的res也不为零
            //      即无论如何始终会面对res = 0的情况
            if((i & 1) == 1) {
                int stone = in.nextInt();
                res ^= stone;
            } else {
                in.nextInt();
            }
        }
        System.out.print(res > 0 ? "Yes" : "No");
    }
}
