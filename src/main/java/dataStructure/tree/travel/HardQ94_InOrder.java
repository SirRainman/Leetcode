package dataStructure.tree.travel;

import dataStructure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回它的中序遍历。
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
 * 输出: [1,3,2]
 * 进阶:递归算法很简单，你可以通过迭代算法完成吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ94_InOrder {
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
        //  1. p != null 判断 遍历指针 是否走到了叶子节点
        //  2. !dataStructure.stack.isEmpty() 判断 是否走完了全部的节点
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

    // TODO: 莫里斯遍历！！！
    //  1.利用叶子节点的未存储的空间，通过两次遍历节点完成遍历
    //  2.关键就在于设置pre存储左子树中最右边的节点：
    //      根据前序和中序遍历，只有遍历过左子树，才可以遍历右子树，所以在这里设置一个标志，pre是遍历左子树过程中的最后一个被访问的
    public List<Integer> inorderMorris(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                res.add(cur.val); // 如果没有左孩子，则直接访问右孩子
                cur = cur.right;
            } else {
                TreeNode predecessor = cur.left; // predecessor 节点就是当前 cur 节点向左走一步，然后一直向右走至无法走为止
                while (predecessor.right != null && predecessor.right != cur) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) { // 如果是第一次遍历到该节点
                    predecessor.right = cur;// 让 predecessor 的右指针指向 cur，继续遍历左子树
                    cur = cur.left;
                } else { // 第二次遍历到该节点，说明左子树已经访问完了，我们需要断开链接
                    res.add(cur.val);
                    predecessor.right = null;
                    cur = cur.right;
                }
            }
        }
        return res;
    }

}
