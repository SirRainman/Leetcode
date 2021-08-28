package dataStructure.array.bit;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: Leetcode
 * @description:
 * 不使用运算符+ 和-，计算两整数a、b之和。
 *
 * 示例 1:
 * 输入: a = 1, b = 2
 * 输出: 3
 * 
 * 示例 2:
 * 输入: a = -2, b = 3
 * 输出: 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-two-integers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-04-02 09:50
 **/
public class Q371_SumOfTwoIntegers {
    public int getSum(int a, int b) {
        int carry = a & b, xor = a ^ b;
        while(carry != 0) {
            int t_xor = (carry << 1) ^ xor;
            int t_carry = (carry << 1) & xor;
            xor = t_xor;
            carry = t_carry;
        }
        return xor;
    }
}
