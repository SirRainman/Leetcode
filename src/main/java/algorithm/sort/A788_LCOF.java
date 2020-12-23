package algorithm.sort;

import java.util.Scanner;

/**
 * 给定一个长度为n的整数数列，请你计算数列中的逆序对的数量。
 * 逆序对的定义如下：对于数列的第 i 个和第 j 个元素，如果满足 i < j 且 a[i] > a[j]，则其为一个逆序对；否则不是。
 *
 * 输入格式
 * 第一行包含整数n，表示数列的长度。
 * 第二行包含 n 个整数，表示整个数列。
 *
 * 输出格式
 * 输出一个整数，表示逆序对的个数。
 *
 * 数据范围
 * 1≤n≤100000
 * 输入样例：
 * 6
 * 2 3 4 5 6 1
 * 输出样例：
 * 5
 *
 * https://www.acwing.com/problem/content/description/790/
 */
public class A788_LCOF {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        int[] nums = new int[len];
        for(int i = 0; i < len; i++) nums[i] = in.nextInt();
        int[] temp = new int[len];
        mergeSort(nums, temp, 0, len - 1);
        System.out.print(ans);
    }
    static long ans = 0;

    public static void mergeSort(int[] nums, int[] temp, int left, int right) {
        if(left >= right) return;

        int mid = left + (right - left) / 2;
        mergeSort(nums, temp, left, mid);
        mergeSort(nums, temp, mid + 1, right);

        int i = left, j = mid + 1, k = 0;
        while(i <= mid && j <= right) {
            if(nums[i] <= nums[j]) temp[k++] = nums[i++];
            else {
                temp[k++] = nums[j++];
                // TODO: 为什么 是mid−i+1 ？
                //  1 i ~ mid是递增的，
                //  2 触发条件是a[i] > a[j]，
                //  3 所以i~mid中的数字都比当前a[j]大，
                //  4 所以左边i ~ mid的数组中有 mid - i + 1个数比a[j] 大
                ans += mid - i + 1;
            }
        }
        while(i <= mid) temp[k++] = nums[i++];
        while(j <= right) temp[k++] = nums[j++];

        for(i = left, j = 0; i <= right; i++, j++) nums[i] = temp[j];
    }
}
