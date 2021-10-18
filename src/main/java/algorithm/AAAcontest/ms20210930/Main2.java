package algorithm.AAAcontest.ms20210930;

/**
 * @program: Leetcode
 * @description:
 * @author: Rain
 * @create: 2021-09-30 13:55
 **/
public class Main2 {
    public static void main(String[] args) {
        // int[] A = new int[] {1, 2, 2, 4};
        int[] A = new int[] {4, 2, 4, 6};
        // int[] A = new int[] {1, 1, 2, 1};
        System.out.println(solution(A));
    }

    public static int solution(int[] A) {
        // write your code in Java SE 8
        int sum = 0;
        for(int x : A) sum += x;

        int n = A.length;
        int average = sum / n;
        if(sum % n != 0) average++;

        int res = 0;
        for(int x : A) {
            if(x < average) res += average - x;
        }

        return res;
    }
}
