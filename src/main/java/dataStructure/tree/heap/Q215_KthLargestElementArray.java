package dataStructure.tree.heap;

import java.util.Arrays;

/**
 * @program: Leetcode
 * @description:
 * 
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 *
 * 示例2:
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author: Rain
 * @create: 2021-02-14 14:30
 **/
public class Q215_KthLargestElementArray {
    public int findKthLargest(int[] nums, int k) {
        Heap heap = new Heap(k);
        for(int x : nums) heap.add(x);
        return heap.peek();
    }

    class Heap {
        private int size;
        private int count;
        private int[] heap;

        public Heap(int k) {
            this.size = k;
            heap = new int[k];
            Arrays.fill(heap, Integer.MIN_VALUE);
        }

        public int peek() {
            return heap[0];
        }

        public void add(int val) {
            if(count < size) {
                heap[0] = val;
                count++;
                heapify(0);
            } else if(val > heap[0]){
                heap[0] = val;
                heapify(0);
            }
        }

        private void heapify(int i) {
            int parent = i, child = 2 * parent + 1;
            while(child < heap.length) {
                if(child + 1 < heap.length && heap[child + 1] < heap[child]) child++;
                if(heap[parent] > heap[child]) swap(parent, child);
                parent = child;
                child = 2 * child + 1;
            }
        }

        private void swap(int i, int j) {
            int t = heap[i];
            heap[i] = heap[j];
            heap[j] = t;
        }
    }
}
