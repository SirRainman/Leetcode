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
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;

        Queue<TreeNode> queue = new LinkedList<>();
        int level = 0;
        TreeNode cur = root;
        queue.add(cur);
        while(!queue.isEmpty()) {
            int size = queue.size();
            level++;
            LinkedList<Integer> levelPath = new LinkedList<>();
            //System.out.println(queue.toString());
            while(size-- > 0) {
                cur = queue.poll();
                // TODO:注意判断奇偶的方法
                //  注意add 和 push 的区别！！！
                if((level & 1) == 1) {
                    levelPath.add(cur.val);
                } else {
                    levelPath.push(cur.val);
                }

                if(cur.left != null) queue.offer(cur.left);
                if(cur.right != null) queue.offer(cur.right);
            }
            // TODO:注意可以把LinkedList放到ArrayList里
            ans.add(new ArrayList(levelPath));
        }
        return ans;
    }

}
