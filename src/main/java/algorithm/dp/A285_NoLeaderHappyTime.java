package algorithm.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Ural大学有N名职员，编号为1~N。
 *
 * 他们的关系就像一棵以校长为根的树，父节点就是子节点的直接上司。
 *
 * 每个职员有一个快乐指数，用整数 Hi 给出，其中 1≤i≤N。
 *
 * 现在要召开一场周年庆宴会，不过，没有职员愿意和直接上司一起参会。
 *
 * 在满足这个条件的前提下，主办方希望邀请一部分职员参会，使得所有参会职员的快乐指数总和最大，求这个最大值。
 *
 * 输入格式
 * 第一行一个整数N。
 *
 * 接下来N行，第 i 行表示 i 号职员的快乐指数Hi。
 *
 * 接下来N-1行，每行输入一对整数L, K,表示K是L的直接上司。
 *
 * 输出格式
 * 输出最大的快乐指数。
 *
 * 数据范围
 * 1≤N≤6000,
 * −128≤Hi≤127
 * 输入样例：
 * 7
 * 1
 * 1
 * 1
 * 1
 * 1
 * 1
 * 1
 * 1 3
 * 2 3
 * 6 4
 * 7 4
 * 4 5
 * 3 5
 * 输出样例：
 * 5
 *
 *
 * https://www.acwing.com/problem/content/287/
 *
 */
public class A285_NoLeaderHappyTime {
    static int n;
    static int[] happy;
    static boolean[] hasFather;
    static int idx;
    static int[] e, head, next;
    static int[][] dp;


    public static void add(int a, int b) {
        e[idx] = b;
        next[idx] = head[a];
        head[a] = idx;
        idx++;
    }

    public static void dfs(int u) {
        for(int i = head[u]; i != -1; i = next[i]) {
            int v = e[i];
            dfs(v); // 先遍历确定 孩子节点
            dp[u][0] += Math.max(dp[v][1], dp[v][0]);
            dp[u][1] += dp[v][0];
        }
        dp[u][1] += happy[u];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();

        e = new int[n + 1];
        head = new int[n + 1];
        Arrays.fill(head, -1);
        next = new int[n + 1];

        happy = new int[n + 1];
        for(int i = 1; i <= n; i++) happy[i] = in.nextInt();

        hasFather = new boolean[n + 1]; // 有没有父节点
        for(int i = 1; i <= n - 1; i++) {
            int a = in.nextInt(), b = in.nextInt();
            add(b, a);
            hasFather[a] = true;
        }

        // 找到根节点
        int root = 0;
        for(int i = 1; i <= n; i++) {
            if(!hasFather[i]) {
                root = i;
                break;
            }
        }

        // TODO：
        //  1 集合划分
        //      1 dp[i][0] 表示的是以i为根的子树在 不选i 的情况下的参加舞会的方案的快乐值
        //      2 dp[i][1] 表示的是以i为根的子树在 选i 的情况下的参加舞会的方案的快乐值
        //  2 属性：max
        //  3 状态计算
        //      dp[u][0] += Math.max(dp[j][0], dp[j][1]);  j是u的子节点，u不参加，他的子节点即可参加，也可以不参加
        //      dp[u][1] += dp[j][0];
        dp = new int[n + 1][2];
        dfs(root);

        int res = Math.max(dp[root][1], dp[root][0]);
        System.out.print(res);
    }

}
