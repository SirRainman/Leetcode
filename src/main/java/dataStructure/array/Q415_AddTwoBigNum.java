package dataStructure.array;

/**
 * 给定两个字符串形式的非负整数num1 和num2，计算它们的和。
 *
 * 
 *
 * 提示：
 *
 * num1 和num2的长度都小于 5100
 * num1 和num2 都只包含数字0-9
 * num1 和num2 都不包含任何前导零
 * 你不能使用任何內建 BigInteger 库，也不能直接将输入的字符串转换为整数形式
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q415_AddTwoBigNum {
    class Solution {
        // TODO: 高精度
        //  1.逆序
        //  2.带位
        public String addStrings(String num1, String num2) {
            char[] A = num1.toCharArray();
            char[] B = num2.toCharArray();

            StringBuffer sb = new StringBuffer();
            int i = A.length - 1, j = B.length - 1;
            int carry = 0;
            while(i >= 0 || j >= 0 || carry > 0) {
                int a = i >= 0 ? A[i--] - '0' : 0;
                int b = j >= 0 ? B[j--] - '0' : 0;
                sb.append((a + b + carry) % 10);
                carry = (a + b + carry) / 10;
            }
            return sb.reverse().toString();
        }

    }
}
