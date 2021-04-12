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

    // TODO: 每个i表示一个圈，不论len是奇是偶都是一共有len/2个圈要进行旋转，从外圈向内依次旋转，直到中心点或者最内圈
    public String convert(String s, int numRows) {
        if(numRows == 1 || numRows >= s.length()) return s;
        StringBuffer[] rows = new StringBuffer[numRows];
        char[] str = s.toCharArray();
        int index = 0;
        for(int i = 0; i < str.length; i++) {
            if(index < numRows && rows[index] == null) rows[index] = new StringBuffer();
            if(index < numRows) rows[index].append(str[i]);
            else rows[2 * numRows - 2 - index].append(str[i]);
            index = (index + 1) % (2 * numRows - 2);
        }
        StringBuffer res = new StringBuffer();
        for(StringBuffer sb : rows) {
            res.append(sb);
        }
        return res.toString();
    }
}
