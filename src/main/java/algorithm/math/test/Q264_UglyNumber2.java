package algorithm.math.test;

/**
 * 编写一个程序，找出第 n 个丑数。
 * 丑数就是质因数只包含2, 3, 5 的正整数。
 *
 * 示例:
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 说明:
 *
 * 1是丑数。
 * n不超过1690。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ugly-number-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q264_UglyNumber2 {
    // TODO: 丑数的递推性质： 丑数只包含因子 2, 3, 5 ，因此有 “丑数 = 某较小丑数 * 某因子” （例如：10 = 5 * 2）。
    //  https://leetcode-cn.com/problems/chou-shu-lcof/solution/mian-shi-ti-49-chou-shu-dong-tai-gui-hua-qing-xi-t/
    //  https://leetcode-cn.com/problems/chou-shu-lcof/solution/chou-shu-ii-qing-xi-de-tui-dao-si-lu-by-mrsate/
    //  1 假设当前存在 3 个数组 nums2, nums3, nums5 分别代表丑数序列从 1 开始分别乘以 2, 3, 5 的序列
    //      nums2 = {1*2, 2*2, 3*2, 4*2, 5*2, 6*2, 8*2...}
    //      nums3 = {1*3, 2*3, 3*3, 4*3, 5*3, 6*3, 8*3...}
    //      nums5 = {1*5, 2*5, 3*5, 4*5, 5*5, 6*5, 8*5...}
    //  2 那么， 最终的丑数序列实际上就是这 3 个有序序列对的合并结果， 计算丑数序列也就是相当于 合并 3 个有序序列。
    //  3 合并 3 个有序序列， 最简单的方法就是每一个序列都各自维护一个指针， 然后比较指针指向的元素的值， 将最小的放入最终的合并数组中， 并将相应指针向后移动一个元素。
    //  时间复杂度 O(N) ： 其中 N = n，动态规划需遍历计算 dp 列表。
    //  空间复杂度 O(N) ： 长度为 N 的 dp 列表使用 O(N) 的额外空间。
    public int nthUglyNumber(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        // TODO: a, b, c是三个较小整数的指针
        int a = 1, b = 1, c = 1;
        for(int i = 2; i <= n; i++) {
            int n2 = dp[a] * 2, n3 = dp[b] * 3, n5 = dp[c] * 5;
            // TODO：Xn+1 = min(Xa×2, Xb×3, Xc×5)
            dp[i] = Math.min(Math.min(n2, n3), n5);

            // TODO：nums2, nums3, nums5 中是存在重复的解的，
            //  例如 nums2[2] == 3*2, nums3[1] == 2*3 都计算出了 6 这个结果，
            //  所以在合并 3 个有序数组的过程中， 还需要跳过相同的结果
            if(dp[i] == n2) a++;
            if(dp[i] == n3) b++;
            if(dp[i] == n5) c++;
        }
        return dp[n];
    }

}
