package algorithm.math;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 给定n堆石子以及一个由k个不同正整数构成的数字集合S。
 * 现在有两位玩家轮流操作，每次操作可以从任意一堆石子中拿取石子，每次拿取的石子数量必须包含于集合S，最后无法进行操作的人视为失败。
 * 问如果两人都采用最优策略，先手是否必胜。
 *
 * 输入格式
 * 第一行包含整数k，表示数字集合S中数字的个数。
 * 第二行包含k个整数，其中第i个整数表示数字集合S中的第i个数si。
 * 第三行包含整数n。
 * 第四行包含n个整数，其中第i个整数表示第i堆石子的数量hi。
 *
 * 输出格式
 * 如果先手方必胜，则输出“Yes”。
 * 否则，输出“No”。
 *
 * 数据范围
 * 1≤n,k≤100,
 * 1≤si,hi≤10000
 * 输入样例：
 * 2
 * 2 5
 * 3
 * 2 4 7
 * 输出样例：
 * Yes
 *
 * https://www.acwing.com/problem/content/895/
 */
public class A893_NimGame3_Set {

    static int N = 100010;
    static int k, n;
    static int[] s; // 存储每次可以拿多少个石子
    static int[] mem; // 记忆话存储。

    // TODO: 定理
    //  有向图游戏的某个局面必胜，当且仅当该局面对应节点的SG函数值大于0。
    //  有向图游戏的某个局面必败，当且仅当该局面对应节点的SG函数值等于0。
    public static int sg(int stone) {
        if(mem[stone] != -1) return mem[stone];

        // 构造子树
        Set<Integer> set = new HashSet<>(); // set存储每一堆的石子，再经过拿取之后，他的sg值。
        for(int i = 0; i < k; i++) {
            if(stone >= s[i]) {
                set.add(sg(stone - s[i])); // 保存后继结点到S中（递归）
            }
        }

        // Mex运算，求出不属于集合S的最小非负整数的运算。
        for(int i = 0; true; i++) {
            if(!set.contains(i)) return mem[stone] = i;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        mem = new int[N];
        Arrays.fill(mem, -1);

        k = in.nextInt();
        s = new int[k];
        for(int i = 0; i < k; i++) s[i] = in.nextInt();

        // TODO：把n堆石子看成n个独立的有向图G，把各个有向图结果做异或即可得到答案
        int res = 0;
        n = in.nextInt();
        for(int i = 0; i < n; i++) {
            int stone = in.nextInt();
            res = res ^ sg(stone); // 把n堆石子看成n个独立的有向图G，把各个有向图结果做异或即可得到答案
        }

        System.out.print(res > 0 ? "Yes" : "No");
    }
}
