package algorithm.AAAcontest.amazon;

import java.util.Scanner;

/**
 * @program: Leetcode
 * @description:
 * 已知：一个整数数组中，给定若干个边将下标上的点归到一个集合中
 * 问：形成的不同的集合，那个集合的权值最大
 * @author: Rain
 * @create: 2021-08-28 20:25
 **/
public class TeamPower {
    static class UnionFind {
        private int[] parent;
        private int[] weight;
        private int maxWeight = 0;

        public UnionFind(int n, int[] weight) {
            parent = new int[n];
            for(int i = 0; i < n; i++) parent[i] = i;
            this.weight = weight;
        }

        public int find(int a) {
            if(parent[a] != a) parent[a] = find(parent[a]);
            return parent[a];
        }

        public void union(int a, int b) {
            int pa = find(a), pb = find(b);
            if(pa == pb) return ;
            parent[pa] = pb;
            weight[pb] += weight[pa];
            maxWeight = Math.max(maxWeight, weight[pb]);
        }

        public int getMaxWeight() {
            return maxWeight;
        }
    }

    public static void main(String[] args) {
        // Scanner in = new Scanner(System.in);
        // int n = in.nextInt(), m = in.nextInt();
        //
        // int[][] edges = new int[m][2];
        // for(int i = 0; i < m; i++) {
        //     edges[i][0] = in.nextInt();
        //     edges[i][1] = in.nextInt();
        // }
        //
        // int[] nums = new int[n];
        // for(int i = 0; i < n; i++) {
        //     nums[i] = in.nextInt();
        // }

        int n = 6;
        int[] nums = new int[]{11, 2, 3, 15, 8, 22};
        int[][] edges = new int[][]{{1, 2}, {2, 3}, {4, 5}};

        UnionFind uf = new UnionFind(n, nums);
        for(int[] edge : edges) {
            uf.union(edge[0] - 1, edge[1] -1);
        }

        System.out.println(uf.getMaxWeight());
    }
}
