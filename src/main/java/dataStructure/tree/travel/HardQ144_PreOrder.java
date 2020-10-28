package dataStructure.tree.travel;


import dataStructure.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，返回它的前序遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 *
 * 输出: [1,2,3]
 * 进阶:递归算法很简单，你可以通过迭代算法完成吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
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

    // TODO:想一想这种办法为什么更快？？？
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

    // TODO:
    //  1.什么是莫里斯遍历？？？
    //      用一些为空的存储空间来记住自己的后路
    //  2.莫里斯遍历的时间复杂度是多少？
    //      每个前驱恰好访问两次，因此复杂度是 O(2N)
    //  3.关键就在于设置pre存储左子树中最右边的节点：
    //      根据前序和中序遍历，只有遍历过左子树，才可以遍历右子树，所以在这里设置一个标志，pre是遍历左子树过程中的最后一个被访问的
    public List<Integer> preOrderMorris(TreeNode root) {
        LinkedList<Integer> output = new LinkedList<>();

        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) { // 如果左子树为空，先访问该节点，在拐向该节点的右子树
                output.add(cur.val);
                cur = cur.right;
            } else { // 如果左子树不为空
                TreeNode predecessor = cur.left; // 找到该树->左子树-最右边的节点
                while ((predecessor.right != null) && (predecessor.right != cur)) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {    // 如果该树的左子树最右边的节点是第一次访问到
                    output.add(cur.val);            // 访问该节点
                    predecessor.right = cur;        // 把该树左子树最右边节点，的右子树指向自己，记住自己的后路
                    cur = cur.left;                 // 继续向该树的左子树进发
                } else {                        // 如果以前访问过该树的左子树的最右边的节点
                    predecessor.right = null;   // 把该树左子树最右边节点的右子树恢复成null
                    cur = cur.right;            // 向该树的右子树拐弯
                }
            }
        }
        return output;
    }
}
