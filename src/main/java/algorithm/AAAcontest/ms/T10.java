package algorithm.AAAcontest.ms;

/**
 * @program: Leetcode
 * @description:
 * 给定一组数字和一个k，求出不超过连续k个元素异或的最大值。
 * @author: Rain
 * @create: 2021-03-27 22:46
 **/
public class T10 {
    // TODO：不对
    // 正确答案：https://blog.csdn.net/ACM_10000h/article/details/48864305?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_title-0&spm=1001.2101.3001.4242
    public static int cal(int[] nums, int k) {
        int n = nums.length;
        int res = 0, xor = 0;
        int left = 0, right = 0;
        while(right < n) {
            xor ^= nums[right];
            if(right - left + 1 > k) {
                xor ^= nums[left];
                left++;
            }
            res = Math.max(res, xor);
            right++;
        }
        return res;
    }
}
