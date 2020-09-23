package dataStructure.tree.level;

import dataStructure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，在树的最后一行找到最左边的值。
 *
 * 示例 1:
 *
 * 输入:
 *
 *     2
 *    / \
 *   1   3
 *
 * 输出:
 * 1
 *
 *
 * 示例 2:
 *
 * 输入:
 *
 *         1
 *        / \
 *       2   3
 *      /   / \
 *     4   5   6
 *        /
 *       7
 *
 * 输出:
 * 7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-bottom-left-tree-value
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q513_FindBottomLeftTreeValue {

    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode cur;
        int ans = 0;
        while(!queue.isEmpty()) {
            cur = queue.poll();
            ans = cur.val;
            if(cur.right != null) queue.offer(cur.right);
            if(cur.left != null) queue.offer(cur.left);
        }
        return ans;
    }

    public int findBottomLeftValue2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode cur;
        queue.offer(root);
        int maxHeight = 0, l = 0;
        int ans = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            l++;
            while(size-- > 0) {
                cur = queue.poll();
                if(cur.left == null && cur.right == null && l > maxHeight)  {
                    ans = cur.val;
                    maxHeight = l;
                }
                if(cur.left != null) queue.offer(cur.left);
                if(cur.right != null) queue.offer(cur.right);
            }
        }
        return ans;
    }
}
