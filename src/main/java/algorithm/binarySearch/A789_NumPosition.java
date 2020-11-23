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
    static int binarySearch(int[] nums, int target) {
        // TODO:注意right = len，所以 mid = left + (right - left) / 2 是在中间靠右的位置
        //  为什么mid在中间偏右的位置，left = mid + 1不会越界？？？
        //  是不是因为只有第一次算mid的时候是靠右的，其他的情况都是靠左？？？
        int left = 0, right = nums.length;
        while(left < right) {
            // TODO:注意mid = left + (right - left) / 2;
            int mid = left + (right - left) / 2;
            // TODO:注意区间的变化
            if(target >= nums[mid]) left = mid + 1;
            else right = mid;
        }
        return left;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt(), times = in.nextInt();
        int[] nums = new int[len];
        for(int i = 0; i < len; i ++) nums[i] = in.nextInt();

        while(times-- > 0) {
            int target = in.nextInt();
            if(target < nums[0] || target > nums[len - 1]) {
                System.out.println("-1 -1");
                continue;
            }
            int start = binarySearch(nums, target - 1);
            if(start == len || nums[start] != target) {
                System.out.println("-1 -1");
                continue;
            }
            int end = binarySearch(nums, target);
            System.out.println(start + " " + (end - 1));
        }
    }


    public static void binarySearch2(){
        Scanner in = new Scanner(System.in);
        int len = in.nextInt(), times = in.nextInt();
        int[] nums = new int[len];
        for(int i = 0; i < len; i ++) nums[i] = in.nextInt();

        while(times-- > 0) {
            int target = in.nextInt();
            if(target < nums[0] || target > nums[len - 1]) {
                System.out.println("-1 -1");
                continue;
            }

            int start = -1, end = -1;
            // TODO：注意right = len - 1 而不是 len
            int left = 0, right = len - 1;
            while(left < right) {
                // TODO:mid在中间靠左的位置，所以left = mid + 1不会越界
                int mid = left + (right - left) / 2;
                if(target <= nums[mid]) right = mid;
                else left = mid + 1;
            }
            if(nums[left] != target) {
                System.out.println("-1 -1");
                continue;
            }
            start = left;

            left = 0;
            right = len - 1;
            while(left < right) {
                // TODO：mid在中间靠右的位置，所以right = mid - 1不会越界
                int mid = left + (right - left + 1) / 2;
                if(target >= nums[mid]) left = mid;
                else right = mid - 1;
            }
            end = left;
            System.out.println(start + " " + end);
        }
    }
}
