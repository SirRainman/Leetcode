package tree.post;


import dataStructure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * */


public class Q104_MaximumDepthOfBinaryTree {

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int maxR, maxL;
        maxL = maxDepth(root.left);
        maxR = maxDepth(root.right);
        return maxL < maxR ? maxR + 1 : maxL + 1;
    }


    // TODO:想一想为什么会用层次遍历呢？
    //  防止递归时深度特别深
    public int maxDepth2(TreeNode root) {

        if (root == null) return 0;

        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        while (queue.isEmpty() == false) {
            int size = queue.size();
            while(size > 0) {
                size--;
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            depth++;
        }

        return depth;
    }
}
