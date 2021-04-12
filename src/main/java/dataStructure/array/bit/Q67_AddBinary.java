package dataStructure.array.bit;

/**
 * @program: Leetcode
 * @description:
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 * 输入为 非空 字符串且只包含数字1和0。
 *
 * 示例1:
 * 输入: a = "11", b = "1"
 * 输出: "100"
 *
 * 示例2:
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 * 
 * 提示：
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-binary
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-03-23 22:59
 **/
public class Q67_AddBinary {
    public String addBinary1(String a, String b) {
        char[] x = a.toCharArray();
        char[] y = b.toCharArray();
        int carry = 0;
        StringBuffer sb = new StringBuffer();
        for(int i = x.length - 1, j = y.length - 1; i >= 0 || j >= 0 || carry > 0; i--, j--) {
            if(i >= 0) carry += x[i] - '0';
            if(j >= 0) carry += y[j] - '0';
            sb.append(carry % 2);
            carry /= 2;
        }
        return sb.reverse().toString();
    }

    // TODO:
    //  异或 是不进位的加法
    //  且   是保存了加法的进位情况
    public String addBinary(String a, String b) {
        int A = Integer.parseInt(a, 2), B = Integer.parseInt(b, 2);
        while(B > 0) {
            int answer = A ^ B;
            int carry = (A & B) << 1;
            A = answer;
            B = carry;
        }
        return Integer.toBinaryString(A);
    }
}
