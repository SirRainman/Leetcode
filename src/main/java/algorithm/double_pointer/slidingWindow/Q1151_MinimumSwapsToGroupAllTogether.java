package algorithm.double_pointer.slidingWindow;

/**
 * @program: Leetcode
 * @description:
 * 给出一个二进制数组data，你需要通过交换位置，将数组中 任何位置 上的 1 组合到一起，并返回所有可能中所需最少的交换次数。
 *
 * 示例 1：
 * 输入：[1,0,1,0,1]
 * 输出：1
 * 解释： 
 * 有三种可能的方法可以把所有的 1 组合在一起：
 * [1,1,1,0,0]，交换 1 次；
 * [0,1,1,1,0]，交换 2 次；
 * [0,0,1,1,1]，交换 1 次。
 * 所以最少的交换次数为 1。
 *
 * 示例 2：
 * 输入：[0,0,0,1,0]
 * 输出：0
 * 解释： 
 * 由于数组中只有一个 1，所以不需要交换。
 *
 * 示例 3：
 * 输入：[1,0,1,0,1,0,0,1,1,0,1]
 * 输出：3
 * 解释：
 * 交换 3 次，一种可行的只用 3 次交换的解决方案是 [0,0,0,0,0,1,1,1,1,1,1]。
 * 
 *
 * 提示：
 * 1 <= data.length <= 10^5
 * 0 <= data[i] <= 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-swaps-to-group-all-1s-together
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-10-05 15:45
 **/
public class Q1151_MinimumSwapsToGroupAllTogether {
    // TODO:
    //  1 先统计出1的个数count
    //  2 把滑动窗口设为count，遍历所有的窗口位置
    //  3 统计窗口中1出现的最多的次数，或者统计0出现最少的次数
    //  4 返回0出现的最少的次数
    public int minSwaps(int[] data) {
        int count = 0;
        for(int x : data) {
            if(x == 1) count++;
        }

        int res = 0;
        int[] cnt = new int[2];
        int left = 0, right = 0;
        while(right < data.length) {
            cnt[data[right]]++;
            if(right - left + 1 > count) {
                cnt[data[left]]--;
                left++;
            }
            res = Math.max(res, cnt[1]);
            right++;
        }
        return count - res;
    }
}
