package dataStructure.array;

import java.util.Arrays;

/**
 * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中B[i] 的值是数组 A 中除了下标 i 以外的元素的积, 即B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
 *
 * 示例:
 * 输入: [1,2,3,4,5]
 * 输出: [120,60,40,30,24]
 * 
 *
 * 提示：
 * 所有元素乘积之和不会溢出 32 位整数
 * a.length <= 100000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/gou-jian-cheng-ji-shu-zu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class QOffer66_MultiOthers {
    public int[] constructArr1(int[] a) {
        int n = a.length;
        int[] left = new int[n + 1];
        Arrays.fill(left, 1);
        int[] right = new int[n + 2];
        Arrays.fill(right, 1);
        int[] res = new int[n];
        for(int i = 1; i < n; i++) left[i] = left[i - 1] * a[i - 1];
        for(int i = n - 2; i >= 0; i--) right[i] = right[i + 1] * a[i + 1];
        for(int i = 0; i < n; i++) res[i] = left[i] * right[i];
        return res;
    }

    public int[] constructArr(int[] a) {
        int n = a.length;
        int[] res = new int[n];
        for(int i = 0, product = 1; i < n; i++) {
            res[i] = product;
            product *= a[i];
        }
        for(int i = n - 1, product = 1; i >= 0; i--) {
            res[i] *= product;
            product *= a[i];
        }
        return res;
    }
}