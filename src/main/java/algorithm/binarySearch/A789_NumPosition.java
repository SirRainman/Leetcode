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
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt(), times = in.nextInt();
        int[] nums = new int[len];
        for(int i = 0; i < len; i++) nums[i] = in.nextInt();
        while(times-- > 0) {
            int x = in.nextInt();
            if(x < nums[0] || x > nums[nums.length - 1]) {
                System.out.println("-1 -1");
                continue;
            }

            // TODO:注意传递的参数是len,不是 len - 1
            int start = binarySearch(nums, 0, len, x - 1);
            if(start == len || nums[start] != x) {
                System.out.println("-1 -1");
                continue;
            }

            int end = binarySearch(nums, 0, len, x);
            System.out.println(start + " "  + (end - 1));
        }
    }

    public static int binarySearch(int[] nums, int left, int right, int x) {
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] <= x) left = mid + 1;
            else right = mid;
        }
        return left;
    }


    public static void binarySearch2(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int q = in.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) nums[i] = in.nextInt();
        while(q-- > 0) {
            int x = in.nextInt();
            int l = 0, r = n - 1;
            while(l < r){
                // TODO：想一想为什么 mid = l + r >> 1 ？？？
                int mid = l + r >> 1;
                // TODO：想一想为什么 l = mid + 1 ？？？
                if(nums[mid] < x) l = mid + 1;
                else r = mid;
            }
            if(nums[l] != x){
                System.out.println("-1 -1");
                continue;
            }
            else System.out.print(l + " ");
            l = 0;
            r = n - 1;
            while(l < r){
                // TODO：想一想为什么 mid = l + r + 1 >> 1 ？？？
                int mid = l + r + 1 >> 1;
                // TODO：想一想为什么 r = mid - 1 ？？？
                if(nums[mid] > x) r = mid - 1;
                else l = mid;
            }
            System.out.println(l);
        }
    }
}
