package algorithm.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定一个如下图所示的数字三角形，从顶部出发，在每一结点可以选择移动至其左下方的结点或移动至其右下方的结点，一直走到底层，要求找出一条路径，使路径上的数字的和最大。
 *
 *         7
 *       3   8
 *     8   1   0
 *   2   7   4   4
 * 4   5   2   6   5
 * 输入格式
 * 第一行包含整数n，表示数字三角形的层数。
 *
 * 接下来n行，每行包含若干整数，其中第 i 行表示数字三角形第 i 层包含的整数。
 *
 * 输出格式
 * 输出一个整数，表示最大的路径数字和。
 *
 * 数据范围
 * 1≤n≤500,
 * −10000≤三角形中的整数≤10000
 *
 * 输入样例：
 * 5
 * 7
 * 3 8
 * 8 1 0
 * 2 7 4 4
 * 4 5 2 6 5
 * 输出样例：
 * 30
 *
 * https://www.acwing.com/problem/content/900/
 */
public class A898_LinearDP_NumberTriangel {

    static int n;
    static int[][] a;

    public static int getMaxPathSum1() {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        a = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                a[i][j] = in.nextInt();
            }
        }


        int res = Integer.MIN_VALUE;

        int[][] f = new int[n + 1][n + 1];
        for (int[] t : f) Arrays.fill(t, Integer.MIN_VALUE); // 初始化边界值，超过边界值以外的点不可以走

        f[1][1] = a[1][1];
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - 1]) + a[i][j];
                // TODO：能不能优化？？
                if (i == n) res = Math.max(res, f[i][j]);
            }
        }

        return res;
    }

    // TODO: 想一想为什么要倒着走？？？
    public static int getMaxPathSum2() {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        a = new int[n + 2][n + 2];
        for (int i = n; i >= 1; i--) {
            for (int j = 1; j <= n - i + 1; j++) {
                a[i][j] = in.nextInt();
            }
        }

        int[][] f = new int[n + 2][n + 2];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n - i + 1; j++) {
                f[i][j] = Math.max(f[i - 1][j], f[i - 1][j + 1]) + a[i][j];
            }
        }


        return f[n][1];
    }

    // TODO:想一想能不能优化到一维？？？
    //  当前状态质和上一层有关
    public static int getMaxPathSum() {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        a = new int[n + 2][n + 2];
        for (int i = n; i >= 1; i--) {
            for (int j = 1; j <= n - i + 1; j++) {
                a[i][j] = in.nextInt();
            }
        }

        int[] f = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n - i + 1; j++) {
                f[j] = Math.max(f[j], f[j + 1]) + a[i][j];
            }
        }
        return f[1];
    }

    public static void main(String[] args) {
        System.out.print(getMaxPathSum());
    }

}
