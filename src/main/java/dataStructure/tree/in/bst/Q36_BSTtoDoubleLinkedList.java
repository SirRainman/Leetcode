package dataStructure.tree.in.bst;

import dataStructure.TreeNode;

public class Q36_BSTtoDoubleLinkedList {

    private TreeNode pre = null, head = null;
    public TreeNode treeToDoublyList(TreeNode root) {
        if(root == null) return null;
        dfs(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    private void dfs(TreeNode root) {
        if(root == null) return;
        dfs(root.left);
        if(pre == null) head = root;
        else pre.right = root;
        root.left = pre;
        pre = root;
        dfs(root.right);
    }
}
