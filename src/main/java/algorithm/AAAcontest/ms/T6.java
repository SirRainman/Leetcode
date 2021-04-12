package algorithm.AAAcontest.ms;

/**
 * @program: Leetcode
 * @description:
 * 求数组中的最小的两个数
 * 要求：两个数不是开头，不是结尾，且不相邻
 * @author: Rain
 * @create: 2021-03-27 11:47
 **/
public class T6 {
    static int[] leftMin;
    static int[] rightMin;

    public static int solve(int[] nums, int l, int r) {
        leftMin = new int[nums.length];
        rightMin = new int[nums.length];

        int min = Integer.MAX_VALUE;
        for (int i = l; i <= r; i++) {
            leftMin[i] = min;
            min = Math.min(min, nums[i]);
        }

        min = Integer.MAX_VALUE;
        for (int i = r; i >= l; i--) {
            rightMin[i] = min;
            min = Math.min(min, nums[i]);
        }

        int res = Integer.MAX_VALUE;
        for (int i = l + 1; i < r; i++) {
            res = Math.min(res, leftMin[i] + rightMin[i]);
        }

        return res;
    }

    public static int solution(int[] A) {
        return solve(A, 1, A.length - 2);
    }

    public static void main(String[] args) {
        int[] A = {5, 2, 3, 5, 6};
        System.out.println(solution(A));

    }
}
