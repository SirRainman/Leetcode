package dataStructure.array;

/**
 * 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
 * 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
 *
 * 示例 1：
 * 输入：n = 12
 * 输出：5
 *
 * 示例 2：
 * 输入：n = 13
 * 输出：6
 *
 * 限制：
 * 1 <= n <2^31
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q233_NumberOfDigitOne {
    // 1 当 cur = 0 时（2308 -> 0010 ~ 2219 = 000 ~ 229）：此位 1 的出现次数 = high * digit
    // 2 当 cur = 1 时（2318 -> 0010 ~ 2318 = 000 ~ 238）：此位 1 的出现次数 = high * digit + low + 1
    // 3 当 cur > 1 时（2368 -> 0010 ~ 2319 = 000 ~ 239）：此位 1 的出现次数 = (high + 1) * digit
    public int countDigitOne(int n) {
        int digit = 1, res = 0;
        int high = n / 10, cur = n % 10, low = 0;
        while(high != 0 || cur != 0) {
            if(cur == 0) res += high * digit;
            else if(cur == 1) res += high * digit + low + 1;
            else res += (high + 1) * digit;
            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }
        return res;
    }
}
