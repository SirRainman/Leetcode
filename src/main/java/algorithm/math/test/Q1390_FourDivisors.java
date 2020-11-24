package algorithm.math.test;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums，请你返回该数组中恰有四个因数的这些整数的各因数之和。
 *
 * 如果数组中不存在满足题意的整数，则返回 0 。
 *
 * 
 *
 * 示例：
 *
 * 输入：nums = [21,4,7]
 * 输出：32
 * 解释：
 * 21 有 4 个因数：1, 3, 7, 21
 * 4 有 3 个因数：1, 2, 4
 * 7 有 2 个因数：1, 7
 * 答案仅为 21 的所有因数的和。
 * 
 *
 * 提示：
 *
 * 1 <= nums.length <= 10^4
 * 1 <= nums[i] <= 10^5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/four-divisors
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q1390_FourDivisors {
    class Solution {
        public int getDivisorSum(int n) {
            int sum = 0, count = 0;
            for(int i = 1; i <= n / i; i++) {
                if(n % i == 0) {
                    sum += i;
                    count++;
                    if(i * i != n) {
                        sum += n / i;
                        count++;
                    }
                }
            }
            return count == 4 ? sum : 0;
        }

        public int sumFourDivisors1(int[] nums) {
            int sum = 0;
            for(int n : nums) {
                sum += getDivisorSum(n);
            }
            return sum;
        }

        public int sumFourDivisors(int[] nums) {
            int N = 100000, C3 = 46; // N 是数组 nums 元素的上限，C3 是 C 的立方根
            int[] primes = new int[N + 1];
            int count = 0;
            boolean[] st = new boolean[N + 1];

            // 埃氏筛法得到素数
            for(int i = 2; i <= N; i++) {
                if(!st[i]) {
                    primes[count++] = i;
                    for(int j = i + i; j <= N; j += i) st[j] = true;
                }
            }


            // 四因数除掉1和本身，另外两个因子要么是 p, p 要么是 p1 p2
            Map<Integer, Integer> factor4 = new HashMap<>();
            for(int p : primes) {
                if(p <= C3) {
                    int t = p * p * p;
                    factor4.put(t, 1 + p + p * p + t);
                }
            }
            for(int i = 0; i < count; i++) {
                for(int j = i + 1; j < count; j++) {
                    if(primes[i] <= N / primes[j]) {
                        int t = primes[i] * primes[j];
                        factor4.put(t, 1 + primes[i] + primes[j] + t);
                    } else {
                        break;
                    }
                }
            }


            int sum = 0;
            for(int n : nums) {
                if(factor4.containsKey(n)) {
                    sum += factor4.get(n);
                }
            }
            return sum;
        }
    }

}
