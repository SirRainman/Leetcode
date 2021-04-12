package dataStructure.array.bit;

/**
 * @program: Leetcode
 * @description:
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 *
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 * 输入: [2,2,3,2]
 * 输出: 3
 *
 * 示例2:
 * 输入: [0,1,0,1,0,1,99]
 * 输出: 99
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-number-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-03-11 14:08
 **/
public class Q137_SingleNumber2 {
    // TODO: 开始 a = 0, b = 0;
    //  https://leetcode-cn.com/problems/single-number-ii/solution/luo-ji-dian-lu-jiao-du-xiang-xi-fen-xi-gai-ti-si-l/
    //  0 ^ x = x,
    //  x ^ x = 0；
    //  x & ~x = 0,
    //  x & ~0 =x;
    //  x第一次出现后，
    //      a = (a ^ x) & ~b, a = x,
    //      b = (b ^ x) & ~a, b = 0。
    //  x第二次出现：
    //      a = (a ^ x) & ~b, a = (x ^ x) & ~0, a = 0;
    //      b = (b ^ x) & ~a, b = (0 ^ x) & ~0, b = x;
    //  x第三次出现：
    //      a = (a ^ x) & ~b,  a = (0 ^ x) & ~x, a = 0;
    //      b = (b ^ x) & ~a,  b = (x ^ x) & ~0, b = 0;
    //  所以出现三次同一个数，a和b最终都变回了0.
    //  只出现一次的数，按照上面x第一次出现的规律可知a = x, b = 0;
    //  因此最后返回a.
    public int singleNumber(int[] nums) {
        int onece = 0, twice = 0;
        for(int x : nums) {
            onece = (onece ^ x) & ~twice;
            twice = (twice ^ x) & ~onece;
        }
        return onece;
    }
}
