package dataStructure.array;

public class Q54_SpiralMatrix2 {
    public int[][] generateMatrix(int n) {
        int left = 0, right = n - 1, up = 0, down = n - 1;
        int index = 1, length = n * n;
        int[][] res = new int[n][n];
        while(index <= length) {
            for(int j = left; j <= right; j++) res[up][j] = index++;
            up++;
            for(int i = up; i <= down; i++) res[i][right] = index++;
            right--;
            for(int j = right; j >= left; j--) res[down][j] = index++;
            down--;
            for(int i = down; i >= up; i--) res[i][left] = index++;
            left++;
        }
        return res;
    }
}
