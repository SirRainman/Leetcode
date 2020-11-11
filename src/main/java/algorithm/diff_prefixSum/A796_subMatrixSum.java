package algorithm.diff_prefixSum;

import java.util.Scanner;

/**
 * 输入一个n行m列的整数矩阵，再输入q个询问，每个询问包含四个整数x1, y1, x2, y2，表示一个子矩阵的左上角坐标和右下角坐标。
 *
 * 对于每个询问输出子矩阵中所有数的和。
 *
 * 输入格式
 * 第一行包含三个整数n，m，q。
 *
 * 接下来n行，每行包含m个整数，表示整数矩阵。
 *
 * 接下来q行，每行包含四个整数x1, y1, x2, y2，表示一组询问。
 *
 * 输出格式
 * 共q行，每行输出一个询问的结果。
 *
 * 数据范围
 * 1≤n,m≤1000,
 * 1≤q≤200000,
 * 1≤x1≤x2≤n,
 * 1≤y1≤y2≤m,
 * −1000≤矩阵内元素的值≤1000
 * 输入样例：
 * 3 4 3
 * 1 7 2 4
 * 3 6 2 8
 * 2 1 2 3
 * 1 1 2 2
 * 2 1 3 4
 * 1 3 3 4
 * 输出样例：
 * 17
 * 27
 * 21
 *
 * https://www.acwing.com/problem/content/798/
 */
public class A796_subMatrixSum {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int row = in.nextInt(), col = in.nextInt();
        int times = in.nextInt();

        int[][] grid = new int[row + 1][col + 1];
        int[][] sums = new int[row + 1][col + 1];
        // TODO: 一定要动手画图
        for(int i = 1; i <= row; i++) {
            for(int j = 1; j <= col; j++) {
                grid[i][j] = in.nextInt();
                sums[i][j] = sums[i - 1][j] + sums[i][j - 1] - sums[i - 1][j - 1] + grid[i][j];
            }
        }

        while(times-- > 0) {
            int x1 = in.nextInt(), y1 = in.nextInt(), x2 = in.nextInt(), y2 = in.nextInt();
            int ans = sums[x2][y2] - sums[x1 - 1][y2] - sums[x2][y1 - 1] + sums[x1 - 1][y1 - 1];
            System.out.println(ans);
        }
    }
}
