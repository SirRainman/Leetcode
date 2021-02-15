package algorithm.math.test;

import java.util.ArrayList;
import java.util.List;

public class Q118_PascalTriangle {
    // TODO: 杨辉三角的性质
    //  1 每行数字左右对称，由 1 开始逐渐变大再变小，并最终回到 1。
    //  2 第 n 行（从 0 开始编号）的数字有 n+1 项，前 n 行共有 n * (n + 1) / 2
    //  3 第 n 行的第 m 个数（从 0 开始编号）可表示为可以被表示为组合数 C(n,m)，记作从 n 个不同元素中取 m 个元素的组合数。
    //  4 每个数字等于上一行的左右两个数字之和，可用此性质写出整个杨辉三角。
    //      即第 n 行的第 i 个数等于第 n - 1 行的第 i - 1 个数和第 i 个数之和。
    //      C(n, i) = C(n - 1, i) + C(n - 1, i - 1)
    //  5 (a + b) ^ n 的展开式（二项式展开）中的各项系数依次对应杨辉三角的第 n 行中的每一项。
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if(numRows == 0) return res;
        for(int i = 0; i < numRows; i++) {
            List<Integer> level = new ArrayList();
            for(int j = 0; j <= i; j++) {
                if(j == 0 || j == i) {
                    level.add(1);
                    continue;
                }
                level.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
            }
            res.add(level);
        }
        return res;
    }
}
