package algorithm.AAAcontest.ms;

import java.util.Scanner;

/**
 * @program: Leetcode
 * @description:
 * 判断是否是旋转数组
 * @author: Rain
 * @create: 2021-03-27 19:42
 **/
public class T9 {
    public static boolean isRotated(int[] nums) {
        int n = nums.length;
        int index = 0;
        while(index + 1 < n && nums[index] >= nums[index + 1]) index++;
        if(index == n - 1) return true;
        index++;
        while(index + 1 < n && nums[index] >= nums[index + 1]) index++;
        if(index != n - 1 || nums[0] > nums[index]) return false;
        return true;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int times = in.nextInt();
        while(times-- > 0) {
            int n = in.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) nums[i] = in.nextInt();
            System.out.println(isRotated(nums) ? "Y" : "N");
        }
    }
}

