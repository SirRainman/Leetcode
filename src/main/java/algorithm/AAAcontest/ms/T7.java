package algorithm.AAAcontest.ms;

/**
 * @program: Leetcode
 * @description:
 * 给定一个无向图，从中选出两个直接相连的点，使这两个点发散出去点边的数目最多
 * @author: Rain
 * @create: 2021-03-27 15:16
 **/
public class T7 {
    public static int solution(int[] A, int[] B, int N) {
        int edges = A.length;
        int[] degree = new int[N + 1];
        for(int i = 0; i < edges; i++) {
            degree[A[i]]++;
            degree[B[i]]++;
        }

        int res = 0;
        for(int i = 0; i < edges; i++) {
            int rank = degree[A[i]] + degree[B[i]] - 1;
            res = Math.max(res, rank);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 4, 5};
        int[] B = {2, 3, 5, 6};
        System.out.println(solution(A, B, 6));
    }
}
