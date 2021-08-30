package dataStructure.union;

import java.util.Scanner;

/**
 * 一共有n个数，编号是1~n，最开始每个数各自在一个集合中。
 * 现在要进行m个操作，操作共有两种：
 * “M a b”，将编号为a和b的两个数所在的集合合并，如果两个数已经在同一个集合中，则忽略这个操作；
 * “Q a b”，询问编号为a和b的两个数是否在同一个集合中；
 *
 *  输入格式
 * 第一行输入整数n和m。
 * 接下来m行，每行包含一个操作指令，指令为“M a b”或“Q a b”中的一种。
 *
 * 输出格式
 * 对于每个询问指令”Q a b”，都要输出一个结果，如果a和b在同一集合内，则输出“Yes”，否则输出“No”。
 * 每个结果占一行。
 *
 * 数据范围
 * 1≤n,m≤105
 * 输入样例：
 * 4 5
 * M 1 2
 * M 3 4
 * Q 1 2
 * Q 1 3
 * Q 3 4
 * 输出样例：
 * Yes
 * No
 * Yes
 * 难度：简单
 * 时/空限制：1s / 64MB
 * 总通过数：7268
 * 总尝试数：12185
 * 来源：模板题,AcWing
 * 算法标签
 *
 * https://www.acwing.com/problem/content/838/
 */
public class A836_MergeSet {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt();
        UnionFind uf = new UnionFind(n);
        for(int i = 0; i < m; i++) {
            String op = in.next();
            int a = in.nextInt(), b = in.nextInt();
            if(op.equals("M")) uf.union(a, b);
            else System.out.println(uf.find(a) == uf.find(b) ? "Yes" : "No");
        }
    }

    public static class UnionFind {
        private int[] parent;
        public UnionFind(int n) {
            parent = new int[n + 1];
            for(int i = 1; i <= n; i++) parent[i] = i;
        }

        public int find(int a) {
            if(parent[a] != a) parent[a] = find(parent[a]);
            return parent[a];
        }

        // TODO:想一想为什么这样做不好？？？
        public int find2(int x) {
            while(parent[x] != x) {
                x = parent[x];
            }
            return x;
        }

        public void union(int a, int b) {
            int pa = find(a), pb = find(b);
            if(pa == pb) return ;
            parent[pa] = pb;
        }
    }
}
