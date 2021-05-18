package dataStructure.array;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 *
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 * 示例 2：
 * 输入：matrix =[[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 * 
 *
 * 限制：
 * 0 <= matrix.length <= 100
 * 0 <= matrix[i].length<= 100
 * 注意：本题与主站 54 题相同：https://leetcode-cn.com/problems/spiral-matrix/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q54_SpiralMatrix {
    public int[] spiralOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0) return new int[]{};
        int row = matrix.length, col = matrix[0].length;
        int left = 0, right = col - 1, up = 0, down = row - 1;
        int size = row * col;
        int[] res = new int[size];
        int idx = 0;

        while(idx < size) {
            for(int j = left; j <= right; j++) {
                res[idx++] = matrix[up][j];
                if(idx == size) return res;
            }
            up++;
            for(int i = up; i <= down; i++) {
                res[idx++] = matrix[i][right];
                if(idx == size) return res;
            }
            right--;
            for(int j = right; j >= left; j--) {
                res[idx++] = matrix[down][j];
                if(idx == size) return res;
            }
            down--;
            for(int i = down; i >= up; i--) {
                res[idx++] = matrix[i][left];
                if(idx == size) return res;
            }
            left++;
        }
        return res;
    }
}
