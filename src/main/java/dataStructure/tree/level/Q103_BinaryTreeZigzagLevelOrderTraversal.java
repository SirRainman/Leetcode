package dataStructure.tree.level;

import dataStructure.TreeNode;

import java.util.*;

/**
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层次遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q103_BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;

        int level = 0;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            level++;
            int levelSize = queue.size();
            LinkedList<Integer> levelNodes = new LinkedList<>();
            while(levelSize-- > 0) {
                TreeNode p = queue.poll();
                // TODO:
                //  add 在末尾添加
                //  push 在开头添加
                // if(level % 2 == 1) levelNodes.add(p.val);
                // else levelNodes.push(p.val);
                if(level % 2 == 1) levelNodes.addLast(p.val);
                else levelNodes.addFirst(p.val);

                if(p.left != null) queue.offer(p.left);
                if(p.right != null) queue.offer(p.right);
            }
            res.add(levelNodes);
        }

        return res;
    }

}
