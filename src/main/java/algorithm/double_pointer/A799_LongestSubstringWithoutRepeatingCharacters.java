package algorithm.double_pointer;

import java.util.Scanner;

/**
 * 给定一个长度为n的整数序列，请找出最长的不包含重复的数的连续区间，输出它的长度。
 *
 * 输入格式
 * 第一行包含整数n。
 *
 * 第二行包含n个整数（均在0~100000范围内），表示整数序列。
 *
 * 输出格式
 * 共一行，包含一个整数，表示最长的不包含重复的数的连续区间的长度。
 *
 * 数据范围
 * 1≤n≤100000
 * 输入样例：
 * 5
 * 1 2 2 3 5
 * 输出样例：
 * 3
 *
 *
 * https://www.acwing.com/problem/content/801/
 */
public class A799_LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        int[] nums = new int[len];
        for(int i = 0; i < len; i++) nums[i] = in.nextInt();

        int[] count = new int[100010];
        int ans = 0;
        for(int fast = 0, slow = 0; fast < len; fast++) {
            count[nums[fast]]++;
            while(count[nums[fast]] > 1) {
                count[nums[slow]]--;
                slow++;
            }
            ans = Math.max(ans, fast - slow + 1);
        }
        System.out.println(ans);
    }
}
