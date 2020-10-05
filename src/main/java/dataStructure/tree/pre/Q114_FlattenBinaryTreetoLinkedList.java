package dataStructure.tree.pre;

import dataStructure.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个二叉树，原地将它展开为一个单链表。
 *
 * 例如，给定二叉树
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * 将其展开为：
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q114_FlattenBinaryTreetoLinkedList {
    public void flatten(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root, pre = null;
        stack.push(cur);
        while(cur != null || !stack.isEmpty()) {
            cur = stack.pop();
            if(pre != null) {
                pre.left = null;
                pre.right = cur;
            }
            if(cur != null && cur.right != null) stack.push(cur.right);
            if(cur != null && cur.left != null) stack.push(cur.left);
            pre = cur;
        }
    }

    TreeNode pre = null;
    public void flatten2(TreeNode root) {
        if(root == null ) return;
        flatten(root.right);
        flatten(root.left);
        root.right = pre;
        root.left = null;
        pre = root;
    }
}
