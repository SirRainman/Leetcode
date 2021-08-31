package algorithm.AAAcontest.pdd0831;

import java.util.Random;
import java.util.Scanner;

/**
 * @program: Test
 * @description:
 * @author: Rain
 * @create: 2021-08-31 20:43
 **/
public class Test4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int i = 0; i < T; i++) {
            int n = in.nextInt();
            Random random = new Random();
            System.out.println(random.nextInt(n));
        }
    }
}
