package dataStructure.array.bit;

/**
 * @program: Leetcode
 * @description:
 * 给定一个包含 [0, n]中n个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
 *
 * 进阶：
 * 你能否实现线性时间复杂度、仅使用额外常数空间的算法解决此问题?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/missing-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-04-01 22:28
 **/
public class Q268_MissingNumber {
    // TODO: 除去丢失的那一个数，其余的数都出现了两次
    public int missingNumber(int[] nums) {
        int n = nums.length, res = n;
        for(int i = 0; i < n; i++) {
            // res ^= i;
            // res ^= nums[i];
            res ^= i ^ nums[i];
        }
        return res;
    }
}
