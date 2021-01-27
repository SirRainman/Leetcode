package dataStructure.tree.in.bst;

public class Q36_BSTtoDoubleLinkedList {
    Node head, pre;
    public Node treeToDoublyList(Node root) {
        if(root == null) return null;
        dfs(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    public void dfs(Node root) {
        if(root == null) return ;
        dfs(root.left);
        root.left = pre;
        if(pre != null) pre.right = root;
        else head = root;
        pre = root;
        dfs(root.right);
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};