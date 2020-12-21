package algorithm.dp;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 给定N个闭区间[ai,bi]，请你将这些区间分成若干组，使得每组内部的区间两两之间（包括端点）没有交集，并使得组数尽可能小。
 * 输出最小组数。
 *
 * 输入格式
 * 第一行包含整数N，表示区间数。
 * 接下来N行，每行包含两个整数ai,bi，表示一个区间的两个端点。
 *
 * 输出格式
 * 输出一个整数，表示最小组数。
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
 *
 * https://www.acwing.com/problem/content/908/
 */
public class A906_IntervalsGroups {
    static int n;
    static int[][] intervals;

    public static int getCount() {
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        // TODO:为什么用小根堆来存储所有组中的最大右端点值？？？
        //  每个组中的区间都是按照左端点排序的，
        //  即 每一个组中的所有区间的的左端点 <= p[0]
        //  若 p[1] <= 组中的最右边边的端点，说明一定有交集
        //  若 组中的最右边边的端点 < p[0]，说明和该组中的区间没有交集，可以放进该组了
        //  判断当前区间是否能放入到当前已有的组中，只需找到当前所有组的最小右端点值
        //      假如 当前所有组的最小右端点值 < p[0]
        //      说明存在这样一个组，这个区间和该组没有交集，可以放到这个组中
        PriorityQueue<Integer> queue = new PriorityQueue<>(); // 用来存放每组区间中的最右边的端点

        for(int[] p : intervals) {
            if( !queue.isEmpty() && queue.peek() < p[0]) { // 如果该区间能够在所有组中找到一个可以放的组
                queue.poll(); // 先把该组最右边的端点拿出来，后面再更新
            }
            queue.offer(p[1]);
        }

        return queue.size();
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
