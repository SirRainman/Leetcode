package algorithm.AAAcontest.amazon;

import java.util.Locale;
import java.util.Scanner;

/**
 * @program: Leetcode
 * @description:
 * 已知：给定字符串 s，p
 * 问题：问p在s中出现几次？
 *
 * @author: Rain
 * @create: 2021-08-28 20:28
 **/
public class CountWord {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // String s = in.next(), p = in.next();
        String s = "TimedasdfTimfdlkfdkTim", p = "Tim";
        s.toLowerCase();
        p.toLowerCase();
        int res = kmp2(s, p);
        System.out.println(res);
    }

    private static int kmp(String s, String p) {
        int res = 0;
        int sLen = s.length(), pLen = p.length();

        int[] next = getNext(p, pLen);

        int i = 0, j = 0;
        while(i < sLen && j < pLen) {
            if(next[j] == -1 || s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
            if(j == pLen) {
                res++;
                i++;
                j = 0;
            }
        }
        return res;
    }

    private static int[] getNext(String p, int len) {
        int[] next = new int[len];
        next[0] = -1;

        int i = 0, k = -1;
        while(i < len - 1) {
            if(k == -1 || p.charAt(i) == p.charAt(k)) {
                i++;
                k++;
                next[i] = k;
            } else {
                k = next[k];
            }
        }
        return next;
    }


    private static int kmp2(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        int[] next = getNext(p, pLen);

        int res = 0;
        for (int i = 0, j = 0; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != p.charAt(j)) j = next[j - 1];
            if (s.charAt(i) == p.charAt(j)) j++;
            if (j == p.length()) {
                res++;
                j = 0;
            }
        }
        return res;
    }

    private static int[] getNext2(String p, int len) {
        int[] next = new int[len];
        for (int i = 1, j = 0; i < p.length(); i++) {
            while (j > 0 && p.charAt(i) != p.charAt(j)) j = next[j - 1];
            if (p.charAt(j) == p.charAt(i)) j++;
            next[i] = j;
        }
        return next;
    }
}
