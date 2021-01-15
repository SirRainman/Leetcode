package dataStructure.graph.dfs;

import java.util.*;

/**
 * 给定一个整数n，将数字1~n排成一排，将会有很多种排列方法。
 * 现在，请你按照字典序将所有的排列方法输出。
 * 
 * 输入格式
 * 共一行，包含一个整数n。
 * 
 * 输出格式
 * 按字典序输出所有排列方案，每个方案占一行。
 * 
 * 数据范围
 * 1≤n≤7
 * 输入样例：
 * 3
 * 输出样例：
 * 1 2 3
 * 1 3 2
 * 2 1 3
 * 2 3 1
 * 3 1 2
 * 3 2 1
 * 
 * 
 * https://www.acwing.com/problem/content/description/844/
 */
public class A842_CombinationNum {

    static Deque<Integer> path = new LinkedList<>();
    static boolean[] st ;
    static int n;

    static void dfs(int count) {
        if(count == n) {
            for(int node : path) System.out.print(node + " ");
            System.out.println();
            return;
        }
        for(int i = 1; i <= n; i++) {
            if(st[i] == false) {
                path.push(i);
                st[i] = true;
                dfs(count + 1);
                st[i] = false;
                path.pop();
            }
        }
    }

    // TODO : 看一下q46的解法
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        st = new boolean[n + 1];
        dfs(0);
    }

}
