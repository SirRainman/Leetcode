package algorithm.double_pointer.slidingWindow;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 中位数是有序序列最中间的那个数。如果序列的大小是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。
 *
 * 例如：
 * [2,3,4]，中位数是3
 * [2,3]，中位数是 (2 + 3) / 2 = 2.5
 * 给你一个数组 nums，有一个大小为 k 的窗口从最左端滑动到最右端。
 * 窗口中有 k 个数，每次窗口向右移动 1 位。
 * 你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。
 *
 * 示例：
 * 给出nums = [1,3,-1,-3,5,3,6,7]，以及k = 3。
 * 窗口位置                      中位数
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 *  1 [3  -1  -3] 5  3  6  7      -1
 *  1  3 [-1  -3  5] 3  6  7      -1
 *  1  3  -1 [-3  5  3] 6  7       3
 *  1  3  -1  -3 [5  3  6] 7       5
 *  1  3  -1  -3  5 [3  6  7]      6
 * 因此，返回该滑动窗口的中位数数组[1,-1,-1,3,5,6]。
 *
 * 提示：
 * 你可以假设k始终有效，即：k 始终小于输入的非空数组的元素个数。
 * 与真实值误差在 10 ^ -5 以内的答案将被视作正确答案。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-median
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ480_SlidingWindowMedian {
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] res = new double[nums.length - k + 1];
        DualHeap dualHeap = new DualHeap(k);
        for(int i = 0; i < k; i++) {
            dualHeap.insert(nums[i]);
        }
        res[0] = dualHeap.getMedian();
        for(int i = k; i < nums.length; i++) {
            dualHeap.insert(nums[i]);
            dualHeap.erase(nums[i - k]);
            res[i - k + 1] = dualHeap.getMedian();
        }
        return res;
    }

    class DualHeap {
        PriorityQueue<Integer> small, large;
        int smallSize, largeSize;
        int k;
        Map<Integer, Integer> delayed;

        public DualHeap(int k) {
            this.k = k;
            small = new PriorityQueue<>(Comparator.reverseOrder());
            large = new PriorityQueue<>(Comparator.naturalOrder());
            smallSize = 0;
            largeSize = 0;
            delayed = new HashMap<>();
        }

        public void insert(int num) {
            if(small.isEmpty()|| num <= small.peek()) {
                small.offer(num);
                smallSize++;
            } else {
                large.offer(num);
                largeSize++;
            }
            makeBalance();
        }

        public double getMedian() {
            return (k & 1) == 1 ? small.peek() : ((double) small.peek() + large.peek()) / 2;
        }

        public void erase(int num) {
            delayed.put(num, delayed.getOrDefault(num, 0) + 1);
            if(num <= small.peek()) {
                smallSize--;
                if(small.peek() == num) prune(small);
            } else {
                largeSize--;
                if(large.peek() == num) prune(large);
            }
            makeBalance();
        }

        private void prune(PriorityQueue<Integer> heap) {
            while(!heap.isEmpty()) {
                int num = heap.peek();
                if(delayed.containsKey(num)) {
                    delayed.put(num, delayed.get(num) - 1);
                    if(delayed.get(num) == 0) delayed.remove(num);
                    heap.poll();
                } else {
                    break;
                }
            }
        }

        private void makeBalance() {
            if(smallSize > largeSize + 1) {
                large.offer(small.poll());
                smallSize--;
                largeSize++;
                prune(small);
            } else if(smallSize < largeSize) {
                small.offer(large.poll());
                largeSize--;
                smallSize++;
                prune(large);
            }
        }
    }
}
