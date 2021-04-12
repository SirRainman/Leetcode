package algorithm.dp;

import java.util.Scanner;

/**
 * 给定一个长度为N的数列，求数值严格单调递增的子序列的长度最长是多少。
 *
 * 输入格式
 * 第一行包含整数N。
 * 第二行包含N个整数，表示完整序列。
 *
 * 输出格式
 * 输出一个整数，表示最大长度。
 *
 * 数据范围
 * 1≤N≤100000，
 * −109≤数列中的数≤109
 * 输入样例：
 * 7
 * 3 1 2 1 8 5 6
 * 输出样例：
 * 4
 *
 * https://www.acwing.com/problem/content/898/
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/
 */
public class A896_LongestAscendingSubsequence2 {
    static int n;
    static int[] nums;

    // TODO:
    //  贪心思路：如果我们要使上升子序列尽可能的长，则我们需要让序列上升得尽可能慢，因此我们希望每次在上升子序列最后加上的那个数尽可能的小。
    //  令数组q保存长度为i的上升子序列末尾元素的最小值
    public static int getMaxLength() {
        // TODO:
        //  q[i] 长度为i的上升子序列末尾元素的最小值
        //  当存在多个长度为i的子序列时，规定q[i] 为多个子序列的最后一个元素中最小的那一个
        //  例如[1, 2, 5] 和 [1, 2, 3] 优先保存[1, 2, 3] 的3，因为它更能接上的后缀种类更多
        //  即q[] 是一个单调递增的数组
        //      证明：否则存在q[i-1]>q[i]，说明长度为i的上升子序列的最小末尾元素比长度为i-1的还小，这与q[i-1]的定义不符
        int[] q = new int[n + 1];
        q[0] = (int) -2e9;

        int len = 0;
        for(int i = 0; i < n; i++) {
            // TODO: 在q[1...len]中找到满足q[l] < nums[i] < q[l + 1]
            int left = 0, right = len;
            while(left < right) {
                int mid = left + right + 1 >> 1;
                if(nums[i] <= q[mid] ) right = mid - 1;
                else left = mid;
            }
            q[left + 1] = nums[i]; // 在长度为l的上升序列中，最小末尾元素是a[i]
            len = Math.max(len, left + 1); // q[l] < nums[i] < q[l + 1]，更新数组q的长度（如果在q末尾插入，则按l + 1更新len
        }
        return len;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        nums = new int[n + 1];
        for(int i = 0; i < n; i++) nums[i] = in.nextInt();
        System.out.print(getMaxLength());
    }
}
