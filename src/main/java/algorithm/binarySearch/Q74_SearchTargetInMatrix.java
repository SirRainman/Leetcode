package algorithm.binarySearch;

/**
 * @program: Leetcode
 * @description:
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。
 *
 * 该矩阵具有如下特性：
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author: Rain
 * @create: 2021-02-15 12:58
 **/
public class Q74_SearchTargetInMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length, col = matrix[0].length;
        int up = 0, down = row - 1;
        // TODO: 注意相等时，应该怎么走
        while(up < down) {
            int mid = up + down + 1 >> 1;
            if(target >= matrix[mid][0]) up = mid;
            else down = mid - 1;
        }
        int left = 0, right = col - 1;
        while(left < right) {
            int mid = left + right >> 1;
            if(target <= matrix[up][mid]) right = mid;
            else left = mid + 1;
        }
        return matrix[up][left] == target;
    }
}
