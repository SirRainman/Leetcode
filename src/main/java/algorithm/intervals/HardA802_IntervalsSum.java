package algorithm.intervals;

import java.util.*;

/**
 * 假定有一个无限长的数轴，数轴上每个坐标上的数都是0。
 * 现在，我们首先进行 n 次操作，每次操作将某一位置x上的数加c。
 * 接下来，进行 m 次询问，每个询问包含两个整数l和r，你需要求出在区间[l, r]之间的所有数的和。
 *
 * 输入格式
 * 第一行包含两个整数n和m。
 * 接下来 n 行，每行包含两个整数x和c。
 * 再接下里 m 行，每行包含两个整数l和r。
 *
 * 输出格式
 * 共m行，每行输出一个询问中所求的区间内数字和。
 *
 * 数据范围
 * −109≤x≤109,
 * 1≤n,m≤105,
 * −109≤l≤r≤109,
 * −10000≤c≤10000
 * 输入样例：
 * 3 3
 * 1 2
 * 3 6
 * 7 5
 * 1 3
 * 4 6
 * 7 8
 * 输出样例：
 * 8
 * 0
 * 5
 *
 *
 * https://www.acwing.com/problem/content/description/804/
 */
public class A802_IntervalsSum {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt(), times = in.nextInt();
        int[][] add = new int[len][2];
        int[][] query = new int[times][2];

        // 记录区间端点的值
        Set<Integer> intervalPoints = new HashSet<>();

        for (int i = 0; i < len; i++) {
            add[i][0] = in.nextInt();
            add[i][1] = in.nextInt();
            // 把点位加到集合里
            intervalPoints.add(add[i][0]);
        }

        for (int i = 0; i < times; i++) {
            query[i][0] = in.nextInt();
            query[i][1] = in.nextInt();
            // 把查询的点位加入到区间集合里
            intervalPoints.add(query[i][0]);
            intervalPoints.add(query[i][1]);
        }

        // 对区间的点位进行排序
        List<Integer> list = new ArrayList<>(intervalPoints);
        // TODO：注意对list的排序方法
        list.sort(Comparator.comparingInt(x -> x));

        int[] diff = new int[list.size() + 1]; // 记录端点间的差值
        int[] sum = new int[list.size() + 1];  // 记录区间里的和值

        for (int[] item : add) {
            int index = binaryFind(list, item[0]);
            diff[index] += item[1];
        }

        for (int i = 1; i <= list.size(); i++) {
            sum[i] = sum[i - 1] + diff[i];
        }

        for (int[] item : query) {
            int left = binaryFind(list, item[0]), right = binaryFind(list, item[1]);
            System.out.println(sum[right] - sum[left - 1]);
        }
    }


    // TODO:找到第一个比x大的数在数组中的位置
    //  注意为什么最后返回的是left + 1
    public static int binaryFind(List<Integer> list, int x) {
        // TODO:注意这里的二分查找是 right = len-1
        int left = 0, right = list.size() - 1;
        while (left < right) {
            int mid = left + (right - left >> 1);
            if (list.get(mid) >= x) right = mid;
            else left = mid + 1;
        }
        return left + 1;
    }

}
