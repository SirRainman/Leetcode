package algorithm.double_pointer;

import java.util.Scanner;

/**
 * 给定一个长度为n的整数序列，请找出最长的不包含重复的数的连续区间，输出它的长度。
 *
 * 输入格式
 * 第一行包含整数n。
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

        int[] isExist = new int[100010];
        int slow = 0, fast = 0;
        int ans = 0;
        while(fast < len) {
            isExist[nums[fast]]++;
            // TODO: 核心思想
            //  遍历数组nums中的每一个元素nums[i],
            //  对于每一个i，找到j使得双指针[j, i]维护的是以nums[i]结尾的最长连续不重复子序列，长度为i - j + 1,
            //  将这一长度与maxLen的较大者更新给maxLen。
            while(isExist[ nums[fast] ] > 1) { // 如果该节点出现过，不重复的区间起点向前移动：直到该节点在这个区间内没有出现过
                isExist[nums[slow]]--;
                slow++;
            }
            ans = Math.max(ans, fast - slow + 1);
            fast++;
        }
        System.out.print(ans);
    }
}
