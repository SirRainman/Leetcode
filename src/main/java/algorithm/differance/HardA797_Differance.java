package algorithm.differance;

import java.util.Scanner;

/**
 * 输入一个长度为n的整数序列。
 *
 * 接下来输入m个操作，每个操作包含三个整数l, r, c，表示将序列中[l, r]之间的每个数加上c。
 *
 * 请你输出进行完所有操作后的序列。
 *
 * 输入格式
 * 第一行包含两个整数n和m。
 *
 * 第二行包含n个整数，表示整数序列。
 *
 * 接下来m行，每行包含三个整数l，r，c，表示一个操作。
 *
 * 输出格式
 * 共一行，包含n个整数，表示最终序列。
 *
 * 数据范围
 * 1≤n,m≤100000,
 * 1≤l≤r≤n,
 * −1000≤c≤1000,
 * −1000≤整数序列中元素的值≤1000
 * 输入样例：
 * 6 3
 * 1 2 2 1 2 1
 * 1 3 1
 * 3 5 1
 * 1 6 1
 * 输出样例：
 * 3 4 5 3 4 2
 *
 *
 * https://www.acwing.com/problem/content/description/799/
 */
public class HardA797_Differance {

    public static void insert(int[] differance, int left, int right, int c) {
        differance[left] += c;
        differance[right + 1] -= c;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        int times = in.nextInt();

        // TODO: 注意差分 和 序列和 之间的关系，他们是一个互逆的关系。
        //  一定要注意差分的数学定义，和他的递推公式：diff[i] = nums[i] - nums[i - 1]
        //  前缀和：就是从第一个元素到当前元素的和。
        //  差分数组：就是当前元素与上一个元素的差。
        //  初始化的时间复杂度为O(n),区间增加的时间复杂度为O(1)，单点查询的时间复杂度为O(n)。
        int[] nums = new int[len + 1];
        int[] differance = new int[ len + 2];

        for(int i = 1; i <= len; i++) {
            nums[i] = in.nextInt();
            // differance[i] = nums[i] - nums[i - 1];
            insert(differance, i, i, nums[i]);
        }

        while(times-- > 0) {
            int start = in.nextInt(), end = in.nextInt(), c = in.nextInt();
            insert(differance, start, end, c);
        }

        for(int i = 1; i <= len; i++) {
            nums[i] = nums[i - 1] + differance[i];
            System.out.print(nums[i] + " ");
        }
    }
}
