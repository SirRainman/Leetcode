package dataStructure.array.bigNum;

import java.util.*;

/**
 * 给定两个非负整数A，B，请你计算 A / B的商和余数。
 *
 * 输入格式
 * 共两行，第一行包含整数A，第二行包含整数B。
 *
 * 输出格式
 * 共两行，第一行输出所求的商，第二行输出所求余数。
 *
 * 数据范围
 * 1≤A的长度≤100000,
 * 1≤B≤10000
 * B 一定不为0
 *
 * 输入样例：
 * 7
 * 2
 * 输出样例：
 * 3
 * 1
 *
 * https://www.acwing.com/problem/content/796/
 */
public class A794_DivideTwoNum {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] AA = in.nextLine().toCharArray();
        int b = in.nextInt();

        List<Integer> A = new ArrayList<>();
        for (int i = AA.length - 1; i >= 0; i--) {
            A.add(AA[i] - '0');
        }
        List<Integer> ans = divideTwoNum(A, b);
        for(int i = ans.size() - 2; i >= 0; i--) System.out.print(ans.get(i));
        System.out.println();
        System.out.print(ans.get(ans.size() - 1));
    }

    // TODO：注意这是大数除小数
    public static List<Integer> divideTwoNum(List<Integer> A, int b) {
        List<Integer> ans = new ArrayList<>();
        int carry = 0;
        for(int i = A.size() - 1; i >= 0; i--) {
            carry = carry * 10 + A.get(i);
            ans.add(carry / b);
            carry %= b;
        }
        Collections.reverse(ans);
        while(ans.size() > 1 && ans.get(ans.size() - 1) == 0) ans.remove(ans.size() - 1);
        // 把余数也加上
        ans.add(carry);
        return ans;
    }

    // TODO：看一看leetcode怎么处理的？？？
}
