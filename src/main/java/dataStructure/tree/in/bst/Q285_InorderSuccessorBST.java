package dataStructure.tree.in.bst;

import dataStructure.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @program: Leetcode
 * @description:
 * 给定一棵二叉搜索树和其中的一个节点 p ，找到该节点在树中的中序后继。如果节点没有中序后继，请返回 null 。
 * 节点p的后继是值比p.val大的节点中键值最小的节点。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/inorder-successor-in-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-03-31 11:34
 **/
public class Q285_InorderSuccessorBST {
    public TreeNode inorderSuccessor1(TreeNode root, TreeNode p) {
        if(root == null) return null;
        TreeNode left = inorderSuccessor(root.left, p);
        if(left != null) return left;
        if(root.val > p.val) return root;
        TreeNode right = inorderSuccessor(root.right, p);
        return right;
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        TreeNode cur = root;
        while(!stack.isEmpty() || cur != null) {
            while(cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if(cur.val > p.val) return cur;
            cur = cur.right;
        }
        return null;
    }

}
