package dataStructure.graph.travel;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个包含了一些 0 和 1 的非空二维数组grid 。
 *
 * 一个岛屿是由一些相邻的1(代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设grid 的四个边缘都被 0（代表水）包围着。
 *
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
 *
 * 
 *
 * 示例 1:
 *
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 对于上面这个给定矩阵应返回6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。
 *
 * 示例 2:
 *
 * [[0,0,0,0,0,0,0,0]]
 * 对于上面这个给定的矩阵, 返回0。
 *
 * 
 *
 * 注意:给定的矩阵grid的长度和宽度都不超过 50。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-area-of-island
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q695_MaxAreaofIsland {
    class Solution {

        // TODO: bfs
        public int maxAreaOfIsland(int[][] grid) {
            int ans = 0;

            for(int i = 0; i < grid.length; i++) {
                for(int j = 0; j < grid[0].length; j++) {
                    int area = 0;
                    if(grid[i][j] == 1) {
                        Queue<Integer> queRow = new LinkedList<>();
                        Queue<Integer> queCol = new LinkedList<>();
                        // 访问i, j
                        grid[i][j] = 0;
                        area++;

                        queRow.offer(i);
                        queCol.offer(j);
                        while( !queRow.isEmpty()) {
                            int x = queRow.poll();
                            int y = queCol.poll();

                            int[] nextRow = new int[] {0, 1, 0, -1};
                            int[] nextCol = new int[] {1, 0, -1, 0};
                            for(int k = 0; k < 4; k++) {
                                int nextX = x + nextRow[k];
                                int nextY = y + nextCol[k];
                                if(nextX >= 0 && nextX < grid.length && nextY >= 0 && nextY < grid[0].length && grid[nextX][nextY] == 1) {
                                    grid[nextX][nextY] = 0;
                                    area++;
                                    queRow.offer(nextX);
                                    queCol.offer(nextY);
                                }
                            }
                        }
                    }
                    ans = Math.max(ans, area);
                }
            }
            return ans;
        }



        // TODO: dfs
        public int maxAreaOfIsland2(int[][] grid) {
            int maxArea = 0;
            for(int i = 0; i < grid.length; i++) {
                for(int j = 0; j < grid[0].length; j++) {
                    if(grid[i][j] == 1) {
                        int area = dfsArea(grid, i, j);
                        maxArea = Math.max(area, maxArea);
                    }
                }
            }
            return maxArea;
        }

        public int dfsArea(int[][] grid, int x, int y) {
            if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0) {
                return 0 ;
            }
            int area = 1;
            grid[x][y] = 0;
            area += dfsArea(grid, x+1, y);
            area += dfsArea(grid, x, y+1);
            area += dfsArea(grid, x-1, y);
            area += dfsArea(grid, x, y-1);
            return area;
        }


        // TODO: 非递归dfs
        public int maxAreaOfIsland3(int[][] grid) {
            int ans = 0;
            for (int i = 0; i != grid.length; ++i) {
                for (int j = 0; j != grid[0].length; ++j) {
                    int cur = 0;
                    Deque<Integer> stacki = new LinkedList<Integer>();
                    Deque<Integer> stackj = new LinkedList<Integer>();
                    stacki.push(i);
                    stackj.push(j);
                    while (!stacki.isEmpty()) {
                        int cur_i = stacki.pop(), cur_j = stackj.pop();
                        if (cur_i < 0 || cur_j < 0 || cur_i == grid.length || cur_j == grid[0].length || grid[cur_i][cur_j] != 1) {
                            continue;
                        }
                        ++cur;
                        grid[cur_i][cur_j] = 0;
                        int[] di = {0, 0, 1, -1};
                        int[] dj = {1, -1, 0, 0};
                        for (int index = 0; index != 4; ++index) {
                            int next_i = cur_i + di[index], next_j = cur_j + dj[index];
                            stacki.push(next_i);
                            stackj.push(next_j);
                        }
                    }
                    ans = Math.max(ans, cur);
                }
            }
            return ans;
        }
    }
}
