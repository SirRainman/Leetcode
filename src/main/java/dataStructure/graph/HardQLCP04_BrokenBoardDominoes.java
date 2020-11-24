package dataStructure.graph;

import java.util.Arrays;

/**
 * 你有一块棋盘，棋盘上有一些格子已经坏掉了。
 * 你还有无穷块大小为1 * 2的多米诺骨牌，你想把这些骨牌不重叠地覆盖在完好的格子上，请找出你最多能在棋盘上放多少块骨牌？
 * 这些骨牌可以横着或者竖着放。
 *
 * 输入：n, m代表棋盘的大小；broken是一个b * 2的二维数组，其中每个元素代表棋盘上每一个坏掉的格子的位置。
 * 输出：一个整数，代表最多能在棋盘上放的骨牌数。
 *
 * 示例 1：
 * 输入：n = 2, m = 3, broken = [[1, 0], [1, 1]]
 * 输出：2
 * 解释：我们最多可以放两块骨牌：[[0, 0], [0, 1]]以及[[0, 2], [1, 2]]。（见下图）
 *
 * 示例 2：
 * 输入：n = 3, m = 3, broken = []
 * 输出：4
 * 解释：下图是其中一种可行的摆放方式
 *
 * 限制：
 * 1 <= n <= 8
 * 1 <= m <= 8
 * 0 <= b <= n * m
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/broken-board-dominoes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQLCP04_BrokenBoardDominoes {
    class Solution {
        int row, col;
        boolean[][] graph;
        boolean[] st;
        int[] matches;

        // TODO:二分图的最大匹配用 匈牙利算法
        //  首先，我们可以对棋盘进行黑白染色，使得任意相邻的两个格子颜色不相同。这意味着将格子看作是节点的话，整个棋盘就是一个二分图。
        //  根据棋盘上的点的 i + j 的和为奇数或者偶数，把所有点分成两个集合。
        //  然后将棋盘上的点尽可能多的链接在一起。
        //  任何一个奇数和的四周最多有四个和为偶数的点，但是因为有些点不能够进行匹配，所以需要用到二分图匹配进行选择
        public boolean find(int x, int y) {
            int[] nextX = new int[] {0, 0, 1, -1};
            int[] nextY = new int[] {1, -1, 0, 0};

            for(int i = 0; i < 4; i++) {
                int u = x + nextX[i];
                int v = y + nextY[i];
                if(u < 0 || u >= row || v < 0 || v >= col || graph[u][v]) continue;

                int p = u * col + v;
                if(!st[p]) {
                    st[p] = true;
                    if(matches[p] == -1 || find(matches[p] / col, matches[p] % col)) {
                        matches[p] = x * col + y;
                        return true;
                    }
                }
            }
            return false;
        }

        public int domino(int n, int m, int[][] broken) {
            row = n;
            col = m;

            graph = new boolean[n][m];
            for(int[] pos : broken) graph[pos[0]][pos[1]] = true;

            st = new boolean[n * m];
            matches = new int[n * m];

            Arrays.fill(matches, -1);

            int res = 0;
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if((i + j) % 2  == 1 || graph[i][j] == true) continue;

                    Arrays.fill(st, false);
                    if(find(i, j)) res++;
                }
            }

            return res;
        }
    }
}
