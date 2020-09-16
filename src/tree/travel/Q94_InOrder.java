package tree.travel;

import dataStructure.TreeNode;
import sun.tools.jstat.Jstat;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回它的中序 遍历。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q94_InOrder {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList();
        travel(root, list);
        return list;
    }

    public void travel(TreeNode root, List<Integer> list) {
        if (root == null) return;
        travel(root.left, list);
        list.add(root.val);
        travel(root.right, list);
    }


    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        // TODO: 为什么这里有两个终止条件？？？
        //  p != null 判断 遍历指针 是否走到了叶子节点
        //  !stack.isEmpty() 判断 是否走完了全部的节点
        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            p = stack.pop();
            list.add(p.val);
            p = p.right;
        }
        return list;
    }

    error;
    // TODO: 莫里斯遍历！！！
    class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            TreeNode curr = root;
            TreeNode pre;
            while (curr != null) {
                if (curr.left == null) {
                    res.add(curr.val);
                    curr = curr.right; // move to next right node
                } else { // has a left subtree
                    pre = curr.left;
                    while (pre.right != null) { // find rightmost
                        pre = pre.right;
                    }
                    pre.right = curr; // put cur after the pre node
                    TreeNode temp = curr; // store cur node
                    curr = curr.left; // move cur to the top of the new tree
                    temp.left = null; // original cur left be null, avoid infinite loops
                }
            }
            return res;
        }
    }
}
