package algorithm.AAAcontest.lc;

import dataStructure.TreeNode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: Leetcode
 * @description:
 * @author: Rain
 * @create: 2021-03-29 13:29
 **/
public class Test {
    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        int sum = Integer.MAX_VALUE;
        for(int i = 0; i < 0; i++) {
            if(nums[i] > target) break;
            int left = 0, right = n - 1;
            while(left < right) {
                int t = nums[i] + nums[left] + nums[right];
                if(t == target) return target;
                else if(t < target) left++;
                else right--;
                if(Math.abs(t - target) < Math.abs(sum - target)) sum = t;
            }
        }
        return sum;
    }
}
