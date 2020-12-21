package algorithm.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定N个闭区间[ai,bi]，请你在数轴上选择尽量少的点，使得每个区间内至少包含一个选出的点。
 *
 * 输出选择的点的最小数量。
 *
 * 位于区间端点上的点也算作区间内。
 *
 * 输入格式
 * 第一行包含整数N，表示区间数。
 *
 * 接下来N行，每行包含两个整数ai,bi，表示一个区间的两个端点。
 *
 * 输出格式
 * 输出一个整数，表示所需的点的最小数量。
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
 * https://www.acwing.com/problem/content/907/
 */
public class A905_FindPointsInIntervals {
    static int n;
    static int[][] intervals;

    // TODO:
    //  假设ans是最优解，cnt是可行解，显然有ans≤cnt
    //  由于算法最后得出cnt个两两不相交的区间，覆盖每个区间至少需要cnt个点，因此ans ≥ cnt
    //  综上所述ans=cnt
    public static int getCount() {
        int count = 0;
        // TODO：为什么对右端点进行排序？？？
        Arrays.sort(intervals, (o1, o2) -> (o1[1] - o2[1])); // 将每个区间按照右端点从小到大进行排序

        int lastRight = Integer.MIN_VALUE; // lastRight 值初始化为无穷小，从前往后枚举区间
        for(int[] interval : intervals) {
            int l = interval[0], r = interval[1];
            if(lastRight < l) { // 当前区间不包括该点，即左端点大于该店
                lastRight = r; // 则把记忆区间更新成当前遍历的区间(只更新右端点，因为只在右端点放点)
                count++;
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
