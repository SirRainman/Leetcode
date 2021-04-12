package algorithm.AAAcontest.ms;

/**
 * @program: Leetcode
 * @description:
 * 求连续子数组中的和（每个元素都要求大于等于0）
 * @author: Rain
 * @create: 2021-03-27 10:39
 **/
public class T4 {
    public static int solution(int[] nums) {
        if(nums == null || nums.length == 0) return -1;
        int res = -1, sum = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] >= 0) {
                sum += nums[i];
                res = Math.max(res, sum);
            } else {
                sum = 0;
            }
        }
        return res < 0 ? -1 : res;
    }

    public static void main(String[] args) {
        // int[] nums = {-1, 2, 2, 1, 0, 2, 1, -3, 4, 3, 0, -1};
        int[] nums = {-1, -8, -9};
        System.out.println(solution(nums));
    }
}
