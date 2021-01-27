package dataStructure.union;

/**
 * 在由 1 x 1 方格组成的 N x N 网格grid 中，每个 1 x 1方块由 /、\ 或空格构成。这些字符会将方块划分为一些共边的区域。
 * （请注意，反斜杠字符是转义的，因此 \ 用 "\\"表示。）。
 * 返回区域的数目。
 *
 * 示例 1：
 * 输入：
 * [
 *  " /",
 *  "/ "
 * ]
 * 输出：2
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regions-cut-by-slashes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q959_MergeRegionsCutBySlashes {
    // TODO: 并查集的核心问题是在合并的两个内容是什么？
    //  在这个问题中，他是怎么想到把一个正方形划分成4部分的？？？
    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        UnionFind uf = new UnionFind(n * n * 4);
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int idx = i * n + j;
                char c = grid[i].charAt(j);
                if(i > 0) {
                    int up = (i - 1) * n + j;
                    uf.union(idx * 4 + 0, up * 4 + 3);
                }
                if(j > 0) {
                    int left = i * n + j - 1;
                    uf.union(idx * 4 + 1, left * 4 + 2);
                }
                if(c == '/') {
                    uf.union(idx * 4 + 0, idx * 4 + 1);
                    uf.union(idx * 4 + 2, idx * 4 + 3);
                } else if(c == '\\') {
                    uf.union(idx * 4 + 0, idx * 4 + 2);
                    uf.union(idx * 4 + 1, idx * 4 + 3);
                } else {
                    uf.union(idx * 4 + 0, idx * 4 + 1);
                    uf.union(idx * 4 + 2, idx * 4 + 3);
                    uf.union(idx * 4 + 0, idx * 4 + 2);
                }
            }
        }
        return uf.size;
    }

    class UnionFind {
        int[] parent;
        int size;
        public UnionFind(int n) {
            size = n;
            parent = new int[n];
            for(int i = 0; i < n; i++) parent[i] = i;
        }
        public int find(int a) {
            if(parent[a] != a) parent[a] = find(parent[a]);
            return parent[a];
        }
        public boolean union(int a, int b) {
            int pa = find(a), pb = find(b);
            if(pa == pb) return false;
            size--;
            parent[pa] = pb;
            return true;
        }
        public boolean isConnected(int a, int b) {
            return find(a) == find(b);
        }
    }
}
