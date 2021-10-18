package algorithm.AAAcontest.ms20210930;

import java.util.ArrayList;

/**
 * @program: Leetcode
 * @description:
 * @author: Rain
 * @create: 2021-09-30 13:54
 **/
public class Main1 {
    public static void main(String[] args) {
        // int N = 16; // 79
        // int N = 19; // 199
        int N = 7;  // 7
        System.out.println(solution1(N));
    }

    public static int solution(int N) {
        // write your code in Java SE 8
        int res = Integer.MAX_VALUE;

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String s = String.valueOf(i);
            int sum = 0;
            for(int j = 0; j < s.length(); j++) sum += s.charAt(j) - '0';
            if(sum == N) return Math.min(res, i);
        }

        return res;
    }

    public static int solution1(int N) {
        // write your code in Java SE 8
        int[] count = new int[10];
        for (int i = 9; i >= 1; i--) {
            while(N >= i) {
                count[i]++;
                N -= i;
            }
        }

        int res = 0;
        for(int i = 1; i <= 9; i++) {
            if(count[i] > 0) {
                for(int j = 0; j < count[i]; j++) {
                    res = res * 10 + i;
                }
            }
        }
        return res;
    }
}
