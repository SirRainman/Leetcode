package algorithm.dp;

import java.util.Scanner;

/**
 * 设有N堆石子排成一排，其编号为1，2，3，…，N。
 * 每堆石子有一定的质量，可以用一个整数来描述，现在要将这N堆石子合并成为一堆。
 * 每次只能合并相邻的两堆，合并的代价为这两堆石子的质量之和，合并后与这两堆石子相邻的石子将和新堆相邻，合并时由于选择的顺序不同，合并的总代价也不相同。
 * 例如有4堆石子分别为 1 3 5 2， 我们可以先合并1、2堆，代价为4，得到4 5 2， 又合并 1，2堆，代价为9，得到9 2 ，再合并得到11，总代价为4+9+11=24；
 * 如果第二步是先合并2，3堆，则代价为7，得到4 7，最后一次合并代价为11，总代价为4+7+11=22。
 * 问题是：找出一种合理的方法，使总的代价最小，输出最小代价。
 *
 * 输入格式
 * 第一行一个数N表示石子的堆数N。
 * 第二行N个数，表示每堆石子的质量(均不超过1000)。
 *
 * 输出格式
 * 输出一个整数，表示最小代价。
 *
 * 数据范围
 * 1≤N≤300
 * 输入样例：
 * 4
 * 1 3 5 2
 * 输出样例：
 * 22
 *
 * https://www.acwing.com/problem/content/284/
 */
public class A282_MergeStones {
    static int INF = 0x3f3f3f3f;
    static int n;
    static int[] s;

    public static int getMinWeight() {
        for(int i = 1; i <= n; i++) { // 前缀和
            s[i] += s[i - 1];
        }

        int[][] dp = new int[n + 1][n + 1]; // 表示将 ii 到 jj 合并成一堆的方案的集合，属性 Min

        // TODO：为什么要先枚举长度？
        //  因为要保证后面使用到的状态在前面被计算过，所以只能先枚举长度
        for(int len = 2; len <= n; len++) { // 固定窗口大小，从小到大遍历, len=1时不合并（类似归并排序的merge）
            for(int i = 1; i + len - 1 <= n; i++) { // 固定窗口左端点，则可确定窗口右端点，注意边界
                int left = i, right = i + len - 1; // 固定窗口边界
                dp[left][right] = INF; // 初始化为无穷大，将初值为无穷大视为非法操作
                for(int k = left; k < right; k++) { // 窗口内划分
                    // 核心：最后一次合并一定是左边连续的一部分和右边连续的一部分进行合并
                    dp[left][right] = Math.min(dp[left][right], dp[left][k] + dp[k + 1][right] + s[right] - s[left - 1]);
                }
            }
        }

        return dp[1][n];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        s = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            s[i] = in.nextInt();
        }
        System.out.print(getMinWeight());
    }
}
