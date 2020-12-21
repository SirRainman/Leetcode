package algorithm.dp;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 有n个结点，每次合并两个结点需要消耗两个结点值之和的能量，求一种能量消耗最少的方案，使得所有结点合并成一个。
 * 这里与合并石子的区别在于，合并石子只能合并相邻的两堆，而这里允许任意两个合并，并不一定是相邻的。
 *
 * https://www.acwing.com/problem/content/150/
 */
public class A148_HoffmanTree {
    static int n;
    static int[] nums;

    public static int getMinCost() {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for(int x : nums) heap.offer(x);

        int res = 0;

        while(heap.size() > 1) {
            int merge = heap.poll() + heap.poll();
            heap.offer(merge);
            res += merge;
        }

        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        nums = new int[n];
        for(int i = 0; i < n; i++) nums[i] = in.nextInt();
        System.out.print(getMinCost());
    }
}
