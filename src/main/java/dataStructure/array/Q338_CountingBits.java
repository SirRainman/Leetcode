package dataStructure.array;

/**
 * 给定一个非负整数num。对于0 ≤ i ≤ num 范围中的每个数字i，计算其二进制数中的 1 的数目并将它们作为数组返回。
 * 示例 1:
 * 输入: 2
 * 输出: [0,1,1]
 *
 * 示例2:
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 *
 * 进阶:
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的__builtin_popcount）来执行此操作。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/counting-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q338_CountingBits {
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        for(int i = 1; i <= num; i++) {
            // 方法一 i & (i - 1)去掉最右边一位1后的大小
            // res[i] = res[i & (i - 1)] + 1;

            // 方法二 (i & -i) 最右边一位1所在的位置
            // i - (i & -i) 去掉最右边一位1后的大小
            res[i] = res[i - (i & -i)] + 1;

            // 方法三 i >> 1 右移一位后，判断最后一位是否为1
            // res[i] = res[i >> 1] + (i & 1);
        }
        return res;
    }
}
