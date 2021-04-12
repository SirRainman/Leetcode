package algorithm.binarySearch;

/**
 * @program: Leetcode
 * @description:
 * @author: Rain
 * @create: 2021-03-24 11:17
 **/
public class Q69_Sqrt {
    public int mySqrt(int x) {
        int left = 0, right = x;
        while(left < right) {
            int mid = left + (right - left) / 2 + 1;
            if((long)mid * mid <= x) left = mid;
            else right = mid - 1;
        }
        return left;
    }
}
