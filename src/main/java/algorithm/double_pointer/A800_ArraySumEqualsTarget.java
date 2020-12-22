package algorithm.double_pointer;

import java.util.Scanner;

/**
 * 给定两个升序排序的有序数组A和B，以及一个目标值x。数组下标从0开始。
 * 请你求出满足A[i] + B[j] = x的数对(i, j)。
 * 数据保证有唯一解。
 *
 * 输入格式
 * 第一行包含三个整数n，m，x，分别表示A的长度，B的长度以及目标值x。
 * 第二行包含n个整数，表示数组A。
 * 第三行包含m个整数，表示数组B。
 *
 * 输出格式
 * 共一行，包含两个整数 i 和 j。
 *
 * 数据范围
 * 数组长度不超过100000。
 * 同一数组内元素各不相同。
 * 1≤数组元素≤109
 * 输入样例：
 * 4 5 6
 * 1 2 4 7
 * 3 4 6 8 9
 * 输出样例：
 * 1 1
 *
 *
 * https://www.acwing.com/problem/content/802/
 */
public class A800_ArraySumEqualsTarget {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len1 = in.nextInt(), len2 = in.nextInt(), target = in.nextInt();
        int[] nums1 = new int[len1];
        int[] nums2 = new int[len2];

        for(int i = 0; i < len1; i++) nums1[i] = in.nextInt();
        for(int i = 0; i < len2; i++) nums2[i] = in.nextInt();

        int i = 0, j = len2 - 1;
        while(i < len1 || j >= 0) {
            if(nums1[i] + nums2[j] < target) {
                i++;
            } else if(nums1[i] + nums2[j] > target) {
                j--;
            } else {
                System.out.print(i + " " + j);
                break;
            }
        }
    }
}
