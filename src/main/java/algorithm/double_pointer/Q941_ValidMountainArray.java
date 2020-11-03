package algorithm.double_pointer;

/**
 * 给定一个整数数组A，如果它是有效的山脉数组就返回true，否则返回 false。
 *
 * 让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
 *
 * A.length >= 3
 * 在0 < i< A.length - 1条件下，存在i使得：
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[A.length - 1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-mountain-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q941_ValidMountainArray {
    class Solution {
        public boolean validMountainArray1(int[] A) {
            if(A.length < 3) return false;
            int cur = 0;
            while(cur + 1 < A.length && A[cur] < A[cur + 1]) cur++;
            if(cur == 0 || cur == A.length - 1) {
                return false;
            }
            while(cur + 1 < A.length && A[cur] > A[cur + 1]) cur++;
            return cur == A.length - 1;
        }

        public boolean validMountainArray(int[] A) {
            int left = 0, right = A.length - 1;
            while(left + 1 < A.length && A[left] < A[left + 1]) left++;
            while(right - 1 >= 0 && A[right] < A[right - 1]) right--;
            return left > 0 && right < A.length - 1 && left == right;
        }
    }

}
