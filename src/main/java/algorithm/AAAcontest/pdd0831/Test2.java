package algorithm.AAAcontest.pdd0831;

import java.util.Scanner;

/**
 * @program: Test
 * @description:
 *
 * 4 -> 1
 * 7 -> 2
 * 10 -> 3
 * 22 -> 8
 * 问n个木棍可以拼几个正方形？
 *
 * @author: Rain
 * @create: 2021-08-31 19:31
 **/
public class Test2 {
    public static void main1(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            long n = in.nextLong();
            System.out.println(cal(n));
        }
    }

    private static long cal(long n) {
        if (n < 4) return 0;
        int m = (int) Math.sqrt((n + 1) / 2);
        int res = m * (m - 1);
        n = n - 2 * m * m + 1;
        if (n > 3) {
            res++;
            n -= 3;
            while (n >= 2) {
                res++;
                n -= 2;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int i = 0; i < T; i++) {
            long n = scanner.nextLong();
            long L = search(n);
            long remain = n - 2 * L * (L + 1);
            if (remain <= 2L) {
                System.out.println(L * L);
            } else if (remain <= 2 * L + 1) {
                System.out.println(L * L + (remain - 1) / 2);
            } else {
                System.out.println(L * L + L + (remain - 2 * L - 2) / 2);
            }
        }
    }

    private static long search(long n) {
        long l = 0, r = (long) Math.sqrt(n / 2);
        while (l <= r) {
            long m = l + (r - l) / 2;
            if (n >= 2 * m * (m + 1)) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return r;
    }
}
