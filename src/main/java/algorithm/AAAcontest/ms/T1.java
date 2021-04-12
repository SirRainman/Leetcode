package algorithm.AAAcontest.ms;

import java.util.Arrays;

/**
 * @program: Leetcode
 * @description:
 * @author: Rain
 * @create: 2021-03-26 22:11
 **/
public class T1 {
    public int solution(int[] D, int[] C, int P) {
        int n = D.length;
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = D[i];
            pairs[i][1] = C[i];
        }
        Arrays.sort(pairs, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if(P < pairs[i][1]) break;
            P -= pairs[i][1];
            cnt++;
        }
        return cnt;
    }

    public static void main(String[] args) {
        String s1 = new StringBuilder("Ni").append("SL").toString();
        System.out.println(s1 == s1.intern());
        String s2 = new StringBuilder("nu").append("ll").toString();
        System.out.println(s2 == s2.intern());
        String s3 = new StringBuilder("ja").append("va").toString();
        System.out.println(s3 == s3.intern());
    }
}