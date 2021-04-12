package dataStructure.stack.monotonousStack;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @program: Leetcode
 * @description:
 *
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * @author: Rain
 * @create: 2021-02-15 16:56
 **/
public class HardQ84_LargestRectangleInHistogram {

    // TODO: 求左右两侧最近的高度小于 h 的柱子
    public int largestRectangleArea1(int[] heights) {
        int n = heights.length;
        int[] left = new int[n]; // 左边边界
        int[] right = new int[n]; // 右边边界

        Deque<Integer> stack = new LinkedList<>();
        for(int i = 0; i < heights.length; i++) {
            while(!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                stack.poll();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        stack.clear();
        for(int i = n - 1; i >= 0; i--) {
            while(!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                stack.poll();
            }
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        int largest = 0;
        for(int i = 0; i < n; i++) {
            largest = Math.max(largest, heights[i] * (right[i] - left[i] - 1));
        }
        return largest;
    }

    // TODO：优化 - 力的作用是相互的
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);

        Deque<Integer> stack = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            while(!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                right[stack.peek()] = i;
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        int largest = 0;
        for (int i = 0; i < n; ++i) {
            largest = Math.max(largest, (right[i] - left[i] - 1) * heights[i]);
        }
        return largest;
    }
}
