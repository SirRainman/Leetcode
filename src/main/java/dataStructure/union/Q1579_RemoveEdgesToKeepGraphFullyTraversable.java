package dataStructure.union;

/**
 * Alice 和 Bob 共有一个无向图，其中包含 n 个节点和 3 种类型的边：
 * 类型 1：只能由 Alice 遍历。
 * 类型 2：只能由 Bob 遍历。
 * 类型 3：Alice 和 Bob 都可以遍历。
 *
 * 给你一个数组 edges ，其中 edges[i] = [typei, ui, vi]表示节点 ui 和 vi 之间存在类型为 typei 的双向边。
 * 请你在保证图仍能够被 Alice和 Bob 完全遍历的前提下，找出可以删除的最大边数。
 * 如果从任何节点开始，Alice 和 Bob 都可以到达所有其他节点，则认为图是可以完全遍历的。
 * 返回可以删除的最大边数，如果 Alice 和 Bob 无法完全遍历图，则返回 -1 。
 *
 * 提示：
 * 1 <= n <= 10^5
 * 1 <= edges.length <= min(10^5, 3 * n * (n-1) / 2)
 * edges[i].length == 3
 * 1 <= edges[i][0] <= 3
 * 1 <= edges[i][1] < edges[i][2] <= n
 * 所有元组 (typei, ui, vi) 互不相同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q1579_RemoveEdgesToKeepGraphFullyTraversable {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        UnionFind aliceUF = new UnionFind(n);
        UnionFind bobUF = new UnionFind(n);
        int count = 0;
        // 先判断公有边是不是可以删除的
        for(int[] e : edges) {
            if(e[0] == 3) {
                if(!aliceUF.union(e[1], e[2])) count++;
                else if(!bobUF.union(e[1], e[2])) count++;
            }
        }
        // 再判断私有是不是可以删除的
        for(int[] e : edges) {
            if(e[0] == 1 && !aliceUF.union(e[1], e[2])) count++;
            if(e[0] == 2 && !bobUF.union(e[1], e[2])) count++;
        }
        return aliceUF.size == 1 && bobUF.size == 1 ? count : -1;
    }

    class UnionFind {
        int[] parent;
        int size;
        public UnionFind(int n) {
            size = n;
            parent = new int[n + 1];
            for(int i = 1; i <= n; i++) parent[i] = i;
        }
        public int find(int a) {
            if(parent[a] != a) parent[a] = find(parent[a]);
            return parent[a];
        }
        public boolean union(int a, int b) {
            int pa = find(a), pb = find(b);
            if(pa == pb) return false;
            parent[pa] = pb;
            size--;
            return true;
        }
    }
}



















