package algorithm.binarySearch;

import java.util.Scanner;

/**
 * 给定一个按照升序排列的长度为n的整数数组，以及 q 个查询。
 * 对于每个查询，返回一个元素k的起始位置和终止位置（位置从0开始计数）。
 * 如果数组中不存在该元素，则返回“-1 -1”。
 *
 * 输入格式
 * 第一行包含整数n和q，表示数组长度和询问个数。
 *
 * 第二行包含n个整数（均在1~10000范围内），表示完整数组。
 *
 * 接下来q行，每行包含一个整数k，表示一个询问元素。
 *
 * 输出格式
 * 共q行，每行包含两个整数，表示所求元素的起始位置和终止位置。
 *
 * 如果数组中不存在该元素，则返回“-1 -1”。
 *
 * 数据范围
 * 1≤n≤100000
 * 1≤q≤10000
 * 1≤k≤10000
 * 输入样例：
 * 6 3
 * 1 2 2 3 3 4
 * 3
 * 4
 * 5
 * 输出样例：
 * 3 4
 * 5 5
 * -1 -1
 *
 * https://www.acwing.com/activity/content/code/content/548089/
 */
public class A789_NumPosition {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int q = in.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) nums[i] = in.nextInt();
        while(q-- > 0) {
            int x = in.nextInt();
            if(x < nums[0] || x > nums[n-1]) {
                System.out.println("-1 -1");
                continue;
            }
            int start = binarySearch(nums, x-1);
            // 如果不在数组中
            if(start == nums.length || nums[start] != x) {
                System.out.println("-1 -1");
                continue;
            }
            int end = binarySearch(nums, x) - 1;
            System.out.println(start + " " + end);
        }
    }

    public static int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length;
        while(left < right) {
            // TODO：注意寻找的方向，向左找，还是向右找
            int mid = left + right >> 1;
            if(nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
