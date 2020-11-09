package dataStructure.tree.trie;

import java.util.Scanner;

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
