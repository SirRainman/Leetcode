package dataStructure.array;

/**
 * @program: Leetcode
 * @description: 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行Z 字形排列。
 * 比如输入字符串为 "PAYPALISHIRING"行数为 3 时，排列如下：
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s 由英文字母（小写和大写）、',' 和 '.' 组成
 * 1 <= numRows <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-03-26 10:04
 **/
public class Q6_ZigZagConversion {
    // TODO: 先上下反转，再对角反转
    public void rotate1(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        for(int up = 0, down = row - 1; up < down; up++, down--) {
            for(int j = 0; j < col; j++) {
                swap(matrix, up, j, down, j);
            }
        }
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < i; j++) {
                swap(matrix, i, j, j, i);
            }
        }
    }

    public void swap(int[][] matrix, int i, int j, int x, int y) {
        int t = matrix[i][j];
        matrix[i][j] = matrix[x][y];
        matrix[x][y] = t;
    }

    // TODO: 找规律
    //  行 0 中的字符位于索引 k(2*numRows−2) 处;
    //  行 numRows−1 中的字符位于索引 k(2*numRows−2) + numRows−1 处;
    //  内部的 行 i 中的字符位于索引 k(2*numRows−2)+i 以及 (k+1)(2*numRows−2)−i 处;
    public String convert(String s, int numRows) {
        if(numRows == 1) return s;
        int n = s.length();
        StringBuffer sb = new StringBuffer();
        int cycleLen = 2 * numRows - 2;
        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j + i < n; j += cycleLen) {
                sb.append(s.charAt(j + i));
                if(i != 0 && i != numRows - 1 && j + cycleLen - i < n) {
                    sb.append(j + cycleLen - i);
                }
            }
        }
        return sb.toString();
    }
}
