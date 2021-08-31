package algorithm.AAAcontest.pdd0831;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: Test
 * @description:
 *
 * 第一层红方块
 * 第二层蓝方块
 * 若两个不同颜色的方块的差值小于k，则可以把这两个方块消除，问最多可以消多少？
 *
 * 1
 * 3 4 1
 * 5 2 3
 * 1 2 9 7
 *
 * @author: Rain
 * @create: 2021-08-31 19:03
 **/
public class Test1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int i = 0; i < T; i++) {
            game(in);
        }
    }

    private static void game(Scanner in) {
        int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[m];


        for(int i = 0; i < n; i++) a[i] = in.nextInt();
        for(int i = 0; i < m; i++) b[i] = in.nextInt();

        Arrays.sort(a);
        Arrays.sort(b);

        int i = 0, j = 0, res = 0;
        while(i < n && j < m) {
            if(Math.abs(a[i] - b[j]) <= k) {
                res++;
                i++;j++;
            } else if(a[i] < b[j]) {
                i++;
            } else {
                j++;
            }
        }

        System.out.println(res);
    }
}
