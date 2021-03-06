package dataStructure.array;

/**
 * 在R行C列的矩阵上，我们从(r0, c0)面朝东面开始
 * 这里，网格的西北角位于第一行第一列，网格的东南角位于最后一行最后一列。
 * 现在，我们以顺时针按螺旋状行走，访问此网格中的每个位置。
 * 每当我们移动到网格的边界之外时，我们会继续在网格之外行走（但稍后可能会返回到网格边界）。
 * 最终，我们到过网格的所有R * C个空间。
 * 按照访问顺序返回表示网格位置的坐标列表。
 *
 * 示例 1：
 * 输入：R = 1, C = 4, r0 = 0, c0 = 0
 * 输出：[[0,0],[0,1],[0,2],[0,3]]
 *
 *
 * 示例 2：
 * 输入：R = 5, C = 6, r0 = 1, c0 = 4
 * 输出：[[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],[1,2],[0,2],[4,5],[4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]
 *
 * 提示：
 * 1 <= R <= 100
 * 1 <= C <= 100
 * 0 <= r0 < R
 * 0 <= c0 < C
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/spiral-matrix-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q54_SpiralMatrix3 {
    int R, C, idx = 1;
    int[][] res;
    void check(int r0, int c0){
        if(r0 >= 0 && r0 < R && c0 >= 0 && c0 < C){
            res[idx][0] = r0;
            res[idx][1] = c0;
            idx++;
        }
    }
    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        this.R = R; this.C = C;
        int len = R * C;
        res = new int[len][2];
        res[0][0] = r0; res[0][1] = c0;
        int row = 1, col = 1;
        while(idx < len){
            for(int i = 1; i <= row; i++) check(r0, ++c0);
            row++;
            for(int i = 1; i <= col; i++) check(++r0, c0);
            col++;
            for(int i = 1; i <= row; i++) check(r0, --c0);
            row++;
            for(int i = 1; i <= col; i++) check(--r0, c0);
            col++;
        }
        return res;
    }
}
