package tree;

import dataStructure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例: 
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * */
public class Q112_PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        if(root.left == null && root.right == null) return sum == root.val;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> queueVal = new LinkedList<>();
        TreeNode cur = root;
        queue.offer(cur);
        queueVal.offer(cur.val);
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                cur = queue.poll();
                int levelSum = queueVal.poll();
                if (cur.left == null && cur.right == null && levelSum == sum) return true;
                if(cur.left != null) {
                    queue.offer(cur.left);
                    queueVal.offer(cur.left.val + levelSum);
                }
                if(cur.right != null) {
                    queue.offer(cur.right);
                    queueVal.offer(cur.right.val + levelSum);
                }
            }
        }
        return false;
    }
}
