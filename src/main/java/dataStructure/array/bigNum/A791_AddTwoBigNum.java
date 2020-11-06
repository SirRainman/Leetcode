package dataStructure.array.bigNum;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 给定两个正整数，计算它们的和。
 *
 * 输入格式
 * 共两行，每行包含一个整数。
 *
 * 输出格式
 * 共一行，包含所求的和。
 *
 * 数据范围
 * 1≤整数长度≤100000
 * 输入样例：
 * 12
 * 23
 * 输出样例：
 * 35
 *
 * https://www.acwing.com/problem/content/793/
 */
public class A791_AddTwoBigNum {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] A = in.nextLine().toCharArray();
        char[] B = in.nextLine().toCharArray();

        reverse(A);
        reverse(B);
        List<Integer> ans = add(A, B);

        // for(int i = ans.size() - 1; i >= 0; i--) {
        //     System.out.print(ans.get(i));
        // }
        for(int x : ans) System.out.print(x);
    }

    public static void reverse(char[] A) {
        int left = 0, right = A.length-1;
        while(left < right) {
            char t = A[left];
            A[left] = A[right];
            A[right] = t;
            left++; right--;
        }
    }

    public static List<Integer> add(char[] A, char[] B) {
        if (A.length < B.length) return add(B, A);
        List<Integer> ans = new ArrayList<>();
        int carry = 0;
        for (int i = 0; i < A.length; i++) {
            carry += A[i] - '0';
            if (i < B.length) carry += B[i] - '0';
            ans.add(0, carry % 10);
            carry /= 10;
        }
        if (carry != 0) ans.add(0, carry);
        return ans;
    }
}
