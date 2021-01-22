package dataStructure.graph;

import java.util.*;

/**
 * 给你一个 n个点的带权无向连通图，节点编号为 0到 n-1，
 * 同时还有一个数组 edges，其中 edges[i] = [fromi, toi, weighti]表示在fromi和toi节点之间有一条带权无向边。
 * 最小生成树(MST) 是给定图中边的一个子集，它连接了所有节点且没有环，而且这些边的权值和最小。
 *
 * 请你找到给定图中最小生成树的所有关键边和伪关键边。如果从图中删去某条边，会导致最小生成树的权值和增加，那么我们就说它是一条关键边。
 * 伪关键边则是可能会出现在某些最小生成树中但不会出现在所有最小生成树中的边。
 * 请注意，你可以分别以任意顺序返回关键边的下标和伪关键边的下标。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ1489_FindCriticalEdgesInMinimumSpanningTree {
    // TODO: 还有tarjan算法需要看一下
    public int kruskal(int[][] edges, int n, int k, boolean hasK) {
        UnionFind uf = new UnionFind(n);
        int res = 0;
        if(hasK) {
            uf.union(edges[k][0], edges[k][1]);
            res += edges[k][2];
        }
        for(int i = 0; i < edges.length && uf.setCount > 1; i++) {
            if(i != k && uf.union(edges[i][0], edges[i][1])) res += edges[i][2];
        }
        return uf.setCount == 1 ? res : -1;
    }

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int m = edges.length;
        int[][] newEdges = new int[m][4];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < 3; j++) newEdges[i][j] = edges[i][j];
            newEdges[i][3] = i;
        }

        Arrays.sort(newEdges, (o1, o2) -> o1[2] - o2[2]);

        int value = kruskal(newEdges, n, -1, false);

        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < 2; i++) res.add(new ArrayList<>());

        for(int i = 0; i < m; i++) {
            int v = kruskal(newEdges, n, i, false);
            if(v == -1 || v > value) {
                res.get(0).add(newEdges[i][3]);
                continue;
            }

            v = kruskal(newEdges, n, i, true);
            if(v == value) res.get(1).add(newEdges[i][3]);
        }
        return res;
    }

    class UnionFind {
        int[] parent, size;
        int setCount;

        public UnionFind(int n) {
            setCount = n;
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

        public boolean isConnected(int a, int b) {
            return find(a) == find(b);
        }

        public boolean union(int a, int b) {
            int pa = find(a), pb = find(b);
            if(pa == pb) return false;

            setCount--;
            parent[pa] = pb;
            size[pb] += size[pa];

            return true;
        }
    }
}



class Solution {
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int m = edges.length;
        int[][] newEdges = new int[m][4];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < 3; ++j) {
                newEdges[i][j] = edges[i][j];
            }
            newEdges[i][3] = i;
        }
        Arrays.sort(newEdges, new Comparator<int[]>() {
            public int compare(int[] u, int[] v) {
                return u[2] - v[2];
            }
        });

        UnionFind uf = new UnionFind(n);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        for (int i = 0; i < 2; ++i) {
            ans.add(new ArrayList<Integer>());
        }
        int[] label = new int[m];
        for (int i = 0; i < m;) {
            // 找出所有权值为 w 的边，下标范围为 [i, j)
            int w = newEdges[i][2];
            int j = i;
            while (j < m && newEdges[j][2] == newEdges[i][2]) {
                ++j;
            }

            // 存储每个连通分量在图 G 中的编号
            Map<Integer, Integer> compToId = new HashMap<Integer, Integer>();
            // 图 G 的节点数
            int gn = 0;

            for (int k = i; k < j; ++k) {
                int x = uf.findset(newEdges[k][0]);
                int y = uf.findset(newEdges[k][1]);
                if (x != y) {
                    if (!compToId.containsKey(x)) {
                        compToId.put(x, gn++);
                    }
                    if (!compToId.containsKey(y)) {
                        compToId.put(y, gn++);
                    }
                } else {
                    // 将自环边标记为 -1
                    label[newEdges[k][3]] = -1;
                }
            }

            // 图 G 的边
            List<Integer>[] gm = new List[gn];
            List<Integer>[] gmid = new List[gn];
            for (int k = 0; k < gn; ++k) {
                gm[k] = new ArrayList<Integer>();
                gmid[k] = new ArrayList<Integer>();
            }

            for (int k = i; k < j; ++k) {
                int x = uf.findset(newEdges[k][0]);
                int y = uf.findset(newEdges[k][1]);
                if (x != y) {
                    int idx = compToId.get(x), idy = compToId.get(y);
                    gm[idx].add(idy);
                    gmid[idx].add(newEdges[k][3]);
                    gm[idy].add(idx);
                    gmid[idy].add(newEdges[k][3]);
                }
            }

            List<Integer> bridges = new TarjanSCC(gn, gm, gmid).getCuttingEdge();
            // 将桥边（关键边）标记为 1
            for (int id : bridges) {
                ans.get(0).add(id);
                label[id] = 1;
            }

            for (int k = i; k < j; ++k) {
                uf.unite(newEdges[k][0], newEdges[k][1]);
            }

            i = j;
        }

        // 未标记的边即为非桥边（伪关键边）
        for (int i = 0; i < m; ++i) {
            if (label[i] == 0) {
                ans.get(1).add(i);
            }
        }

        return ans;
    }
}

// 并查集模板
class UnionFind {
    int[] parent;
    int[] size;
    int n;
    // 当前连通分量数目
    int setCount;

    public UnionFind(int n) {
        this.n = n;
        this.setCount = n;
        this.parent = new int[n];
        this.size = new int[n];
        Arrays.fill(size, 1);
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
        }
    }

    public int findset(int x) {
        return parent[x] == x ? x : (parent[x] = findset(parent[x]));
    }

    public boolean unite(int x, int y) {
        x = findset(x);
        y = findset(y);
        if (x == y) {
            return false;
        }
        if (size[x] < size[y]) {
            int temp = x;
            x = y;
            y = temp;
        }
        parent[y] = x;
        size[x] += size[y];
        --setCount;
        return true;
    }

    public boolean connected(int x, int y) {
        x = findset(x);
        y = findset(y);
        return x == y;
    }
}

class TarjanSCC {
    List<Integer>[] edges;
    List<Integer>[] edgesId;
    int[] low;
    int[] dfn;
    List<Integer> ans;
    int n;
    int ts;

    public TarjanSCC(int n, List<Integer>[] edges, List<Integer>[] edgesId) {
        this.edges = edges;
        this.edgesId = edgesId;
        this.low = new int[n];
        Arrays.fill(low, -1);
        this.dfn = new int[n];
        Arrays.fill(dfn, -1);
        this.n = n;
        this.ts = -1;
        this.ans = new ArrayList<Integer>();
    }

    public List<Integer> getCuttingEdge() {
        for (int i = 0; i < n; ++i) {
            if (dfn[i] == -1) {
                getCuttingEdge(i, -1);
            }
        }
        return ans;
    }

    private void getCuttingEdge(int u, int parentEdgeId) {
        low[u] = dfn[u] = ++ts;
        for (int i = 0; i < edges[u].size(); ++i) {
            int v = edges[u].get(i);
            int id = edgesId[u].get(i);
            if (dfn[v] == -1) {
                getCuttingEdge(v, id);
                low[u] = Math.min(low[u], low[v]);
                if (low[v] > dfn[u]) {
                    ans.add(id);
                }
            } else if (id != parentEdgeId) {
                low[u] = Math.min(low[u], dfn[v]);
            }
        }
    }
}