package dataStructure.tree.travel;

import dataStructure.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，返回它的 后序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * */
public class HardQ145_PostOrder {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        postTravel(root, list);
        return list;
    }

    public void postTravel(TreeNode root, List<Integer> list) {
        if (root == null) return;
        postTravel(root.left, list);
        postTravel(root.right, list);
        list.add(root.val);
    }

    error;
    // TODO:注意这种写法
    public List<Integer> postorderTraversal2(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        if (root == null) return list;
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode p;
        stack.add(root);
        while (!stack.isEmpty()) {
            p = stack.pollLast();
            list.addFirst(p.val);
            if (p.left != null) stack.add(p.left);
            if (p.right != null) stack.add(p.right);
        }
        return list;
    }

    // TODO： 这种非递归的重要
    public List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> output = new LinkedList<>();
        if (root == null) return output;
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode p = root, pre = null;
        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            p = stack.peek();
            if (p.right != null && p.right != pre) {
                p = p.right;
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                output.add(p.val);
                pre = p;
                // TODO:不要忘了置为null
                p = null;
            }
        }

        return output;
    }

    // TODO：一个元素入栈两次空间复杂度比较大
    public List<Integer> postorderTraversal4(TreeNode root) {
        List<Integer> output = new LinkedList<>();
        if (root == null) return output;
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            if(p != null) {
                stack.push(p);
                stack.push(p);
                p = p.left;
                continue;
            }
            p = stack.pop();
            if(p == stack.peek()) {
                p = p.right;
            } else {
                output.add(p.val);
                p = null;
            }
        }

        return output;
    }

}
