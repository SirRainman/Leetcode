package dataStructure.tree.heap;

import java.util.PriorityQueue;

/**
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 * 例如，
 * [2,3,4]的中位数是 3
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * 设计一个支持以下两种操作的数据结构：
 *  void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 *  double findMedian() - 返回目前所有元素的中位数。
 * 示例：
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3) 
 * findMedian() -> 2
 *
 * 进阶:
 * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-median-from-data-stream
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q295_FindMedianFromDataStream {
    // 我们可以使用两个优先队列（堆）维护所有的元素
    //      第一个优先队列 small 是一个大根堆，它负责维护所有元素中较小的那一半；
    //      第二个优先队列 large 是一个小根堆，它负责维护所有元素中较大的那一半。
    //      具体地，如果当前需要维护的元素个数为 x，那么 small 中维护了 x/2 个元素，large 中维护了 x/2个元素
    // 这样设计的好处在于：
    //      当二者包含的元素个数相同时，它们各自的堆顶元素的平均值即为中位数；
    //      而当 small 包含的元素多了一个时，small 的堆顶元素即为中位数。
    //      这样 getMedian() 就设计完成了。
    class MedianFinder {
        PriorityQueue<Integer> smallHeap, bigHeap;
        /** initialize your data structure here. */
        public MedianFinder() {
            bigHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
            smallHeap = new PriorityQueue<>((o1, o2) -> o1 - o2);
        }

        public void addNum(int num) {
            bigHeap.offer(num);
            smallHeap.offer(bigHeap.poll());
            if(bigHeap.size() + 1 < smallHeap.size()) bigHeap.offer(smallHeap.poll());
        }

        public double findMedian() {
            if(bigHeap.size() < smallHeap.size()) return smallHeap.peek();
            else return smallHeap.peek() + bigHeap.peek() / 2.0;
        }
    }
}
