package algorithm.intervals;

import java.util.*;

/**
 * 给定 n 个区间 [li,ri]，要求合并所有有交集的区间。
 * 注意如果在端点处相交，也算有交集。
 * 输出合并完成后的区间个数。
 * 例如：[1,3]和[2,6]可以合并为一个区间[1,6]。
 *
 * 输入格式
 * 第一行包含整数n。
 * 接下来n行，每行包含两个整数 l 和 r。
 *
 * 输出格式
 * 共一行，包含一个整数，表示合并区间完成后的区间个数。
 *
 * 数据范围
 * 1≤n≤100000,
 * −109≤li≤ri≤109
 * 输入样例：
 * 5
 * 1 2
 * 2 4
 * 5 6
 * 7 8
 * 7 9
 * 输出样例：
 * 3
 *
 * https://www.acwing.com/problem/content/805/
 */
public class A803_MergeIntervals {
    // TODO；以区间左端点排序
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        int[][] intervals = new int[len][2];
        for(int i = 0; i < len; i++) {
            intervals[i][0] = in.nextInt();
            intervals[i][1] = in.nextInt();
        }

        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);

        System.out.println(cal2(len, intervals));
    }

    public static int cal1(int n, int[][] intervals) {
        List<int[]> mergedIntervals = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            int start = intervals[i][0], end = intervals[i][1];
            int currentSize = mergedIntervals.size();
            if(currentSize == 0 || mergedIntervals.get(currentSize - 1)[1] < start) { // 没有交集
                mergedIntervals.add(new int[]{start, end});
            } else { // 更新端点
                mergedIntervals.get(currentSize - 1)[1] = Math.max(end, mergedIntervals.get(currentSize - 1)[1]);
            }
        }
        return mergedIntervals.size();
    }

    public static int cal2(int n, int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        int lastRight = Integer.MIN_VALUE, res = 0;
        for(int[] in : intervals) {
            if(lastRight < in[0]) {
                res++;
            }
            lastRight = Math.max(lastRight, in[1]);
        }
        return res;
    }
}
