package dataStructure.tree.trie;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: Leetcode
 * @description:
 * @author: Rain
 * @create: 2021-03-29 21:53
 **/
public class Q208_ImplementTrie {
    // TODO: Trie 树
    class Trie {
        private Trie[] child;
        private boolean isWordEnd; // 是否存在 以该节点为结尾 的单词

        /** Initialize your data structure here. */
        public Trie() {
            child = new Trie[26];
            isWordEnd = false;
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            Trie t = find(word, true);
            t.isWordEnd = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            Trie t = find(word, false);
            return t != null && t.isWordEnd;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            Trie t = find(prefix, false);
            return t != null;
        }
        private Trie find(String word, boolean isInsert) {
            char[] w = word.toCharArray();
            Trie parent = this;
            for(int i = 0; i < w.length; i++) {
                int c = w[i] - 'a';
                if(parent.child[c] == null) {
                    if(!isInsert) return null;
                    parent.child[c] = new Trie();
                }
                parent = parent.child[c];
            }
            return parent;
        }
    }


    // TODO：数组模拟
    class Trie1 {
        private int[][] trie;
        private int[] count;
        private int idx;

        /** Initialize your data structure here. */
        public Trie1() {
            trie = new int[100010][26];
            count = new int[100010];
            idx = 0;
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            char[] w = word.toCharArray();
            int parent = 0;
            for(int i = 0; i < w.length; i++) {
                int c = w[i] - 'a';
                if(trie[parent][c] == 0) trie[parent][c] = ++idx;
                parent = trie[parent][c];
            }
            count[parent]++;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            char[] w = word.toCharArray();
            int parent = 0;
            for(int i = 0; i < w.length; i++) {
                int c = w[i] - 'a';
                if(trie[parent][c] == 0) return false;
                parent = trie[parent][c];
            }
            return count[parent] > 0;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            char[] w = prefix.toCharArray();
            int parent = 0;
            for(int i = 0; i < w.length; i++) {
                int c = w[i] - 'a';
                if(trie[parent][c] == 0) return false;
                parent = trie[parent][c];
            }
            return true;
        }
    }
}
