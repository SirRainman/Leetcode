package dataStructure.hash;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 维护一个集合，支持如下几种操作：
 *
 * “I x”，插入一个数x；
 * “Q x”，询问数x是否在集合中出现过；
 * 现在要进行N次操作，对于每个询问操作输出对应的结果。
 *
 * 输入格式
 * 第一行包含整数N，表示操作数量。
 *
 * 接下来N行，每行包含一个操作指令，操作指令为”I x”，”Q x”中的一种。
 *
 * 输出格式
 * 对于每个询问指令“Q x”，输出一个询问结果，如果x在集合中出现过，则输出“Yes”，否则输出“No”。
 *
 * 每个结果占一行。
 *
 * 数据范围
 * 1≤N≤105
 * −109≤x≤109
 * 输入样例：
 * 5
 * I 1
 * I 2
 * I 3
 * Q 2
 * Q 5
 * 输出样例：
 * Yes
 * No
 *
 * https://www.acwing.com/problem/content/842/
 */
public class A840_SimulateHashTable_Kaifangxunzhi {
    static int N = 200003; //比两倍到三倍的数据范围大一丢丢的素数，可以减少冲突的概率
    static int[] hash = new int[N];

    // 如果x在哈希表中，返回x的下标；如果x不在哈希表中，返回x应该插入的位置
    static int find(int x) {
        int k = (x % N + N) % N;
        while(hash[k] != Integer.MAX_VALUE && hash[k] != x) {
            k = (k + 1) % N;
        }
        return k;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 初始化
        Arrays.fill(hash, Integer.MAX_VALUE);

        int times = in.nextInt();
        while(times-- > 0) {
            String op = in.next();
            int x = in.nextInt();
            int index = find(x);
            if(op.compareTo("I") == 0) {
                hash[index] = x;
            } else {
                System.out.println(hash[index] !=  Integer.MAX_VALUE? "Yes" : "No");
            }
        }
    }
}
