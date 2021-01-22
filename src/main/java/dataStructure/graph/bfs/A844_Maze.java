package dataStructure.graph.bfs;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 给定一个n*m的二维整数数组，用来表示一个迷宫，数组中只包含0或1，其中0表示可以走的路，1表示不可通过的墙壁。
 * 最初，有一个人位于左上角(1, 1)处，已知该人每次可以向上、下、左、右任意一个方向移动一个位置。
 * 请问，该人从左上角移动至右下角(n, m)处，至少需要移动多少次。
 * 数据保证(1, 1)处和(n, m)处的数字为0，且一定至少存在一条通路。
 *
 * 输入格式
 * 第一行包含两个整数n和m。
 * 接下来n行，每行包含m个整数（0或1），表示完整的二维数组迷宫。
 *
 * 输出格式
 * 输出一个整数，表示从左上角移动至右下角的最少移动次数。
 *
 * 数据范围
 * 1≤n,m≤100
 * 输入样例：
 * 5 5
 * 0 1 0 0 0
 * 0 1 0 1 0
 * 0 0 0 0 0
 * 0 1 1 1 0
 * 0 0 0 1 0
 * 输出样例：
 * 8
 *
 * https://www.acwing.com/problem/content/846/
 */
public class A844_Maze {
    static int n, m;
    static int[][] g, dist;

    public static int bfs() {
        int[] nx = new int[]{0, 0, 1, -1};
        int[] ny = new int[]{1, -1, 0, 0};
        Deque<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        dist[0][0] = 0;
        while(!queue.isEmpty()) {
            int[] v = queue.poll();
            for(int i = 0; i < 4; i++) {
                int x = v[0] + nx[i], y = v[1] + ny[i];
                if(x < 0 || x >= n || y < 0 || y >= m) continue;
                if(g[x][y] == 0 && dist[x][y] == -1) {
                    queue.offer(new int[]{x, y});
                    dist[x][y] = dist[v[0]][v[1]] + 1;
                }
            }
        }

        return dist[n - 1][m - 1];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt(); m = in.nextInt();
        g = new int[n][m];
        dist = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                g[i][j] = in.nextInt();
                dist[i][j] = -1;
            }
        }
        System.out.print(bfs());
    }
}
