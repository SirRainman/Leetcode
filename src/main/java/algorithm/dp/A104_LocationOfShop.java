package algorithm.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 在一条数轴上有 N 家商店，它们的坐标分别为 A1~AN。
 * 现在需要在数轴上建立一家货仓，每天清晨，从货仓到每家商店都要运送一车商品。
 * 为了提高效率，求把货仓建在何处，可以使得货仓到每家商店的距离之和最小。
 *
 * 输入格式
 * 第一行输入整数N。
 * 第二行N个整数A1~AN。
 *
 * 输出格式
 * 输出一个整数，表示距离之和的最小值。
 *
 * 数据范围
 * 1 ≤ N ≤ 100000
 * 输入样例：
 * 4
 * 6 2 9 1
 * 输出样例：
 * 12
 *
 *
 * https://www.acwing.com/problem/content/106/
 */
public class A104_LocationOfShop {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) nums[i] = in.nextInt();
        // TODO：想一想为什么这么排序？？？
        Arrays.sort(nums);
        int res = 0;
        for(int i = 0; i < n; i++) res += Math.abs(nums[i] - nums[n / 2]);
        // int left = 0, right = n - 1;
        // while(left < right) {
        //     res += nums[right--] - nums[left++];
        // }
        System.out.print(res);
    }
}
