package dataStructure.tree.pre;

import dataStructure.TreeNode;

/**
 * 给定一颗根结点为root的二叉树，树中的每一个结点都有一个从0 到25的值，分别代表字母'a' 到'z'：值0 代表'a'，值1代表'b'，依此类推。
 *
 * 找出按字典序最小的字符串，该字符串从这棵树的一个叶结点开始，到根结点结束。
 *
 * （小贴士：字符串中任何较短的前缀在字典序上都是较小的：例如，在字典序上"ab" 比"aba"要小。叶结点是指没有子结点的结点。）
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/smallest-string-starting-from-leaf
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q988_SmallestStringStartingFromLeaf {
    String ans = "~";//"~"的字典序比“z”高就行
    public String smallestFromLeaf(TreeNode root) {
        dfs(root, new StringBuffer());
        return ans;
    }

    // TODO：
    //  1.注意回溯的思想
    //  2.注意为什么使用StringBuffer
    public void dfs(TreeNode root, StringBuffer path) {
        if(root == null) return;
        path.append((char)('a'+root.val));
        if(root.left == null && root.right == null) {
            // TODO:注意
            //  StringBuffer的翻转
            //  String的字典序比较
            path.reverse();
            String s = path.toString();
            path.reverse();
            if(s.compareTo(ans) < 0) {
                ans = s;
            }
        }
        dfs(root.left, path);
        dfs(root.right, path);
        path.deleteCharAt(path.length() - 1);
    }
}
