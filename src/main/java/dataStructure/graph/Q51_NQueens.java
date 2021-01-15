package dataStructure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * n皇后问题研究的是如何将 n个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q51_NQueens {
    class Solution {
        List<List<String>> ans = new ArrayList<>();

        char[][] board;
        boolean[] col, diagnal, antiDiagnal;

        public void dfs(int row, int n) {
            if(row == n) {
                List<String> path = new ArrayList<>();
                for(char[] b : board) {
                    String p = new String(b);
                    path.add(p);
                }
                ans.add(path);
                return ;
            }

            for(int i = 0; i < n; i++) {
                if(!col[i] && !diagnal[i - row + n] && !antiDiagnal[i + row]) {
                    col[i] = diagnal[i - row + n] = antiDiagnal[i + row] = true;
                    board[row][i] = 'Q';
                    dfs(row + 1, n);
                    board[row][i] = '.';
                    col[i] = diagnal[i - row + n] = antiDiagnal[i + row] = false;
                }
            }
        }
        public List<List<String>> solveNQueens(int n) {
            board = new char[n][n];
            col = new boolean[n];
            diagnal = new boolean[n + n];
            antiDiagnal = new boolean[n + n];

            for(char[] b : board) Arrays.fill(b, '.');
            dfs(0, n);
            return ans;
        }
    }
}
