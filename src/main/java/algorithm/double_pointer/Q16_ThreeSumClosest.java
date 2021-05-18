package algorithm.double_pointer;

import java.util.Arrays;

/**
 * @program: Leetcode
 * @description:
 * 在数组中到找最接近target的三数之和
 * @author: Rain
 * @create: 2021-03-29 15:50
 **/
public class Q16_ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        int sum = 0x3f3f3f3f;
        for(int i = 0; i < n; i++) {
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1, right = n - 1;
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
