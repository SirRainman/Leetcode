package algorithm.sort;

import java.util.Arrays;

/**
 * @program: Leetcode
 * @description:
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 *
 * 示例 1：
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 *
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-03-28 17:06
 **/
public class Q179_LargestNumber {
    // TODO: 注意不能直接相减，注意数组元素的范围
    public String largestNumber(int[] nums) {
        int n = nums.length;
        String[] strNums = new String[n];
        for(int i = 0; i < n; i++) {
            strNums[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strNums, (a, b) -> (b + a).compareTo(a + b) );
        StringBuilder sb = new StringBuilder();
        for(String s : strNums) sb.append(s);
        while(sb.length() > 1 && sb.charAt(0) == '0') sb.deleteCharAt(0);
        return sb.toString();
    }
}
