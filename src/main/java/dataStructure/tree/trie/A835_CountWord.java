package dataStructure.tree.trie;

import java.util.Scanner;

/**
 *维护一个字符串集合，支持两种操作：
 * “I x”向集合中插入一个字符串x；
 * “Q x”询问一个字符串在集合中出现了多少次。
 * 共有N个操作，输入的字符串总长度不超过 105，字符串仅包含小写英文字母。
 *
 * 输入格式
 * 第一行包含整数N，表示操作数。
 * 接下来N行，每行包含一个操作指令，指令为”I x”或”Q x”中的一种。
 *
 * 输出格式
 * 对于每个询问指令”Q x”，都要输出一个整数作为结果，表示x在集合中出现的次数。
 *
 * 每个结果占一行。
 *
 * 数据范围
 * 1≤N≤2∗104
 * 输入样例：
 * 5
 * I abc
 * Q abc
 * Q ab
 * I ab
 * Q ab
 * 输出样例：
 * 1
 * 0
 * 1
 * 难度：简单
 * 时/空限制：1s / 64MB
 * 总通过数：8458
 * 总尝试数：11230
 * 来源：模板题
 * 算法标签
 *
 * https://www.acwing.com/problem/content/837/
 */
public class A835_CountWord {

    static int[][] son = new int[100010][26];
    static int[] count = new int[100010]; // TODO:以该节点结尾单词的数量
    static int idx = 0; // TODO:注意idx 的含义是什么？idx是指向下一个节点的指针，每个节点有26个孩子

    public static void insert(String str) {
        char[] word = str.toCharArray();
        int parent = 0;
        for (int i = 0; i < word.length; i++) {
            int c = word[i] - 'a';
            if (son[parent][c] == 0) son[parent][c] = ++idx;
            parent = son[parent][c];
        }
        count[parent]++; // 以该节点结尾的单词的数量++
    }

    public static int query(String str) {
        char[] word = str.toCharArray();
        int parent = 0;
        for (int i = 0; i < word.length; i++) {
            int c = word[i] - 'a';
            if (son[parent][c] == 0) return 0; // 没有这个单词
            parent = son[parent][c];
        }
        return count[parent];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int times = in.nextInt();
        while (times-- > 0) {
            String op = in.next();
            String str = in.next();
            if (op.compareTo("I") == 0) insert(str);
            else System.out.println(query(str));
        }
    }

}
