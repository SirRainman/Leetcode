package dataStructure.tree.travel;


import dataStructure.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，返回它的 前序 遍历。
 *
 *  示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,2,3]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * */
public class HardQ144_PreOrder {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        preTravel(root, list);
        return list;
    }

    public void preTravel(TreeNode root, List<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        preTravel(root.left, list);
        preTravel(root.right, list);
    }

    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                stack.push(p);
                list.add(p.val);
                p = p.left;
            }
            p = stack.pop();
            p = p.right;
        }
        return list;
    }

    public List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (root == null) return list;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode p = stack.pop();
            list.add(p.val);
            if (p.right != null) stack.push(p.right);
            if (p.left != null) stack.push(p.left);
        }
        return list;
    }


    error;
    // TODO:什么是莫里斯遍历？？？？？
    class Solution {
        public List<Integer> preorderTraversal(TreeNode root) {
            LinkedList<Integer> output = new LinkedList<>();

            TreeNode node = root;
            while (node != null) {
                if (node.left == null) {
                    output.add(node.val);
                    node = node.right;
                }
                else {
                    TreeNode predecessor = node.left;
                    while ((predecessor.right != null) && (predecessor.right != node)) {
                        predecessor = predecessor.right;
                    }

                    if (predecessor.right == null) {
                        output.add(node.val);
                        predecessor.right = node;
                        node = node.left;
                    }
                    else{
                        predecessor.right = null;
                        node = node.right;
                    }
                }
            }
            return output;
        }
    }
}
