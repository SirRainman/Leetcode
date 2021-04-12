package dataStructure.graph;

/**
 * @program: Leetcode
 * @description:
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * @author: Rain
 * @create: 2021-03-28 12:36
 **/
public class Q130_SurroundedRegions {
    private int row, col;
    private int[] nx = {0, 0, 1, -1};
    private int[] ny = {1, -1, 0, 0};
    private boolean[][] isVisited;

    // TODO: 先把未被包围的区域标记一下，剩下的就是被包围的
    public void solve(char[][] board) {
        row = board.length;
        col = board[0].length;
        isVisited = new boolean[row][col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(i == 0 || i == row - 1 || j == 0 || j == col - 1) {
                    if(!isVisited[i][j] && board[i][j] == 'O') {
                        dfs(board, i, j);
                    }
                }
            }
        }

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(board[i][j] == 'S') board[i][j] = 'O';
                else if(board[i][j] == 'O') board[i][j] = 'X';
            }
        }
    }

    public void dfs(char[][] board, int i, int j) {
        if(i < 0 || i >= row || j < 0 || j >= col || board[i][j] != 'O' || isVisited[i][j]) return;
        board[i][j] = 'S';
        for (int t = 0; t < 4; t++) {
            dfs(board, i + nx[t], j + ny[t]);
        }
    }
}
