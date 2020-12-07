package dataStructure.array.bigNum;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 给定两个正整数A和B，请你计算A * B的值。
 *
 * 输入格式
 * 共两行，第一行包含整数A，第二行包含整数B。
 *
 * 输出格式
 * 共一行，包含A * B的值。
 *
 * 数据范围
 * 1≤A的长度≤100000,
 * 0≤B≤10000
 * 输入样例：
 * 2
 * 3
 * 输出样例：
 * 6
 *
 * https://www.acwing.com/problem/content/795/
 */
public class A793_MultiTwoNum {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] AA = in.nextLine().toCharArray();
        int b = in.nextInt();
        List<Integer> ans = multi(AA, b);
        for(int i = ans.size() - 1; i >= 0; i--) System.out.print(ans.get(i));
    }

    // TODO：注意这是大数 * 小数
    //  小数 * 小数的应该怎么半？？？？
    public static List<Integer> multi(char[] A, int b) {
        List<Integer> C = new ArrayList<>();
        for(int i = A.length - 1, carry = 0; i >= 0 || carry != 0; i--) {
            if(i >= 0) carry += (A[i] - '0') * b;
            C.add(carry % 10);
            carry /= 10;
        }

        while(C.size() > 1 && C.get(C.size() - 1) == 0 ) C.remove(C.size() - 1);
        return C;
    }

    // TODO：重要 ！！！！！
    //  这是小数 * 小数的情况
    //  leetcode：https://leetcode-cn.com/problems/multiply-strings
    public String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0")) return "0";

        char[] A = num1.toCharArray();
        char[] B = num2.toCharArray();

        int[] ans = new int[A.length + B.length];
        for(int i = A.length - 1; i >= 0; i--) {
            int a = A[i] - '0';
            for(int j = B.length - 1; j >= 0; j--) {
                int b = B[j] - '0';
                // TODO:竖式计算，每次都能确定一位
                //  想一想为什么会出现这种情况？？？
                int sum = ans[i + j + 1] + a * b;
                ans[i + j + 1] = sum % 10;
                ans[i + j] += sum / 10;
            }
        }
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < ans.length; i++) {
            if(i == 0 && ans[i] == 0 ) continue;
            sb.append(ans[i]);
        }
        return sb.toString();
    }
}
