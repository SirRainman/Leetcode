package algorithm.double_pointer;

/**
 * 我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
 * B.length >= 3, 存在 0 < i< B.length - 1
 *      使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
 * （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
 * 给出一个整数数组 A，返回最长 “山脉”的长度。
 * 如果不含有 “山脉”则返回 0。
 * 
 * 示例 1：
 * 输入：[2,1,4,7,3,2,5]
 * 输出：5
 * 解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。
 *
 * 示例 2：
 * 输入：[2,2,2]
 * 输出：0
 * 解释：不含 “山脉”。
 *
 * 提示：
 * 0 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-mountain-in-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ845_LongestMountainArray {
    // TODO:想一想为什么没有下面的好？？？
    public int longestMountain(int[] A) {
        int longest = 0;
        int i = 0;
        while (i < A.length) {
            int left = i, right = i, len = 0;
            while (left - 1 >= 0 && A[left - 1] < A[left]) left--;
            while (right + 1 < A.length && A[right + 1] < A[right]) right++;
            if (left < i && right > i) {
                len = right - left + 1;
                i = right;
            } else {
                i++;
            }
            longest = Math.max(longest, len);

        }
        return longest;
    }

    public int longestMountain1(int[] A) {
        int ans = 0;
        int left = 0, right = 0;
        while (left + 2 < A.length) {
            right = left + 1;

            // 开始丈量每一座山峰
            if (A[left] < A[right]) {
                // 丈量左半边山峰
                while (right + 1 < A.length && A[right] < A[right + 1]) {
                    right++;
                }
                if (right + 1 < A.length && A[right] > A[right + 1]) {
                    while (right + 1 < A.length && A[right] > A[right + 1]) {
                        right++;
                    }
                    ans = Math.max(ans, right - left + 1);
                } else {
                    right++;
                }
            }
            left = right;
        }
        return ans;
    }

    public int longestMountain2(int[] A) {
        if (A.length < 3) return 0;

        int[] left = new int[A.length];
        int[] right = new int[A.length];

        for (int i = 1; i < A.length; i++) {
            // 如果比左边的高，记录左半边山峰的高度
            left[i] = (A[i] > A[i - 1] ? left[i - 1] + 1 : 0);
        }
        for (int i = A.length - 2; i >= 0; i--) {
            // 记录右半边山峰的高度
            right[i] = (A[i] > A[i + 1] ? right[i + 1] + 1 : 0);
        }

        int ans = 0;
        for (int i = 0; i < A.length; i++) {
            //System.out.println(left[i] + " " +right[i]);
            if (left[i] > 0 && right[i] > 0) {
                ans = Math.max(ans, left[i] + right[i] + 1);
            }
        }

        return ans;
    }
}
