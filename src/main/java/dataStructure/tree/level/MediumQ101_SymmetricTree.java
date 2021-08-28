package dataStructure.tree.level;


import dataStructure.TreeNode;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 * 例如，二叉树[1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 *
 * 但是下面这个[1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 *
 * 进阶：
 *
 * 你可以运用递归和迭代两种方法解决这个问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/symmetric-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MediumQ101_SymmetricTree {

    // TODO: 层次遍历判断二叉树是否对称
    public boolean compare2(TreeNode u, TreeNode v) {
        Deque<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(u);
        queue.offer(v);
        TreeNode p, q;
        while (!queue.isEmpty()) {
            p = queue.poll();
            q = queue.poll();

            if(p == null && q == null) {
                continue;
            } else if(p == null || q == null) {
                return false;
            } else if(p.val != q.val) {
                return false;
            }

            queue.offer(p.left);
            queue.offer(q.right);

            queue.offer(p.right);
            queue.offer(q.left);
        }
        return true;
    }

    public boolean isSymmetric(TreeNode root) {
        return isSame(root, root);
    }

    private boolean isSame(TreeNode p, TreeNode q) {
        if(p == null) return q == null;
        if(q == null) return p == null;
        return p.val == q.val && isSame(p.left, q.right) && isSame(p.right, q.left);
    }
}
