package dataStructure.array;

/**
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 *
 * 示例 1：
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 *
 * 示例 2：
 * 输入：nums = [1,2,10,4,1,4,3,3]
 * 输出：[2,10] 或 [10,2]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class QOffer56_NumberCount {
    // TODO：
    //  x ^ x = 0
    //  x ^ 0 = x
    //  假设只有一个数出现了一次，那么数组中所有的元素异或之后的结果即为该数
    //  1 有两个数出现了一次，那么所有的数异或之后为 or = a ^ b
    //  2 找到or的最右边的1的位置
    //  3 将数组分为两部分，该位置为一的，该位置为0的
    //  4 分别对这两组进行异或，得到结果
    public int[] singleNumbers(int[] nums) {
        int or = 0;
        for(int x : nums) or = or ^ x;
        int mask = (or & -or);
        int a = 0, b = 0;
        for(int x : nums) {
            if((mask & x) != 0) a = a ^ x;
            else b = b ^ x;
        }
        return new int[]{a, b};
    }
}
