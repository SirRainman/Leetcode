package dataStructure.union;

import java.util.*;

/**
 * 给定一个列表 accounts，每个元素 accounts[i]是一个字符串列表，
 * 其中第一个元素 accounts[i][0]是名称 (name)，其余元素是 emails 表示该账户的邮箱地址。
 * 现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。
 * 请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。
 * 合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是按顺序排列的邮箱地址。账户本身可以以任意顺序返回。
 *
 * 示例 1：
 * 输入：
 * accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 * 输出：
 * [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
 * 解释：
 * 第一个和第三个 John 是同一个人，因为他们有共同的邮箱地址 "johnsmith@mail.com"。 
 * 第二个 John 和 Mary 是不同的人，因为他们的邮箱地址没有被其他帐户使用。
 * 可以以任何顺序返回这些列表，例如答案 [['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com']，
 * ['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']] 也是正确的。
 * 
 *
 * 提示：
 * accounts的长度将在[1，1000]的范围内。
 * accounts[i]的长度将在[1，10]的范围内。
 * accounts[i][j]的长度将在[1，30]的范围内。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/accounts-merge
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q721_AccountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int emailsCount = 0;
        Map<String, String> emailsToName = new HashMap<>();
        Map<String, Integer> emailsToIndex = new HashMap<>();
        for(List<String> account : accounts) {
            String name = account.get(0);
            int size = account.size();
            for(int i = 1; i < size; i++) {
                String email = account.get(i);
                if(!emailsToIndex.containsKey(email)) {
                    emailsToIndex.put(email, emailsCount++);
                    emailsToName.put(email, name);
                }
            }
        }

        UnionFind uf = new UnionFind(emailsCount);
        for(List<String> account : accounts) {
            String firstEmail = account.get(1);
            int size = account.size();
            for(int i = 2; i < size; i++) {
                String currentEmail = account.get(i);
                uf.union(emailsToIndex.get(firstEmail), emailsToIndex.get(currentEmail));
            }
        }

        Map<Integer, List<String>> indexToEmails = new HashMap<>();
        for(String email : emailsToIndex.keySet()) {
            int index = uf.find(emailsToIndex.get(email));
            List<String> account = indexToEmails.getOrDefault(index, new ArrayList<>());
            account.add(email);
            indexToEmails.put(index, account);
        }

        List<List<String>> merged = new ArrayList<>();
        for (List<String> emails : indexToEmails.values()) {
            Collections.sort(emails);
            String name = emailsToName.get(emails.get(0));
            List<String> account = new ArrayList<String>();
            account.add(name);
            account.addAll(emails);
            merged.add(account);
        }
        return merged;
    }

    class UnionFind {
        private int[] parent;

        public UnionFind(int n ) {
            parent = new int[n];
            for(int i = 0; i < n; i++) parent[i] = i;
        }

        public int find(int a) {
            if(parent[a] != a) parent[a] = find(parent[a]);
            return parent[a];
        }

        public void union(int a, int b) {
            int pa = find(a), pb = find(b);
            if(pa == pb) return ;
            parent[pa] = pb;
        }
    }
}
