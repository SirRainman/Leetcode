package algorithm.sort;

import java.util.Scanner;

/**
 * 给定一个长度为n的整数数列，以及一个整数k，请用快速选择算法求出数列从小到大排序后的第k个数。
 *
 * 输入格式
 * 第一行包含两个整数 n 和 k。
 *
 * 第二行包含 n 个整数（所有整数均在1~109范围内），表示整数数列。
 *
 * 输出格式
 * 输出一个整数，表示数列的第k小数。
 *
 * 数据范围
 * 1 ≤ n ≤ 100000,
 * 1 ≤ k ≤ n
 * 输入样例：
 * 5 3
 * 2 4 1 5 3
 * 输出样例：
 * 3
 *
 * https://www.acwing.com/problem/content/788/
 */
public class A786_KthLargest {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), k = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = in.nextInt();
        }

        // quickSort(nums, 0, n - 1);

        System.out.println(quickSort(nums, 0, n - 1, k - 1));
    }

    public static int quickSort(int[] nums, int left, int right, int k) {

        int mid = partition(nums, left, right);
        if (mid == k) {
            return nums[k];
        } else if (mid > k) {
            return quickSort(nums, left, mid - 1, k);
        } else {
            return quickSort(nums, mid + 1, right, k);
        }

    }

    public static int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        while (left < right) {
            while (left < right && pivot <= nums[right]) right--;
            if (left < right) nums[left] = nums[right];

            while (left < right && pivot >= nums[left]) left++;
            if (left < right) nums[right] = nums[left];
        }
        nums[left] = pivot;
        return left;
    }

}
