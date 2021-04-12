package algorithm.intervals;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定N个闭区间[ai,bi]，请你在数轴上选择若干区间，使得选中的区间之间互不相交（包括端点）。
 * 输出可选取区间的最大数量。
 *
 * 输入格式
 * 第一行包含整数N，表示区间数。
 * 接下来N行，每行包含两个整数ai,bi，表示一个区间的两个端点。
 *
 * 输出格式
 * 输出一个整数，表示可选取区间的最大数量。
 *
 * 数据范围
 * 1≤N≤105,
 * −109≤ai≤bi≤109
 * 输入样例：
 * 3
 * -1 1
 * 2 4
 * 3 5
 * 输出样例：
 * 2
 *
 * https://www.acwing.com/problem/content/910/
 */
public class A908_FindMostUnmeetIntervals {
    static int n;
    static int[][] intervals;

    // TODO: 将每个区间按照右端点从小到大进行排序
    public static int getCount() {
        int count = 0;
        Arrays.sort(intervals, (o1, o2) -> o1[1] - o2[1]);
        int lastRight = Integer.MIN_VALUE;
        for(int[] p : intervals) {
            if(lastRight < p[0]) {
                count++;
                lastRight = p[1];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        intervals = new int[n][2];
        for(int i = 0; i < n; i++) {
            intervals[i][0] = in.nextInt();
            intervals[i][1] = in.nextInt();
        }
        System.out.print(getCount());
    }
}
