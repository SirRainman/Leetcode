package algorithm.AAAcontest.lc;

import java.time.chrono.MinguoDate;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @program: Leetcode
 * @description:
 * 给你一个偶数 n ，已知存在一个长度为 n 的排列 perm ，其中 perm[i] == i（下标 从 0 开始 计数）。
 * 一步操作中，你将创建一个新数组 arr ，对于每个 i ：
 *
 * 如果 i % 2 == 0 ，那么 arr[i] = perm[i / 2]
 * 如果 i % 2 == 1 ，那么 arr[i] = perm[n / 2 + (i - 1) / 2]
 * 然后将 arr 赋值给 perm 。
 *
 * 要想使 perm 回到排列初始值，至少需要执行多少步操作？返回最小的 非零 操作步数。
 * @author: Rain
 * @create: 2021-03-28 11:36
 **/
public class Q5715_NumBackToOrigin {
    // TODO: 倒推给定公式，便能知道每一个位置的元素，经过一次重排列后新的位置。
    //  由于第一个元素（0）和最后一个元素（n - 1）是永远保持不变的，而其余任一元素在回到排列初始值前不会重复出现在同一个位置上。
    //  所以，我们直接看第一个元素每一次的下标 index 变化趋势即可。
    public static int reinitializePermutation(int n) {
        if(n == 2) return 1;
        int idx = 2, res = 1;
        while(idx != 1) {
            if(idx * 2 >= n) idx = 2 * idx + 1 - n;
            else idx = 2 * idx;
            res++;
        }
        return res;
    }
    public static void main(String[] args) {
        System.out.println(reinitializePermutation(10));
    }
}
