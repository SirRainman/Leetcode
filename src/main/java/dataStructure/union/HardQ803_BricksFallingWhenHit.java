package dataStructure.union;

/**
 * 有一个 m x n 的二元网格，其中 1 表示砖块，0 表示空白。砖块 稳定（不会掉落）的前提是：
 * 一块砖直接连接到网格的顶部，或者
 * 至少有一块相邻（4个方向之一）砖块 稳定 不会掉落时
 * 给你一个数组 hits ，这是需要依次消除砖块的位置。
 * 每当消除hits[i] = (rowi, coli) 位置上的砖块时，对应位置的砖块（若存在）会消失，然后其他的砖块可能因为这一消除操作而掉落。
 * 一旦砖块掉落，它会立即从网格中消失（即，它不会落在其他稳定的砖块上）。
 * 返回一个数组 result ，其中 result[i] 表示第 i 次消除操作对应掉落的砖块数目。
 * 注意，消除可能指向是没有砖块的空白位置，如果发生这种情况，则没有砖块掉落。
 *
 * 
 *
 * 示例 1：
 * 输入：grid = [[1,0,0,0],[1,1,1,0]], hits = [[1,0]]
 * 输出：[2]
 * 解释：
 * 网格开始为：
 * [[1,0,0,0]，
 *  [1,1,1,0]]
 * 消除 (1,0) 处加粗的砖块，得到网格：
 * [[1,0,0,0]
 *  [0,1,1,0]]
 * 两个加粗的砖不再稳定，因为它们不再与顶部相连，也不再与另一个稳定的砖相邻，因此它们将掉落。得到网格：
 * [[1,0,0,0],
 *  [0,0,0,0]]
 * 因此，结果为 [2] 。
 *
 * 示例 2：
 * 输入：grid = [[1,0,0,0],[1,1,0,0]], hits = [[1,1],[1,0]]
 * 输出：[0,0]
 * 解释：
 * 网格开始为：
 * [[1,0,0,0],
 *  [1,1,0,0]]
 * 消除 (1,1) 处加粗的砖块，得到网格：
 * [[1,0,0,0],
 *  [1,0,0,0]]
 * 剩下的砖都很稳定，所以不会掉落。网格保持不变：
 * [[1,0,0,0], 
 *  [1,0,0,0]]
 * 接下来消除 (1,0) 处加粗的砖块，得到网格：
 * [[1,0,0,0],
 *  [0,0,0,0]]
 * 剩下的砖块仍然是稳定的，所以不会有砖块掉落。
 * 因此，结果为 [0,0] 。
 * 
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * grid[i][j] 为 0 或 1
 * 1 <= hits.length <= 4 * 104
 * hits[i].length == 2
 * 0 <= xi<= m - 1
 * 0 <=yi <= n - 1
 * 所有 (xi, yi) 互不相同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bricks-falling-when-hit
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ803_BricksFallingWhenHit {
    private int row, col;
    private int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public int[] hitBricks(int[][] grid, int[][] hits) {
        int[] res = new int[hits.length];
        row = grid.length; col = grid[0].length;

        // 第 1 步：把 grid 中的砖头全部击碎，通常算法问题不能修改输入数据，这一步非必需，可以认为是一种答题规范
        int[][] afterHit = new int[row][col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                afterHit[i][j] = grid[i][j];
            }
        }
        for(int[] hit : hits) { // 把 afterHit 中的砖头全部击碎
            afterHit[hit[0]][hit[1]] = 0;
        }

        // 第 2 步：建图，把砖块和砖块的连接关系输入并查集，size 表示二维网格的大小，也表示虚拟的「屋顶」在并查集中的编号
        int size = row * col;
        UnionFind uf = new UnionFind(size + 1);
        // 将下标为 0 的这一行的砖块与「屋顶」相连
        for(int j = 0; j < col; j++) {
            if(afterHit[0][j] == 1) {
                uf.union(j, size);
            }
        }
        // 其余网格向上、向左看一下，如果也是砖块，在并查集中进行合并
        for(int i = 1; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(afterHit[i][j] == 1) {
                    // 如果上方也是砖块
                    if(afterHit[i - 1][j] == 1) {
                        uf.union(getIndex(i - 1, j), getIndex(i, j));
                    }
                    // 如果左边也是砖块
                    if(j > 0 && afterHit[i][j - 1] == 1) {
                        uf.union(getIndex(i, j - 1), getIndex(i, j));
                    }
                }
            }
        }

        // 第 3 步：按照 hits 的逆序，在 copy 中补回砖块，把每一次因为补回砖块而与屋顶相连的砖块的增量记录到 res 数组中
        for(int i = hits.length - 1; i >= 0; i--) {
            int x = hits[i][0], y = hits[i][1];
            // 如果原来在 grid 中，这一块是空白，这一步不会产生任何砖块掉落,逆向补回的时候，与屋顶相连的砖块数量也肯定不会增加
            if(grid[x][y] == 0) continue;

            // 补回之前与屋顶相连的砖块数
            int origin = uf.getSize(size);

            // 注意：如果补回的这个结点在第 1 行，要告诉并查集它与屋顶相连
            if(x == 0) uf.union(y, size);

            for(int[] d : directions) {
                int u = x + d[0], v = y + d[1];
                if(inArea(u, v) && afterHit[u][v] == 1) {
                    uf.union(getIndex(u, v), getIndex(x, y));
                }
            }

            // 补回之后与屋顶相连的砖块数
            int current = uf.getSize(size);
            // 减去的 1 是逆向补回的砖块（正向移除的砖块），与 0 比较大小，是因为存在一种情况，添加当前砖块，不会使得与屋顶连接的砖块数更多
            res[i] = Math.max(0, current - origin - 1);

            // 真正补上这个砖块
            afterHit[x][y] = 1;
        }

        return res;
    }


    // 输入坐标在二维网格中是否越界
    private boolean inArea(int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }

    // 二维坐标转换为一维坐标
    private int getIndex(int x, int y) {
        return x * col + y;
    }

    class UnionFind {
        private int[] parent, size;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            for(int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int a) {
            if(parent[a] != a) parent[a] = find(parent[a]);
            return parent[a];
        }

        public int getSize(int a) {
            return size[find(a)];
        }

        public void union(int a, int b) {
            int pa = find(a), pb = find(b);
            if(pa == pb) return ;
            parent[pa] = pb;
            size[pb] += size[pa];
        }
    }
}
