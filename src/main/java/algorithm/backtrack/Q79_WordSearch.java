package algorithm.backtrack;

/**
 * @program: Leetcode
 * @description:
 * 
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 *
 * 示例:
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 * 
 *
 * 提示：
 * board 和 word 中只包含大写和小写英文字母。
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author: Rain
 * @create: 2021-03-11 11:51
 **/
public class Q79_WordSearch {

    int row, col;
    boolean[][] st;
    int[] nextX = {0, 0, 1, -1};
    int[] nextY = {1, -1, 0, 0};

    public boolean exist(char[][] board, String word) {
        row = board.length;
        col = board[0].length;
        st = new boolean[row][col];
        char[] w = word.toCharArray();

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(dfs(board, i, j, 0, w)) return true;
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, int x, int y, int cur, char[] w) {
        if(cur == w.length) return true;
        if(x < 0 || x >= row || y < 0 || y >= col || st[x][y] || board[x][y] != w[cur]) return false;

        st[x][y] = true;
        for(int i = 0; i < 4; i++) {
            int nx = x + nextX[i], ny = y + nextY[i];
            if(dfs(board, nx, ny, cur + 1, w)) return true;
        }
        st[x][y] = false;
        return false;
    }
}
