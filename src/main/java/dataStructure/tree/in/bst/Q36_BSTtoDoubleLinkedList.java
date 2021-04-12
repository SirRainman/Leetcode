package dataStructure.tree.in.bst;

import dataStructure.TreeNode;

public class Q36_BSTtoDoubleLinkedList {
    TreeNode head, pre;
    public TreeNode treeToDoublyList(TreeNode root) {
        if(root == null) return null;
        dfs(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    public void dfs(TreeNode root) {
        if(root == null) return ;
        dfs(root.left);
        root.left = pre;
        if(pre != null) pre.right = root;
        else head = root;
        pre = root;
        dfs(root.right);
    }
}
