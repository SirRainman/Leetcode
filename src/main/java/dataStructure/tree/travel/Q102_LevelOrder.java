package dataStructure.tree.travel;


import dataStructure.TreeNode;

import java.util.*;

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
    //  ArrayList的实现用的是数组，LinkedList是基于链表，ArrayList适合查找，LinkedList适合增删
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;

        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> levelNodes = new ArrayList<>();
            while(levelSize-- > 0) {
                TreeNode p = queue.poll();
                levelNodes.add(p.val);
                if(p.left != null) queue.offer(p.left);
                if(p.right != null) queue.offer(p.right);
            }
            res.add(levelNodes);
        }

        return res;
    }
}
