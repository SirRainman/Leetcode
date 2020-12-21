package algorithm.dp;

import java.util.Scanner;

/**
 * 求把N*M的棋盘分割成若干个1*2的的长方形，有多少种方案。
 * 例如当N=2，M=4时，共有5种方案。当N=2，M=3时，共有3种方案。
 *
 * 如下图所示：
 * 2411_1.jpg
 *
 * 输入格式
 * 输入包含多组测试用例。
 * 每组测试用例占一行，包含两个整数N和M。
 * 当输入用例N=0，M=0时，表示输入终止，且该用例无需处理。
 *
 * 输出格式
 * 每个测试用例输出一个结果，每个结果占一行。
 *
 * 数据范围
 * 1≤N,M≤11
 * 输入样例：
 * 1 2
 * 1 3
 * 1 4
 * 2 2
 * 2 3
 * 2 4
 * 2 11
 * 4 11
 * 0 0
 * 输出样例：
 * 1
 * 0
 * 1
 * 2
 * 3
 * 5
 * 144
 * 51205
 *
 * https://www.acwing.com/problem/content/293/
 */
public class HardA291_MenderamDream {
    static int n, m;
    static int N = 13;
    static long[][] dp = new long[N][1 << N];
    static boolean[] st = new boolean[1 << N];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while(true) {
            n = in.nextInt(); m = in.nextInt();
            if(n == 0 && m == 0) break;

            for(int i = 0; i < 1 << n; i++) { // 每一行的空格 要么放 要么不放 这个横着的方块，所以是2^n
                st[i] = true; // i是该列的横块的放置方案，因为一列有n行，所以横着放的方案数为2 ^ n
                int count = 0; // 记录该列某一行上方的方块中，没有伸过来的横着的方块的个数，即空闲块的个数
                for(int j = 0; j < n; j++) {
                    if((i >> j & 1) == 1) { // 判断i放置状态下，该行第j列的方块是否处于空闲状态，即是否有横着的方块伸过来
                        if((count & 1) == 1) { // 如果有横着的方块伸过来，判断该列第j行上方的空闲区块是否是奇数，如果是奇数，说明这个放置方案是不合理的，因为这样没有办法竖着放1*2长的方块
                            st[i] = false;
                            break;
                        }
                        count = 0;
                    } else { // 该列第j行的区块处于空闲状态，没有横着的区块伸过来
                        count++;
                    }
                }
                if((count & 1) == 1) st[i] = false; // 判断一下该列的所有空闲区块是不是奇数
            }

            // TODO：状态表示
            //  f[i][j]表示 i-1列的方案数已经确定，从i-1列伸出，并且第i列的状态是j的所有方案数
            dp[0][0] = 1; // 棋盘是从第0列开始，没有-1列，所以第0列第0行，不会有延伸出来的小方块。没有横着摆放的小方块，所有小方块都是竖着摆放的，这种状态记录为一种方案

            for(int i = 1; i <= m; i++) { // // 棋盘一共有0~m-1列，遍历每一列
                for(int j = 0; j < 1 << n; j++) { // j = i - 1，第i列的前一列中，所有伸过来的横块的放置方案
                    dp[i][j] = 0;
                    for(int k = 0; k < 1 << n; k++) { // k = i - 2，第j列的前一列中，所有伸过来的横块的放置方案
                        // TODO：状态转移的条件
                        //  1 j & k == 0 第k列的横块伸到第j列后，第j列该位置不能再放横块
                        //  2 st[j | k] == true 第k列的横块伸到第j列后，第j列再放置横块后，空闲块不能为奇数，即第k列和第j列第放置方案一定是合理第放置方案
                        if((j & k) == 0 && st[j | k] == true) {
                            dp[i][j] += dp[i - 1][k];
                        }
                    }
                }
            }

            // TODO：
            //  f[m][0]表示 前m-1列的方案数已经确定，从m-1列伸出，并且第m列的状态是0的所有方案数
            //  也就是m列不放小方格，前m-1列已经完全摆放好并且不伸出来的状态
            System.out.println(dp[m][0]);
        }
    }
}
