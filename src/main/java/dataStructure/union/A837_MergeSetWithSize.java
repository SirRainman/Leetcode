package dataStructure.union;

import java.util.Scanner;

/**
 * 给定一个包含n个点（编号为1~n）的无向图，初始时图中没有边。
 *
 * 现在要进行m个操作，操作共有三种：
 * “C a b”，在点a和点b之间连一条边，a和b可能相等；
 * “Q1 a b”，询问点a和点b是否在同一个连通块中，a和b可能相等；
 * “Q2 a”，询问点a所在连通块中点的数量；
 *
 * 输入格式
 * 第一行输入整数n和m。
 * 接下来m行，每行包含一个操作指令，指令为“C a b”，“Q1 a b”或“Q2 a”中的一种。
 *
 * 输出格式
 * 对于每个询问指令”Q1 a b”，如果a和b在同一个连通块中，则输出“Yes”，否则输出“No”。
 * 对于每个询问指令“Q2 a”，输出一个整数表示点a所在连通块中点的数量
 * 每个结果占一行。
 *
 * 数据范围
 * 1≤n,m≤105
 * 输入样例：
 * 5 5
 * C 1 2
 * Q1 1 2
 * Q2 1
 * C 2 5
 * Q2 5
 * 输出样例：
 * Yes
 * 2
 * 3
 *
 *  https://www.acwing.com/problem/content/839/
 */
public class A837_MergeSetWithSize {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt();
        UnionFind uf = new UnionFind(n);

        for(int i = 0; i < m; i++) {
            String op = in.next();
            if(op.equals("C")) {
                int a = in.nextInt(), b = in.nextInt();
                uf.union(a, b);
            } else if(op.equals("Q1")) {
                int a = in.nextInt(), b = in.nextInt();
                System.out.println(uf.find(a) == uf.find(b) ? "Yes" : "No");
            } else {
                int a = in.nextInt();
                System.out.println(uf.getSize(a));
            }
        }
    }

    public static class UnionFind {
        private int[] parent, size;

        public UnionFind(int n ) {
            parent = new int[n + 1];
            for(int i = 1; i <= n; i++) parent[i] = i;
            size = new int[n + 1];
            for(int i = 1; i <= n; i++) size[i] = 1;
        }

        public int find(int a) {
            if(parent[a] != a) parent[a] = find(parent[a]);
            return parent[a];
        }

        public void union(int a, int b) {
            int pa = find(a), pb = find(b);
            if(pa == pb) return ;
            parent[pa] = pb;
            size[pb] += size[pa];
        }

        public int getSize(int a) {
            return size[find(a)];
        }
    }
}
