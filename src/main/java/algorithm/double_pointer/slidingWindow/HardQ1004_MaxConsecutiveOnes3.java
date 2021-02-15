package algorithm.double_pointer.slidingWindow;

/**
 * 给定一个由若干 0 和 1 组成的数组A，我们最多可以将K个值从 0 变成 1 。
 * 返回仅包含 1 的最长（连续）子数组的长度。
 *
 * 示例 1：
 * 输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * 输出：6
 * 解释： 
 * [1,1,1,0,0,1,1,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
 *
 * 示例 2：
 * 输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 * 输出：10
 * 解释：
 * [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 10。
 *
 * 提示：
 * 1 <= A.length <= 20000
 * 0 <= K <= A.length
 * A[i] 为0或1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-consecutive-ones-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ1004_MaxConsecutiveOnes3 {
    // TODO: 看Q424题，想一想为什么滑动窗口为什么可以窗口不用变小？？？
    public int longestOnes(int[] A, int K) {
        int n = A.length;
        int[] count = new int[2];
        int left = 0, right = 0;
        while(right < n) {
            count[A[right]]++;
            if(count[0] > K) {
                count[A[left]]--;
                left++;
            }
            right++;
        }
        return right - left;
    }

    public int longestOnes2(int[] A, int K) {
        int n = A.length;
        int[] count = new int[2];
        int left = 0, right = 0;
        int maxLen = 0;
        while(right < n) {
            count[A[right]]++;
            while(count[0] > K) {
                count[A[left]]--;
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        return maxLen;
    }
}
