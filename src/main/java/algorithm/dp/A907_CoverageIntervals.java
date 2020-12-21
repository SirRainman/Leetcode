package algorithm.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定N个闭区间[ai,bi]以及一个线段区间[s,t]，请你选择尽量少的区间，将指定线段区间完全覆盖。
 * 输出最少区间数，如果无法完全覆盖则输出-1。
 *
 * 输入格式
 * 第一行包含两个整数s和t，表示给定线段区间的两个端点。
 * 第二行包含整数N，表示给定区间数。
 * 接下来N行，每行包含两个整数ai,bi，表示一个区间的两个端点。
 *
 * 输出格式
 * 输出一个整数，表示所需最少区间数。
 * 如果无解，则输出-1。
 *
 * 数据范围
 * 1≤N≤105,
 * −109≤ai≤bi≤109,
 * −109≤s≤t≤109
 * 输入样例：
 * 1 5
 * 3
 * -1 3
 * 2 4
 * 3 5
 * 输出样例：
 * 2
 *
 * https://www.acwing.com/problem/content/909/
 */
public class A907_CoverageIntervals {
    static int n, start, end;
    static int[][] intervals;

    public static int getCount() {
        int res = 0;
        boolean flag = false;

        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]); // 区间按左端点进行排序

        for(int i = 0; i < n; i++) {
            int j = i, right = Integer.MIN_VALUE;
            while(j < n && intervals[j][0] <= start) { // 找到能覆盖住当前节点的，最远的右端点的区间
                right = Math.max(right, intervals[j][1]);
                j++;
            }
            if(right < start) break; // 如果覆盖不了当前节点
            res++;
            if(right >= end) { // 如果能够覆盖终点
                flag = true;
                break;
            }
            start = right; // 更新起点
            i = j - 1; // 更新区间
        }

        return flag == true ? res : -1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        start = in.nextInt(); end = in.nextInt();
        n = in.nextInt();
        intervals = new int[n][2];
        for(int i = 0; i < n; i++) {
            intervals[i][0] = in.nextInt();
            intervals[i][1] = in.nextInt();
        }
        System.out.print(getCount());
    }
}
