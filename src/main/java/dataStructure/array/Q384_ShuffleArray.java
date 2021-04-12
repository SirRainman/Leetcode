package dataStructure.array;

import java.util.Random;

/**
 * @program: Leetcode
 * @description:
 * 给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。
 * 实现 Solution class:
 * Solution(int[] nums) 使用整数数组 nums 初始化对象
 * int[] reset() 重设数组到它的初始状态并返回
 * int[] shuffle() 返回数组随机打乱后的结果
 * 
 *
 * 示例：
 * 输入
 * ["Solution", "shuffle", "reset", "shuffle"]
 * [[[1, 2, 3]], [], [], []]
 * 输出
 * [null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
 *
 * 解释
 * Solution solution = new Solution([1, 2, 3]);
 * solution.shuffle();    // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。例如，返回 [3, 1, 2]
 * solution.reset();      // 重设数组到它的初始状态 [1, 2, 3] 。返回 [1, 2, 3]
 * solution.shuffle();    // 随机返回数组 [1, 2, 3] 打乱后的结果。例如，返回 [1, 3, 2]
 * 
 *
 * 提示：
 * 1 <= nums.length <= 200
 * -106 <= nums[i] <= 106
 * nums 中的所有元素都是 唯一的
 * 最多可以调用 5 * 104 次 reset 和 shuffle
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shuffle-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-04-01 15:27
 **/
public class Q384_ShuffleArray {
    private int[] origin, mix;
    private Random random = new Random();

    public Q384_ShuffleArray(int[] nums) {
        int n = nums.length;
        origin = new int[n];
        for(int i = 0; i < n; i++) origin[i] = nums[i];
        mix = new int[n];
        for(int i = 0; i < n; i++) mix[i] = nums[i];
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return origin;
    }

    // TODO:
    //  洗牌算法（Knuth shuffle算法）：对于有n个元素的数组来说，为了保证洗牌的公平性，应该要能够等概率的洗出n!种结果。
    //  开始数组中有五个元素；
    //  在前五个数中随机选一个数与第五个数进行交换，每个数都有五分之一的概率被交换到最后一个位置；
    //  在前四个数中随机选一个数与第四个数进行交换，每个数都有五分之一的概率被交换到第四个位置；
    //  在前三个数中随机选一个数与第三个数进行交换，每个数都有五分之一的概率被交换到第三个位置；
    //  综上所述，每个数都有相等的概率被放到任意一个位置中，即每个位置中出现任意一个数的概率都是相同的。这，就是洗牌算法。
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int n = mix.length;
        for(int i = n - 1; i >= 0; i--) {
            int idx = random.nextInt(i + 1); // 每次从前面的数中选择一个数进行移动到最后一位
            int t = mix[idx];
            mix[idx] = mix[i];
            mix[i] = t;
        }
        return mix;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */