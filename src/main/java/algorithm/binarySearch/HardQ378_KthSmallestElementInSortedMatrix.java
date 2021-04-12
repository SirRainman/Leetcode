package algorithm.binarySearch;

/**
 * @program: Leetcode
 * @description:
 * 给你一个n x n矩阵matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 * 请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。
 *
 * 示例 1：
 * 输入：matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
 * 输出：13
 * 解释：矩阵中的元素为 [1,5,9,10,11,12,13,13,15]，第 8 小元素是 13
 *
 * 示例 2：
 * 输入：matrix = [[-5]], k = 1
 * 输出：-5
 *
 * 提示：
 * n == matrix.length
 * n == matrix[i].length
 * 1 <= n <= 300
 * -109 <= matrix[i][j] <= 109
 * 题目数据 保证 matrix 中的所有行和列都按 非递减顺序 排列
 * 1 <= k <= n2
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-03-31 12:00
 **/
public class HardQ378_KthSmallestElementInSortedMatrix {
    // TODO：二分判断矩阵中的范围
    //  第k小的元素一定是在[min, max]中间的
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0], right = matrix[n - 1][n - 1]; // 最小值 和 最大值
        while(left < right) {
            int mid = left + right >> 1;
            if(check(matrix, mid, k)) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    // TODO：检查matrix中小于等于mid的元素个数是否大于k？
    //  有序矩阵可以从对角线来寻找元素
    private boolean check(int[][] matrix, int mid, int k) {
        int n = matrix.length, count = 0;
        int i = 0, j = n - 1; // 从右上角开始判断
        while(i < n && j >= 0) {
            if(matrix[i][j] <= mid) {
                count += j + 1;
                i++;
            } else {
                j--;
            }
        }
        return count >= k;
    }
}
