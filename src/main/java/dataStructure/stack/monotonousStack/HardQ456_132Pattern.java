package dataStructure.stack.monotonousStack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @program: Leetcode
 * 给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。
 * 如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。
 *
 * 进阶：很容易想到时间复杂度为 O(n^2) 的解决方案，你可以设计一个时间复杂度为 O(n logn) 或 O(n) 的解决方案吗？
 *
 * 示例 1：
 * 输入：nums = [1,2,3,4]
 * 输出：false
 * 解释：序列中不存在 132 模式的子序列。
 *
 * 示例 2：
 * 输入：nums = [3,1,4,2]
 * 输出：true
 * 解释：序列中有 1 个 132 模式的子序列： [1, 4, 2] 。
 *
 * 示例 3：
 * 输入：nums = [-1,3,2,0]
 * 输出：true
 * 解释：序列中有 3 个 132 模式的的子序列：[-1, 3, 2]、[-1, 3, 0] 和 [-1, 2, 0] 。
 * 
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 104
 * -109 <= nums[i] <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/132-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-04-01 12:25
 **/
public class HardQ456_132Pattern {
    // TODO：维护nums[i]，使得numsi 是 [0, i] 中最小得
    public boolean find132pattern1(int[] nums) {
        int n = nums.length;
        int numsi = nums[0]; // j 左边最小的值
        for(int j = 1; j < n - 1; j++) {
            for(int k = j + 1; k < n; k++) {
                if(numsi < nums[k] && nums[j] > nums[k]) return true;
            }
            numsi = Math.min(numsi, nums[j]);
        }
        return false;
    }

    // TODO: 维护nums[k] 是小于nums[j]得最大的
    //  1. 从后往前做，维护一个「单调递减」的栈，同时使用 k 记录所有出栈元素的最大值（k 代表满足 132 结构中的 2）。
    //  2. 当我们遍历到 i，只要满足发现满足 nums[i] < k，说明我们找到了符合条件的 i j k。
    //      [3, 1, 4, 2]
    //      枚举到 2：栈内元素为 [2]，k = INF
    //      枚举到 4：不满足「单调递减」，2 出栈更新 k，4 入栈。栈内元素为 [4]，k = 2
    //      枚举到 1：满足 nums[i] < k，说明对于 i 而言，后面有一个比其大的元素（满足 i < k 的条件），同时这个 k 的来源又是因为维护「单调递减」而弹出导致被更新的（满足 i 和 k 之间，有比 k 要大的元素）。因此我们找到了满足 132 结构的组合。
    public boolean find132pattern2(int[] nums) {
        int n = nums.length;
        int numsk = Integer.MIN_VALUE;
        Deque<Integer> stack = new LinkedList<>();
        // TODO:想一想为什么是从后往前遍历
        //  因为通过单调栈可以确定 i j 之间的大小关系
        //  出来一个元素就确定了一个numsk
        for(int i = n - 1; i >= 0; i--) {
            // TODO：栈顶元素是numsj
            while(!stack.isEmpty() && nums[i] > stack.peek()) numsk = Math.max(numsk, stack.pop());
            if(nums[i] < numsk) return true;
            stack.push(nums[i]);
        }
        return false;
    }
}
