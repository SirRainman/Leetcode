package dataStructure.graph.distance;

import java.util.*;

public class HardQ1631_PathWithMinimumEffort {
    int INF = 0x3f3f3f3f;
    int row, col;
    int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    // TODO: dijkstra
    public int minimumEffortPath1(int[][] heights) {
        row = heights.length; col = heights[0].length;
        int[] dist = new int[row * col];
        Arrays.fill(dist, INF);
        PriorityQueue<int[]> heap = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        boolean[] st = new boolean[row * col];
        dist[0] = 0;
        heap.offer(new int[]{0, 0, 0});

        while(!heap.isEmpty()) {
            int[] e = heap.poll();
            int i = e[0], j = e[1], w = e[2];
            int id = getIndex(i, j);
            if(!st[id]) {
                st[id] = true;
                // System.out.println(id);
                if(i == row - 1 && j == col - 1) break;
                for(int[] d : dirs) {
                    int nextX = i + d[0], nextY = j + d[1];
                    int nextID = getIndex(nextX, nextY);
                    if(nextX >= 0 && nextX < row && nextY >= 0 && nextY < col) {
                        int cost = Math.abs(heights[i][j] - heights[nextX][nextY]);
                        if(Math.max(dist[id], cost) < dist[nextID]) {
                            dist[nextID] = Math.max(dist[id], cost);
                            heap.offer(new int[]{nextX, nextY, dist[nextID]});
                        }
                    }
                }
            }
        }

        return dist[getIndex(row - 1, col - 1)];
    }

    public int getIndex(int i, int j) {
        return i * col + j;
    }

    // TODO: 二分
    public int minimumEffortPath2(int[][] heights) {
        row = heights.length; col = heights[0].length;
        int left = 0, right = 1000000;
        int res = 0;
        while(left < right) {
            int mid = left + right >> 1;
            if(isPossible(heights, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean isPossible(int[][] g, int cost) {
        boolean[][] st = new boolean[row][col];
        Deque<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        st[0][0] = true;
        while(!queue.isEmpty()) {
            int[] p = queue.poll();
            int x = p[0], y = p[1];
            for(int[] d : dirs) {
                int u = x + d[0], v = y + d[1];
                if(u < 0 || u >= row || v < 0 || v >= col || st[u][v] == true) continue;
                if(Math.abs(g[x][y] - g[u][v]) <= cost) {
                    queue.offer(new int[]{u, v});
                    st[u][v] = true;
                }
            }
        }
        return st[row - 1][col - 1];
    }


    // TODO：并查集
    public int minimumEffortPath(int[][] heights) {
        row = heights.length; col = heights[0].length;
        if(row == 1 && col == 1) return 0;
        List<int[]> edges = new ArrayList<>();
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(i + 1 < row) edges.add(new int[]{getIndex(i, j), getIndex(i + 1, j), Math.abs(heights[i][j] - heights[i + 1][j])});
                if(j + 1 < col) edges.add(new int[]{getIndex(i, j), getIndex(i, j + 1), Math.abs(heights[i][j] - heights[i][j + 1])});
            }
        }
        Collections.sort(edges, (o1, o2) -> o1[2] - o2[2]);

        UnionFind uf = new UnionFind(row * col);
        for(int[] e : edges) {
            uf.union(e[0], e[1]);
            if(uf.isConnected(0, getIndex(row - 1, col - 1))) return e[2];
        }
        //System.out.println(uf.isConnected(0, getIndex(row - 1, col - 1)));
        return Integer.MAX_VALUE;
    }

    class UnionFind {
        int[] parent;
        public UnionFind(int n) {
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
            parent[pa] = pb;
            return true;
        }
        public boolean isConnected(int a, int b) {
            return find(a) == find(b);
        }
    }

}
