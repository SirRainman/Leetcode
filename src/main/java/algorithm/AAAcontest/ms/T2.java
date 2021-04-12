package algorithm.AAAcontest.ms;

/**
 * @program: Leetcode
 * @description:
 * @author: Rain
 * @create: 2021-03-26 21:29
 **/
public class T2 {
    public static int solution(int[] A, int K) {
        // write your code in Java SE 8
        int n = A.length;
        int[] leftMinNum = new int[n];
        int[] leftMaxNum = new int[n];
        int[] rightMinNum = new int[n];
        int[] rightMaxNum = new int[n];

        int minNumLeft = Integer.MAX_VALUE;
        int maxNumLeft = Integer.MIN_VALUE;
        int minNumRight = Integer.MAX_VALUE;
        int maxNumRight = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            minNumLeft = Math.min(minNumLeft, A[i]);
            leftMinNum[i] = minNumLeft;
            maxNumLeft = Math.max(maxNumLeft, A[i]);
            leftMaxNum[i] = maxNumLeft;

            minNumRight = Math.min(minNumRight, A[n - 1 - i]);
            rightMinNum[n - 1 - i] = minNumRight;
            maxNumRight = Math.max(maxNumRight, A[n - 1 - i]);
            rightMaxNum[n - 1 - i] = maxNumRight;
        }

        int res = Integer.MAX_VALUE;
        for(int i = 0; i < n - K + 1; i++) {
            int minAbs = 0;
            if(i == 0) {
                int maxN = rightMaxNum[i + K];
                int minN = rightMinNum[i + K];
                minAbs = maxN - minN;
            } else if(i > 0 && i + K < n) {
                int leftMax = leftMaxNum[i - 1];
                int leftMin = leftMinNum[i - 1];
                int rightMax = rightMaxNum[i + K];
                int rightMin = rightMinNum[i + K];
                minAbs = Math.max(leftMax, rightMax) - Math.min(leftMin, rightMin);
            } else if(i == n - K) {
                int maxN = leftMaxNum[i - 1];
                int minN = leftMinNum[i - 1];
                minAbs = maxN - minN;
            }
            res = Math.min(res, minAbs);
        }
        return res;
    }

    public static int solution1(int[] A, int k) {
        int n = A.length;
        int[] leftMin = new int[n];
        int[] leftMax = new int[n];
        int[] rightMin = new int[n];
        int[] rightMax = new int[n];
        int leftMinV = Integer.MAX_VALUE;
        int leftMaxV = Integer.MIN_VALUE;
        int rightMinV = Integer.MAX_VALUE;
        int rightMaxV = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            leftMinV = Math.min(leftMinV, A[i]);
            leftMaxV = Math.max(leftMaxV, A[i]);
            leftMin[i] = leftMinV;
            leftMax[i] = leftMaxV;
        }
        for (int i = n - 1; i >= 0; i--) {
            rightMinV = Math.min(rightMinV, A[i]);
            rightMaxV = Math.max(rightMaxV, A[i]);
            rightMin[i] = rightMinV;
            rightMax[i] = rightMaxV;
        }

        int minAm = Integer.MAX_VALUE;
        for (int i = 0; i < n - k + 1; i++) {
            if (i > 0 && i + k < n) {
                int lMax = leftMax[i - 1];
                int lMin = leftMin[i - 1];
                int rMax = rightMax[i + k];
                int rMin = rightMin[i + k];
                int am = Math.max(lMax, rMax) - Math.min(lMin, rMin);
                minAm = Math.min(minAm, am);
            } else if (i == 0) {
                int max = rightMax[i + k];
                int min = rightMin[i + k];
                int am = max - min;
                minAm = Math.min(minAm, am);
            } else if (i == n - k) {
                int max = leftMax[i - 1];
                int min = leftMin[i - 1];
                int am = max - min;
                minAm = Math.min(minAm, am);
            } else {
                System.out.println("i");
            }
        }
        return minAm;
    }

    public static void main(String[] args) {
        int[] A = {5, 3, 6, 1, 3};
        int k = 2;
        System.out.println(solution(A, k));
    }
}
