package dataStructure.graph.dfs;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定一颗树，树中包含n个结点（编号1~n）和n-1条无向边。
 * 请你找到树的重心，并输出将重心删除后，剩余各个连通块中点数的最大值。
 * 重心定义：重心是指树中的一个结点，如果将这个点删除后，剩余各个连通块中点数的最大值最小，那么这个节点被称为树的重心。
 *
 * 输入格式
 * 第一行包含整数n，表示树的结点数。
 * 接下来n-1行，每行包含两个整数a和b，表示点a和点b之间存在一条边。
 *
 * 输出格式
 * 输出一个整数m，表示将重心删除后，剩余各个连通块中点数的最大值。
 *
 * 数据范围
 * 1≤n≤105
 * 输入样例
 * 9
 * 1 2
 * 1 7
 * 1 4
 * 2 8
 * 2 5
 * 4 3
 * 3 9
 * 4 6
 * 输出样例：
 * 4
 *
 * https://www.acwing.com/problem/content/848/
 */
public class HardA846_TreeCenterOfGravity {
    static int[] head, e, next; // 拉链式结构，存储每个节点的孩子节点
    static int idx = 0, ans, n;
    static boolean[] isVisited;

    public static void add(int a, int b) {
        e[idx] = b;
        next[idx] = head[a];
        head[a] = idx;
        idx++;
    }

    // 返回以u为跟节点的子树的重量（节点数）
    public static int dfs(int u) {
        isVisited[u] = true;
        int res = 0, sum = 1;
        for(int v = head[u]; v != -1; v = next[v]) {
            if(isVisited[e[v]]) continue;
            int s = dfs(e[v]); // 每个孩子节点的节点数
            res = Math.max(res, s); // 求他的孩子节点中，体重（节点数目）最大的那一个
            sum += s; // 每个节点的总重量
        }
        // TODO：将重心删除掉之后，会划分出多个连通图，多个连通图中谁最有可能成为最大的连通图？
        //  1 去掉以该节点为根的树
        //  2 以该节点为根的树中最重的那个孩子节点
        res = Math.max(res, n - sum);
        ans = Math.min(res, ans); // TODO：注意树的重心的定义，反正我不太懂！！！如果将这个点删除后，剩余各个连通块中点数的最大值最小，那么这个节点被称为树的重心。
        return sum;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        ans = n;
        head = new int[n + 1]; Arrays.fill(head, -1);
        isVisited = new boolean[n + 1];
        e = new int[2 * (n + 1)];
        next = new int[2 * (n + 1)];

        for(int i = 0; i < n - 1; i++) {
            int a = in.nextInt(), b = in.nextInt();
            add(a, b);
            add(b, a);
        }
        dfs(1);
        System.out.println(ans);
    }
}
