package algorithm.double_pointer;

/**
 * 给定一个整数数组A，如果它是有效的山脉数组就返回true，否则返回 false。
 *
 * n >= 3
 * 在0 < i< n - 1条件下，存在i使得：
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[n - 1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-mountain-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q941_ValidMountainArray {
    public boolean validMountainArray1(int[] A) {
        int n = A.length, cur = 0;
        while(cur + 1 < n && A[cur] < A[cur + 1]) cur++;
        // TODO:判断一下是否走到了最后或者是否原地踏步
        if(cur == 0 || cur == n - 1) return false;
        while(cur + 1 < n && A[cur] > A[cur + 1]) cur++;
        // 判断是否走到了最后
        return cur == n - 1;
    }

    public boolean validMountainArray(int[] A) {
        int n = A.length - 1;
        int left = 0, right = n - 1;
        while(left + 1 < n && A[left] < A[left + 1]) left++;
        while(right - 1 >= 0 && A[right] < A[right - 1]) right--;
        return left > 0 && right < n - 1 && left == right;
    }
}
