package algorithm.double_pointer.slidingWindow;

/**
 * 给定一个数组 nums，有一个大小为k的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 *
 * 进阶：
 * 你能在线性时间复杂度内解决此题吗？
 *
 * 示例:
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * 
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^4<= nums[i]<= 10^4
 * 1 <= k<= nums.length
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ239_SlidingWindowMaximum {
    /**
     * 1 当滑动窗口向右移动时，我们需要把一个新的元素放入队列中。
     *      为了保持队列的性质，我们会不断地将新的元素与队尾的元素相比较，
     *      如果前者大于等于后者，那么队尾的元素就可以被永久地移除，我们将其弹出队列。
     * 2 我们需要不断地进行此项操作，直到队列为空或者新的元素小于队尾的元素。
     * 3 由于队列中下标对应的元素是严格单调递减的，因此此时队首下标对应的元素就是滑动窗口中的最大值。
     *      此时的最大值可能在滑动窗口左边界的左侧，并且随着窗口向右移动，它永远不可能出现在滑动窗口中了。
     *      因此我们还需要不断从队首弹出元素，直到队首元素在窗口中为止。
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0) return new int[0];
        int n = nums.length;
        int[] res = new int[n - k + 1];

        // TODO: 想一想为什么队列里存储的是下标？？？
        //  答：如果不存下标，是没有办法判断什么时候出队的
        int[] queue = new int[n];
        int front = 0, rear = -1;
        for(int i = 0; i < n; i++) {
            // TODO：i - k + 1 是当前窗口的左边界，和队列没有关系，如果存的是数组元素的话，就没有办法判断什么时候出队了
            if(front <= rear && queue[front] < i - k + 1) front++;
            while(front <= rear && nums[queue[rear]] <= nums[i]) rear--;
            queue[++rear] = i;
            if(i >= k - 1) res[i - k + 1] = nums[queue[front]];
        }

        return res;
    }
}
