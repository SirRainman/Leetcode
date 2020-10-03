package dataStructure.tree.travel;

import dataStructure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回它的中序遍历。
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
 * 进阶:递归算法很简单，你可以通过迭代算法完成吗？
 * <p>
 * <p>
 * <p>
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
        //  p != null 判断 遍历指针 是否走到了叶子节点
        //  !dataStructure.stack.isEmpty() 判断 是否走完了全部的节点
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

    // TODO: 莫里斯遍历！！！ 利用叶子节点的未存储的空间，通过两次遍历节点完成遍历
    class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<Integer>();
            TreeNode predecessor = null;

            while (root != null) {
                if (root.left == null) {
                    // 如果没有左孩子，则直接访问右孩子
                    res.add(root.val);
                    root = root.right;
                } else {
                    // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
                    predecessor = root.left;
                    while (predecessor.right != null && predecessor.right != root) {
                        predecessor = predecessor.right;
                    }

                    if (predecessor.right == null) { // 如果是第一次遍历到该节点
                        // 让 predecessor 的右指针指向 root，继续遍历左子树
                        predecessor.right = root;
                        root = root.left;
                    } else { // 第二次遍历到该节点，说明左子树已经访问完了，我们需要断开链接
                        res.add(root.val);
                        predecessor.right = null;
                        root = root.right;
                    }
                }

            }
            return res;
        }
    }

}
