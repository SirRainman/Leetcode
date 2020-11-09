package dataStructure.tree.trie;

public class A835_CountWord {

    static int[][] son = new int[100010][26];
    static int[] count = new int[100010];
    static int idx = 0;

    public static void insert(String str) {
        char[] word = str.toCharArray();
        int parent = 0;
        for (int i = 0; i < word.length; i++) {
            int c = word[i] - 'a';
            if (son[parent][c] == 0) son[parent][c] = ++idx;
            parent = son[parent][c];
        }
        count[parent]++;
    }

    public static int query(String str) {
        char[] word = str.toCharArray();
        int parent = 0;
        for (int i = 0; i < word.length; i++) {
            int c = word[i] - 'a';
            if (son[parent][c] == 0) return 0;
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
