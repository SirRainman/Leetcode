package algorithm.AAAcontest.ms;

import java.util.Arrays;

/**
 * @program: Leetcode
 * @description:
 * 给一个1~N点，M条边点 的无向图
 * 点的权值是1~N
 * 边的权值是端点的权值相加
 * 现在给这些点赋值，求怎么赋值可以得到最大值
 * @author: Rain
 * @create: 2021-03-27 11:22
 **/
public class T5 {
    public static int solution(int N, int[] A, int[] B) {
        int[] out = new int[N + 1];
        for(int i = 0; i < A.length; i++) {
            out[A[i]]++;
            out[B[i]]++;
        }
        Arrays.sort(out);
        int res = 0;
        for(int i = N; i >= 1; i--) {
            res += i * out[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] A = {2, 2, 1, 2};
        int[] B = {1, 3, 4, 4};
        int N = 5;
        System.out.println(solution(N, A, B));
    }
}