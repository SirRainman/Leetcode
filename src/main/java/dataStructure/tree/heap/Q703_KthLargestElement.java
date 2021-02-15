package dataStructure.tree.heap;

import java.util.PriorityQueue;

/**
 * @program: Leetcode
 * @description:
 * 
 * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
 * 请实现 KthLargest类：
 * KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
 * int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
 * 
 * 示例：
 * 输入：
 * ["KthLargest", "add", "add", "add", "add", "add"]
 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 * 输出：
 * [null, 4, 5, 5, 8, 8]
 *
 * 解释：
 * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 * kthLargest.add(3);   // return 4
 * kthLargest.add(5);   // return 5
 * kthLargest.add(10);  // return 5
 * kthLargest.add(9);   // return 8
 * kthLargest.add(4);   // return 8
 * 
 *
 * 提示：
 * 1 <= k <= 104
 * 0 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * -104 <= val <= 104
 * 最多调用 add 方法 104 次
 * 题目数据保证，在查找第 k 大元素时，数组中至少有 k 个元素
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-a-stream
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author: Rain
 * @create: 2021-02-14 12:48
 **/
public class Q703_KthLargestElement {
    class KthLargest1 {
        private PriorityQueue<Integer> heap;
        private int k;

        public KthLargest1(int k, int[] nums) {
            this.k = k;
            heap = new PriorityQueue<Integer>(k, (a, b) -> Integer.compare(a, b));
            for(int x : nums) add(x);
        }

        public int add(int val) {
            if(heap.size() < k) {
                heap.offer(val);
            } else if(heap.peek() < val) {
                heap.poll();
                heap.offer(val);
            }
            return heap.peek();
        }
    }

    class KthLargest {
        Heap heap;

        public KthLargest(int k, int[] nums) {
            heap = new Heap(nums, k);
        }

        public int add(int val) {
            return heap.add(val);
        }
    }

    /**
     * Your KthLargest object will be instantiated and called as such:
     * KthLargest obj = new KthLargest(k, nums);
     * int param_1 = obj.add(val);
     */

    class Heap {
        private int[] heap;
        private int size; // heap 最小堆的大小，维护的数组中第k大的数
        private int count; // heap中的元素个数

        public Heap(int[] nums, int k) {
            this.size = k;
            heap = new int[k];

            int len = Math.min(nums.length, k);
            for(int i = 0; i < len; i++) {
                heap[i] = nums[i];
                count++;
            }
            // 数据保证add的时候 至少有k个 意味着当nums长度小于k的时候 k比nums.length 大1
            if(nums.length < k) heap[k - 1] = Integer.MIN_VALUE;
            build();
            for(int i = len; i < nums.length; i++) {
                if(nums[i] > heap[0]) {
                    replaceWith(nums[i]);
                }
            }
        }

        private void build() {
            int n = heap.length;
            for(int i = (n >> 1) - 1; i >= 0; i--) {
                heapify(i);
            }
        }

        private void replaceWith(int val) {
            heap[0] = val;
            heapify(0);
        }

        private void heapify(int i) {
            int parent = i, child = 2 * parent + 1;
            while(child < heap.length) {
                if(child + 1 < heap.length && heap[child] > heap[child + 1]) child++;
                if(heap[child] < heap[parent]) {
                    int t = heap[child];
                    heap[child] = heap[parent];
                    heap[parent] = t;
                }
                parent = child;
                child = child * 2 + 1;
            }
        }

        public int add(int val) {
            if(count < size) {
                // heap[0] = val;
                // heapify(0);
                replaceWith(val);
                count++;
            } else if(val > heap[0]) {
                replaceWith(val);
            }
            return heap[0];
        }
    }
}
