package algorithm.math;

import java.util.Scanner;

/**
 * 输入一个包含n个方程n个未知数的线性方程组。
 * 方程组中的系数为实数。
 * 求解这个方程组。
 *
 * 输入格式
 * 第一行包含整数n。
 * 接下来n行，每行包含n+1个实数，表示一个方程的n个系数以及等号右侧的常数。
 *
 * 输出格式
 * 如果给定线性方程组存在唯一解，则输出共n行，其中第i行输出第i个未知数的解，结果保留两位小数。
 * 如果给定线性方程组存在无数解，则输出“Infinite group solutions”。
 * 如果给定线性方程组无解，则输出“No solution”。
 *
 * 数据范围
 * 1≤n≤100,
 * 所有输入系数以及常数均保留两位小数，绝对值均不超过100。
 *
 * 输入样例：
 * 3
 * 1.00 2.00 -1.00 -6.00
 * 2.00 1.00 -3.00 -9.00
 * -1.00 -1.00 2.00 7.00
 * 输出样例：
 * 1.00
 * -2.00
 * 3.00
 *
 *
 * https://www.acwing.com/problem/content/885/
 */
public class A883_Gauss {
    static int n;
    static double eps = 1e-6;
    static double[][] a;

    public static void swap(int r1, int c1, int r2, int c2) {
        double t = a[r1][c1];
        a[r1][c1] = a[r2][c2];
        a[r2][c2] = t;
    }

    public static int gauss() {
        int c = 0, r = 0;
        for(c = 0, r = 0; c < n; c++) {

            // 1.找到当前列绝对值最大元素所在的行（搜索第r行~最后一行）
            int t = r;
            for(int i = r + 1; i < n; i++) {
                if(Math.abs(a[i][c]) > Math.abs(a[t][c])) {
                    t = i;
                }
            }

            // 2.当前列全是0，因为a[t][c]本来就是该列的最大值了
            if(Math.abs(a[t][c]) < eps) continue;

            // 3.将绝对值最大的行换到未确定的最顶端
            for(int j = c; j <= n; j++) swap(r, j, t, j);

            // 4.将当前上的首位变成1。
            for(int j = n; j >= c; j--) a[r][j] = a[r][j] / a[r][c]; // TODO: 注意从后往前遍历，防止先写后读。先写完a[r][c]之后，会影响后面的数

            // 5.用第r行，将r行下面所有行的第c列消成0
            for(int i = r + 1; i < n; i++) {
                if(Math.abs(a[i][c]) > eps) {
                    for(int j = n; j >= c; j--) { // TODO: 注意从后往前遍历，防止先写后读。先写完a[i][c]之后，会影响后面的数
                        a[i][j] = a[i][j] - a[i][c] * a[r][j]; // TODO：把a[i][c] 变为0
                    }
                }
            }

            r++;
        }

        if(r < n) {
            // 判断是否有解
            for(int i = r; i < n; i++) {
                if(Math.abs(a[i][n]) > eps) {
                    return -1;
                }
            }
            return 0;
        }

        // TODO：难：化简为单元矩阵
        for(int i = n - 1; i >= 0; i--) { // TODO: 注意从后往前遍历，防止先写后读。
            for(int j = i + 1; j < n; j++) {
                a[i][n] = a[i][n] - a[i][j] * a[j][n];  // TODO:把a[i][j]变为0
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();

        a = new double[n][n + 1];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n + 1; j++) {
                a[i][j] = in.nextDouble();
            }
        }

        int s = gauss();
        if (s == -1) {
            System.out.println("No solution");
        } else if(s == 0) {
            System.out.println("Infinite group solutions");
        } else {
            for(int i = 0; i < n; i++) {
                System.out.printf("%.2f\n", a[i][n]);
            }
        }
    }
}
