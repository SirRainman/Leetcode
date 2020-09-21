package algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 编写一个程序，通过已填充的空格来解决数独问题。
 *
 * 一个数独的解法需遵循如下规则：
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sudoku-solver
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ37_SudokuSolver {

    private boolean[][] line = new boolean[10][10];
    private boolean[][] colum = new boolean[10][10];
    private boolean[][][] block = new boolean[4][4][10];
    private boolean valid = false;
    private List<int[]> spaces = new ArrayList<int[]>();

    public void solveSudoku(char[][] board) {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j] == '.') {
                    spaces.add(new int[]{i, j});
                } else {
                    int digit = board[i][j] - '0';
                    line[i][digit] = colum[j][digit] = block[i / 3][j / 3][digit] = true;
                }
            }
        }

        dfs(board, 0);
    }

    public void dfs(char[][] board, int pos) {
        if(pos == spaces.size()) {
            valid = true;
            return;
        }

        int[] space = spaces.get(pos);
        int i = space[0], j = space[1];
        for(int digit = 1; digit <= 9 && !valid; digit++) {
            if(!line[i][digit] && !colum[j][digit] && !block[i / 3][j / 3][digit]) {
                line[i][digit] = colum[j][digit] = block[i / 3][j / 3][digit] = true;
                board[i][j] = (char) ('0' + digit);
                dfs(board, pos + 1);
                line[i][digit] = colum[j][digit] = block[i / 3][j / 3][digit] = false;
            }
        }
    }
}
