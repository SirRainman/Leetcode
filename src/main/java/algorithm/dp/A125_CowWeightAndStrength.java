package algorithm.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 有n头牛，第i头牛的重量为wi，强壮值为si，
 * n头牛叠在一起
 * 一头牛的危险系数定义为这头牛上边的重量之和减去这头牛的强壮值，
 * 求一种顺序，使得 n头牛最大的危险系数最小
 *
 * https://www.acwing.com/problem/content/127/
 */
public class A125_CowWeightAndStrength {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] cows = new int[n][2];
        for(int i = 0; i < n; i++) {
            cows[i][0] = in.nextInt();
            cows[i][1] = in.nextInt();
        }

        // TODO: 想一想为什么这么排序！！！
        Arrays.sort(cows, (o1, o2) -> (o1[0] + o1[1]) - (o2[0] + o2[1]));

        int res = Integer.MIN_VALUE, sum = 0;
        for(int i = 0; i < n; i++) {
            res = Math.max(res, sum - cows[i][1]);
            sum += cows[i][0];
        }
        System.out.print(res);
    }
}
