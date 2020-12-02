package algorithm.math.test;

/**
 * 编写一个程序判断给定的数是否为丑数。
 *
 * 丑数就是只包含质因数2, 3, 5的正整数。
 *
 * 示例 1:
 *
 * 输入: 6
 * 输出: true
 * 解释: 6 = 2 ×3
 * 示例 2:
 *
 * 输入: 8
 * 输出: true
 * 解释: 8 = 2 × 2 ×2
 * 示例3:
 *
 * 输入: 14
 * 输出: false 
 * 解释: 14 不是丑数，因为它包含了另外一个质因数7。
 * 说明：
 *
 * 1是丑数。
 * 输入不会超过 32 位有符号整数的范围:[−231, 231− 1]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ugly-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q263_UglyNumber {
    class Solution {
        public boolean isUgly(int num) {
            if(num == 1) return true;
            if(num == 0) return false;
            for(int i = 2; i <= 5; i++) {
                if(num % i == 0) {
                    while(num % i == 0) {
                        num /= i;
                    }
                }
                //System.out.println(num);
            }
            return num == 1;
        }
    }
}
