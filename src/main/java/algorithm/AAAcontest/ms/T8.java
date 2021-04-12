package algorithm.AAAcontest.ms;

/**
 * @program: Leetcode
 * @description:
 * 从数组中选出一堆点，这一堆点中的任意两个点之间点距离要模M同余
 * @author: Rain
 * @create: 2021-03-27 15:37
 **/
public class T8 {
    public static int solution(int[] A, int M) {
        int[] aligned = new int[M];
        for(int i = 0; i < A.length; i++) {
            aligned[(((A[i] % M) + M) % M)]++;
        }
        int res = 0;
        for(int n : aligned) res = Math.max(res, n);
        return res;
    }

    public static void main(String[] args) {
        int[] A = {-3, -2, 1, 0, 8, 7, 1};
        System.out.println(solution(A, 3));
    }
}
