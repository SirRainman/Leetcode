package dataStructure.tree.travel;


import dataStructure.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * */
public class Q102_LevelOrder {
    // TODO: linkedlist 和 Arraylist的区别？？？
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new LinkedList<>();
        if (root == null) return lists;
        Queue<TreeNode> level = new LinkedList<>();
        level.offer(root);
        TreeNode p;
        while (!level.isEmpty()) {
            int size = level.size();
            List<Integer> list = new LinkedList<>();
            while (size-- > 0) {
                p = level.poll();
                list.add(p.val);
                if (p.left != null) level.offer(p.left);
                if (p.right != null) level.offer(p.right);
            }
            lists.add(list);
        }
        return  lists;
    }
}
