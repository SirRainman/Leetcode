package dataStructure.union;

import java.util.*;

/**
 * 给你一个字符串s，以及该字符串中的一些「索引对」数组pairs，
 * 其中pairs[i] =[a, b]表示字符串中的两个索引（编号从 0 开始）。
 * 你可以 任意多次交换 在pairs中任意一对索引处的字符。
 * 返回:在经过若干次交换后，s可以变成的按字典序最小的字符串。
 *
 * 
 *
 * 示例 1:
 * 输入：s = "dcab", pairs = [[0,3],[1,2]]
 * 输出："bacd"
 * 解释： 
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[1] 和 s[2], s = "bacd"
 *
 * 示例 2：
 * 输入：s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * 输出："abcd"
 * 解释：
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[0] 和 s[2], s = "acbd"
 * 交换 s[1] 和 s[2], s = "abcd"
 *
 * 示例 3：
 * 输入：s = "cba", pairs = [[0,1],[1,2]]
 * 输出："abc"
 * 解释：
 * 交换 s[0] 和 s[1], s = "bca"
 * 交换 s[1] 和 s[2], s = "bac"
 * 交换 s[0] 和 s[1], s = "abc"
 * 
 *
 * 提示：
 * 1 <= s.length <= 10^5
 * 0 <= pairs.length <= 10^5
 * 0 <= pairs[i][0], pairs[i][1] <s.length
 * s中只含有小写英文字母
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/smallest-string-with-swaps
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ1202_SmallestStringWithSwaps {
    // TODO: 注意维护数并查集大小的方法
    class UnionSet {
        int n;
        int[] parent;
        int[] size;

        public UnionSet(int n) {
            this.n = n;
            size = new int[n];
            Arrays.fill(size, 1);
            parent = new int[n];
            for(int i = 0; i < n; i++) parent[i] = i;
        }
        public void union(int a, int b) {
            int pa = find(a), pb = find(b);
            if(pa == pb) return;
            if(size[pa] > size[pb]) {
                int t = pa;
                pa = pb;
                pb = t;
            }
            parent[pa] = pb;
            size[pb] += size[pa];
        }

        public int find(int a) {
            if(parent[a] != a) parent[a] = find(parent[a]);
            return parent[a];
        }
    }

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if(pairs == null || pairs.size() == 0) return s;
        char[] str = s.toCharArray();
        UnionSet unionSet = new UnionSet(str.length);
        // TODO：将所有的下标加入到并查集中
        for(List<Integer> pair : pairs) {
            int i = pair.get(0), j = pair.get(1);
            unionSet.union(i, j);
        }

        // TODO：找到每个并查集中的元素
        Map<Integer, PriorityQueue<Character>> son = new HashMap<>();
        for(int i = 0; i < str.length; i++) {
            int pi = unionSet.find(i);
            if(!son.containsKey(pi)) son.put(pi, new PriorityQueue<>());
            son.get(pi).add(str[i]);
        }

        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < str.length; i++) {
            int pi = unionSet.find(i);
            char c = son.get(pi).poll();
            sb.append(c);
        }
        return sb.toString();
    }
}
