package dataStructure.array.bit;

/**
 * @program: Leetcode
 * @description:
 * 给你三个正整数a、b 和 c。
 * 你可以对 a 和 b的二进制表示进行位翻转操作，返回能够使按位或运算 a OR b == c成立的最小翻转次数。
 * 「位翻转操作」是指将一个数的二进制表示任何单个位上的 1 变成 0 或者 0 变成 1 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-flips-to-make-a-or-b-equal-to-c
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-03-15 19:27
 **/
public class Q1318_MinimumFlips_AorBEqualC {
    public int minFlips(int a, int b, int c) {
        int res = 0;
        for(int i = 0; i < 32; i++) {
            int bitA = a >> i & 1;
            int bitB = b >> i & 1;
            int bitC = c >> i & 1;
            if(bitC == 0) {
                res += bitA + bitB;
            } else {
                res += (bitA + bitB) == 0 ? 1 : 0;
            }
        }
        return res;
    }
}
