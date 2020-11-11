package algorithm.diff_prefixSum;

import java.io.*;

/**
 * 输入一个n行m列的整数矩阵，再输入q个操作，每个操作包含五个整数x1, y1, x2, y2, c，其中(x1, y1)和(x2, y2)表示一个子矩阵的左上角坐标和右下角坐标。
 *
 * 每个操作都要将选中的子矩阵中的每个元素的值加上c。
 *
 * 请你将进行完所有操作后的矩阵输出。
 *
 * 输入格式
 * 第一行包含整数n,m,q。
 *
 * 接下来n行，每行包含m个整数，表示整数矩阵。
 *
 * 接下来q行，每行包含5个整数x1, y1, x2, y2, c，表示一个操作。
 *
 * 输出格式
 * 共 n 行，每行 m 个整数，表示所有操作进行完毕后的最终矩阵。
 *
 * 数据范围
 * 1≤n,m≤1000,
 * 1≤q≤100000,
 * 1≤x1≤x2≤n,
 * 1≤y1≤y2≤m,
 * −1000≤c≤1000,
 * −1000≤矩阵内元素的值≤1000
 * 输入样例：
 * 3 4 3
 * 1 2 2 1
 * 3 2 2 1
 * 1 1 1 1
 * 1 1 2 2 1
 * 1 3 2 3 2
 * 3 1 3 4 1
 * 输出样例：
 * 2 3 4 1
 * 4 3 4 1
 * 2 2 2 2
 *
 *
 * https://www.acwing.com/problem/content/800/
 */
public class HardA798_DifferanceMatrix {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] str = reader.readLine().split(" ");

        int row = Integer.parseInt(str[0]), col = Integer.parseInt(str[1]);
        int times = Integer.parseInt(str[2]);

        int[][] nums = new int[row + 1][col + 1];
        int[][] diff = new int[row + 2][col + 2];

        for(int i = 1; i <= row; i++) {
            str = reader.readLine().split(" ");
            for(int j = 1; j <= col; j++) {
                nums[i][j] = Integer.parseInt(str[j - 1]);
                insert(diff, i, j, i, j, nums[i][j]);
            }
        }

        while(times-- > 0) {
            str = reader.readLine().split(" ");
            int x1 = Integer.parseInt(str[0]), y1 = Integer.parseInt(str[1]), x2 = Integer.parseInt(str[2]), y2 = Integer.parseInt(str[3]), value = Integer.parseInt(str[4]);
            insert(diff, x1, y1, x2, y2, value);
        }

        for(int i = 1; i <= row; i++) {
            for(int j = 1; j <= col; j++) {
                // TODO：想一下为什么会这样？？？
                // diff[i][j] += diff[i - 1][j] + diff[i][j - 1] - diff[i - 1][j - 1];
                // log.write(diff[i][j] + " ");

                nums[i][j] = nums[i][j - 1] + nums[i - 1][j] + diff[i][j] - nums[i - 1][j - 1];
                log.write(nums[i][j] + " ");
            }
            log.newLine();
        }

        log.flush();
        reader.close();
        log.close();
    }

    // TODO: 一定得注意差分矩阵的定义 自己画一个矩形，然后分析一下四个角都干了啥
    //  定义：diff[i][j] = nums[i][j] - nums[i - 1][j] - nums[i][j - 1] + nums[i - 1][j - 1];
    public static void insert(int[][] diff, int x1, int y1, int x2, int y2, int value) {
        diff[x1][y1] += value;
        diff[x1][y2 + 1] -= value;
        diff[x2 + 1][y1] -= value;
        diff[x2 + 1][y2 + 1] += value;
    }
}
