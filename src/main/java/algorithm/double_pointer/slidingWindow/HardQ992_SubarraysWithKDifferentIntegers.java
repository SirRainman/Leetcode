package algorithm.double_pointer.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个正整数数组 A，如果 A的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定独立的子数组为好子数组。
 * （例如，[1,2,3,1,2] 中有3个不同的整数：1，2，以及3。）
 * 返回A中好子数组的数目。
 *
 * 示例 1：
 * 输入：A = [1,2,1,2,3], K = 2
 * 输出：7
 * 解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
 *
 * 示例 2：
 * 输入：A = [1,2,1,3,4], K = 3
 * 输出：3
 * 解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
 *
 * 提示：
 * 1 <= A.length <= 20000
 * 1 <= A[i] <= A.length
 * 1 <= K <= A.length
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarrays-with-k-different-integers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ992_SubarraysWithKDifferentIntegers {
    // TODO: 恰好由 K 个不同整数的子数组的个数 = 最多由 K 个不同整数的子数组的个数 - 最多由 K - 1 个不同整数的子数组的个数
    public int subarraysWithKDistinct(int[] A, int K) {
        return atMostKDistinct(A, K) - atMostKDistinct(A, K - 1);
    }

    public int atMostKDistinct1(int[] A, int K) {
        int n = A.length;
        int right = 0, left = 0, res = 0;
        Map<Integer, Integer> countMap = new HashMap<>();
        while(right < n) {
            countMap.put(A[right], countMap.getOrDefault(A[right], 0) + 1);
            while(countMap.size() > K) {
                countMap.put(A[left], countMap.get(A[left]) - 1);
                if(countMap.get(A[left]) == 0) countMap.remove(A[left]);
                left++;
            }
            res += right - left + 1;
            right++;
        }
        return res;
    }

    public int atMostKDistinct(int[] A, int K) {
        int n = A.length;
        int right = 0, left = 0, res = 0;
        int[] countMap = new int[20010];
        int distinct = 0;
        while(right < n) {
            if(countMap[A[right]]++ == 0) distinct++;
            while(distinct > K) {
                countMap[A[left]]--;
                if(countMap[A[left]] == 0) distinct--;
                left++;
            }
            res += right - left + 1;
            right++;
        }
        return res;
    }
}
