package dataStructure.graph.dfs;

import java.util.Arrays;
import java.util.Scanner;

/**
 *  n-皇后问题是指将 n 个皇后放在 n∗n 的国际象棋棋盘上，使得皇后不能相互攻击到，即任意两个皇后都不能处于同一行、同一列或同一斜线上。
 *
 * https://www.acwing.com/problem/content/845/
 */
public class A845_NQueens {
    static int n;
    static char[][] board;
    // col[0] 记录的是第0列是否有皇后
    static boolean[] col, diagnal, antiDiagnal;

    public static void dfs(int row) {
        if(row == n) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        for(int i = 0; i < n; i++) { // i是列下标
            //  对角线元素的行下标 减 列下标 为一个固定的数
            //反对角线元素的行下标 加 列下标 为一个固定的数
            if(!col[i] && !antiDiagnal[row + i] && !diagnal[row - i + n]) {
                col[i] = antiDiagnal[row + i] = diagnal[row - i + n] = true;
                board[row][i] = 'Q';
                dfs(row + 1);
                board[row][i] = '.';
                col[i] = antiDiagnal[row + i] = diagnal[row - i + n] = false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        board = new char[n][n];
        col = new boolean[n];
        diagnal = new boolean[n + n];
        antiDiagnal = new boolean[n + n];
        for(char[] b : board) Arrays.fill(b, '.');
        dfs(0); // 先确定第0行的皇后在哪一列

    }
}
