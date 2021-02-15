package algorithm.math.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: Leetcode
 * @description:
 *
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * 示例:
 * 输入: 3
 * 输出: [1,3,3,1]
 *
 * 进阶：
 * 你可以优化你的算法到 O(k) 空间复杂度吗？
 *
 * @author: Rain
 * @create: 2021-02-14 15:09
 **/
public class Q119_PascalTriangle2 {

    public List<Integer> getRow1(int rowIndex) {
        int[][] nums = new int[rowIndex + 1][rowIndex + 1];
        for(int i = 0; i <= rowIndex; i++) {
            for(int j = 0; j <= i; j++) {
                if(j == 0 || j == i) nums[i][j] = 1;
                else if(i > 0) nums[i][j] = nums[i - 1][j] + nums[i - 1][j - 1];
            }
        }

        List<Integer> res = new ArrayList<>();
        for(int j = 0; j <= rowIndex; j++) res.add(nums[rowIndex][j]);
        return res;
    }

    // 只用了与上一层有关的数据
    public List<Integer> getRow2(int rowIndex) {
        int[] nums = new int[rowIndex + 1];
        for(int i = 0; i <= rowIndex; i++) {
            nums[0] = nums[i] = 1;
            for(int j = i - 1; j > 0; j--) {
                nums[j] = nums[j] + nums[j - 1];
            }
        }

        List<Integer> res = new ArrayList<>();
        for(int j = 0; j <= rowIndex; j++) res.add(nums[j]);
        return res;
    }

    // 递推公式: C(n, m) = C(n, m - 1) * (n - m + 1) / m
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<Integer>();
        row.add(1);
        for (int i = 1; i <= rowIndex; ++i) {
            row.add((int) ((long) row.get(i - 1) * (rowIndex - i + 1) / i));
        }
        return row;
    }

}
