package dataStructure.union;

/**
 * 给你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 示例 1：
 * 输入：grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * 输出：1
 *
 * 示例 2：
 * 输入：grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * 输出：3
 * 
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q200_NumberofIslands {

    public int numIslands(char[][] grid) {
        if(grid == null || grid.length < 0) return 0;
        int ans = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    dfs(grid, i, j);
                    ans++;
                }
            }
        }
        return ans;
    }

    public void dfs(char[][] grid, int row, int column) {

        if(row == grid.length || column == grid[0].length || row < 0 || column < 0 || grid[row][column] == '0') {
            return;
        }

        grid[row][column] = '0';

        dfs(grid, row+1, column);
        dfs(grid, row-1, column);
        dfs(grid, row, column+1);
        dfs(grid, row, column-1);
    }


    // TODO:注意并查集的解法
    private int row, col;
    public int numIslands2(char[][] grid) {
        row = grid.length; col = grid[0].length;
        // 空地的数量
        int spaces = 0;
        UnionFind uf = new UnionFind(row * col);
        // TODO: 想一想为什么只在这两个方向判断就足够了？？？
        int[][] directions = {{1, 0}, {0, 1}};
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == '0') {
                    spaces++;
                } else {
                    for(int[] d : directions) {
                        int u = i + d[0], v = j + d[1];
                        if(inArea(u, v) && grid[u][v] == '1') {
                            uf.union(getIndex(i, j), getIndex(u, v));
                        }
                    }
                }
            }
        }
        return uf.getCount() - spaces;
    }

    private boolean inArea(int i, int j) {
        return i >= 0 && i < row && j >= 0 && j < col;
    }

    private int getIndex(int i, int j) {
        return i * col + j;
    }

    class UnionFind {
        private int[] parent;
        private int count;

        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            for(int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int a) {
            if(parent[a] != a) parent[a] = find(parent[a]);
            return parent[a];
        }

        public void union(int a, int b) {
            int pa = find(a), pb = find(b);
            if(pa == pb) return ;
            parent[pa] = pb;
            count--;
        }

        public int getCount() {
            return count;
        }
    }

}
