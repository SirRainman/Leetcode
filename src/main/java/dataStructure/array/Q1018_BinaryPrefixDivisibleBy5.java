package dataStructure.array;

import java.util.*;

/**
 * 给定由若干0和1组成的数组 A。我们定义N_i：从A[0] 到A[i]的第 i个子数组被解释为一个二进制数（从最高有效位到最低有效位）。
 * 返回布尔值列表answer，只有当N_i可以被 5整除时，答案answer[i] 为true，否则为 false。
 *
 * 示例 1：
 * 输入：[0,1,1]
 * 输出：[true,false,false]
 * 解释：
 * 输入数字为 0, 01, 011；也就是十进制中的 0, 1, 3 。只有第一个数可以被 5 整除，因此 answer[0] 为真。
 *
 * 示例 2：
 * 输入：[1,1,1]
 * 输出：[false,false,false]
 *
 * 示例 3：
 * 输入：[0,1,1,1,1,1]
 * 输出：[true,false,false,false,true,false]
 *
 * 示例4：
 * 输入：[1,1,1,0,1]
 * 输出：[false,false,false,false,false]
 *
 * 提示：
 * 1 <= A.length <= 30000
 * A[i] 为0或1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-prefix-divisible-by-5
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q1018_BinaryPrefixDivisibleBy5 {
    // 1. (a + b) % p = (a % p + b % p) % p
    // 2. (a - b) % p = (a % p - b % p) % p
    // 3. (a * b) % p = (a % p * b % p) % p
    // 4. (a^b) % p = ((a % p)^b) % p
    // 假设我们想求2^n对5的模（即2^n % 5），如果n很大，我们是无法通过计算出2^n，再去取模的。
    // 那么依律3，我们可以先算r = (2^n-1) % 5，再将结果r带入(r * 2) % 5；
    // 那么如何计算r呢，已经发现了吧——递归（递归（递归）...）。
    // 甚至依律3，我们还可以对2^n进行2^(n/2) * 2^(n/2)这样的拆分，以此实现类似快速幂的求模方式。
    // 类似的求模技巧虽然很简单，但是作为某些难题的组成部分，经常是容易被忽视的，
    // 比如律1律3经常用在字符串匹配的rolling-hash算法当中，想计算一个整形的hash值，而小写文字有26种可能取值，
    // 指数幂26^n很容易就会超过值域，即便是长整形也需要不断取模才能使得hash值有意义——当我们忽略碰撞，
    // 模空间内的值就会成为可靠的校验依据。
    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> res = new ArrayList<>();
        int suffix = 0;
        for(int i = 0; i < A.length; i++) {
            suffix = ((suffix << 1) + A[i]) % 5;
            boolean add = res.add(suffix == 0);
            suffix %= 5;
        }
        return res;
    }
}
