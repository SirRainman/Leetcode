package dataStructure.array.bigNum;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 给定两个正整数，计算它们的差，计算结果可能为负数。
 *
 * 输入格式
 * 共两行，每行包含一个整数。
 *
 * 输出格式
 * 共一行，包含所求的差。
 *
 * 数据范围
 * 1≤整数长度≤105
 * 输入样例：
 * 32
 * 11
 * 输出样例：
 * 21
 *
 * https://www.acwing.com/problem/content/794/
 */
public class A792_SubTwoBigNum {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] AA = in.nextLine().toCharArray(), BB = in.nextLine().toCharArray();
        List<Integer> A = new ArrayList<>(), B = new ArrayList<>();
        for(int i = AA.length - 1; i >= 0; i--) A.add(AA[i] - '0');
        for(int i = BB.length - 1; i >= 0; i--) B.add(BB[i] - '0');

        List<Integer> C;
        if(cmp(A, B)) C = subTwoBigNum(A, B);
        else {
            C = subTwoBigNum(B, A);
            System.out.print("-");
        }
        for(int i = C.size() - 1; i >= 0; i--) System.out.print(C.get(i));
    }

    // 比较大小
    public static boolean cmp(List<Integer> A, List<Integer> B){
        if(A.size() != B.size()) return A.size() > B.size();
        for(int i = A.size() - 1; i >= 0; i--) {
            if(A.get(i) != B.get(i)) {
                return A.get(i) > B.get(i);
            }
        }
        return true;
    }

    // A永远比B大
    public static List<Integer> subTwoBigNum(List<Integer> A, List<Integer> B) {
        List<Integer> C = new ArrayList<>();
        for (int i = 0, t = 0; i < A.size(); i++) {
            // 先删掉借位
            t = A.get(i) - t;
            if(i < B.size()) t = t - B.get(i);
            C.add((t + 10) % 10);
            // 判断借位
            t = t < 0 ? 1 : 0;
        }
        // TODO: 删掉前导多余的0
        while (C.size() > 1 && C.get(C.size() - 1) == 0) C.remove(C.size() - 1);
        return C;
    }
}
