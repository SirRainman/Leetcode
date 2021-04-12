package algorithm.math;

/**
 * @program: Leetcode
 * @description:
 * 给定一个整数 n，返回 n! 结果尾数中零的数量。
 *
 * 示例 1:
 * 输入: 3
 * 输出: 0
 * 解释:3! = 6, 尾数中没有零。
 *
 * 示例2:
 * 输入: 5
 * 输出: 1
 * 解释:5! = 120, 尾数中有 1 个零.
 * 说明: 你算法的时间复杂度应为O(logn)。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/factorial-trailing-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-04-01 21:23
 **/
public class HardQ172_FactorialTrailingZeroes {
    // TODO: 只需要找有多少对 2和5
    //  1.含有 2 的因子每两个出现一次，含有 5 的因子每 5 个出现一次
    //  2.每隔 25 个数字，出现的是两个 5，所以除了每隔 5 个数算作一个 5，每隔 25 个数，还需要多算一个 5, 也就是我们需要再加上 n / 25 个 5。
    //  3.同理我们还会发现每隔 5 * 5 * 5 = 125 个数字，会出现 3 个 5，所以我们还需要再加上 n / 125 。
    //  综上，规律就是每隔 5 个数，出现一个 5，每隔 25 个数，出现 2 个 5，每隔 125 个数，出现 3 个 5... 以此类推。
    public int trailingZeroes(int n) {
        int res = 0;
        while(n > 0) {
            n /= 5;
            res += n;
        }
        return res;
    }
}
